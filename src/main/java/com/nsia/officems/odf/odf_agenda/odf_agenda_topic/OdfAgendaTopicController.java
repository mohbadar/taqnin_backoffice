package com.nsia.officems.odf.odf_agenda.odf_agenda_topic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.dto.OdfAgendaTopicDto;
import com.nsia.officems.odf.odf_presenters.OdfPresenter;
import com.nsia.officems.odf.odf_presenters.OdfPresenterService;
import com.nsia.officems.odf.odf_presenters.dto.OdfPresenterMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/odf/agenda_topics")
public class OdfAgendaTopicController {
    @Autowired
    private OdfAgendaTopicService odfAgendaTopicService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OdfPresenterService odfPresenterService;

    @GetMapping()
    public List<OdfAgendaTopic> findAll() {
        return odfAgendaTopicService.findAll();
    }

    @PostMapping("/list")
    public Object getFilesList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside GetFileList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return odfAgendaTopicService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") Long id) {
        OdfAgendaTopic odfAgendaTopic = odfAgendaTopicService.findById(id);
        List<OdfPresenter> presenters = odfPresenterService.findByAgendaTopicId(id);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("odfAgendaTopic", odfAgendaTopic);
        data.put("presenters", presenters);
        return ResponseEntity.ok(data);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id,
            @RequestParam("data") String data, HttpServletRequest request) throws Exception {
        Gson g = new Gson();
        OdfAgendaTopicDto dto = g.fromJson(data, OdfAgendaTopicDto.class);
        return ResponseEntity.ok(odfAgendaTopicService.update(id, dto));
    }

    // @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    // public @ResponseBody ResponseEntity<OdfAgendaTopic>
    // create(@RequestParam("data") String data, HttpServletRequest request) throws
    // Exception
    // {
    // Gson g = new Gson();
    // OdfAgendaTopicDto dto = g.fromJson(data, OdfAgendaTopicDto.class);
    // return ResponseEntity.ok(odfAgendaTopicService.create(dto));
    // }

    @PostMapping(value = "/update/{id}")
    public Boolean update(@PathVariable("id") Long id, @RequestBody OdfAgendaTopicDto editAgendaTopic) {
        Boolean isAgendaTopicServiceCreated = odfAgendaTopicService.update(id, editAgendaTopic);
        if (isAgendaTopicServiceCreated != null) {
            if (editAgendaTopic.getPresenters() != null) {
                editAgendaTopic.getPresenters().forEach((presenterName) -> {
                    OdfPresenter createdOdfPresenter = odfPresenterService.create(OdfPresenterMapper
                            .MapPresentDto(new OdfPresenter(), id, presenterName, odfAgendaTopicService));
                });
            }
        }
        return isAgendaTopicServiceCreated;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(odfAgendaTopicService.delete(id));
    }

    @GetMapping("agenda/{agendaId}")
    public List<OdfAgendaTopic> findByAgendaId(@PathVariable("agendaId") Long id) {
        return odfAgendaTopicService.findByAgendaId(id);
    }
}
