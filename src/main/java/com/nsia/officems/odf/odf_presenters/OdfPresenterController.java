package com.nsia.officems.odf.odf_presenters;

import java.util.List;

import com.nsia.officems.odf.odf_agenda.OdfAgendaService;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopicService;
import com.nsia.officems.odf.odf_presenters.dto.OdfPresenterDto;
import com.nsia.officems.odf.odf_presenters.dto.OdfPresenterMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/odf/odf-presenter")
public class OdfPresenterController {

    @Autowired
    private OdfPresenterService odfPresenterService;

    @Autowired
    private OdfAgendaTopicService odfAgendaTopicService;

    // @PostMapping("/list")
    // public Object getFilesList(@RequestBody String requestBody) throws Exception
    // {
    // JSONObject json = new JSONObject(requestBody);

    // DataTablesInput input = objectMapper.readValue(json.get("input").toString(),
    // DataTablesInput.class);
    // System.out.println("requestBody: " + input);

    // System.out.println("Filter inside GetFileList " + json.get("filters"));
    // Map<String, String> filters = new HashMap<>();
    // if (json.has("filters") && !(json.get("filters").toString().equals("null")))
    // {
    // System.out.println(json.get("filters"));
    // filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
    // } else {
    // filters = null;
    // }
    // try {
    // return orderService.getList(input, filters);
    // } catch (Exception e) {
    // System.out.println("Exception occured" + e.getLocalizedMessage());
    // }
    // return null;
    // }

    public List<OdfPresenter> findAll() {
        return odfPresenterService.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public OdfPresenter findById(@PathVariable("id") Long id) {
        return odfPresenterService.findById(id);
    }

    // @PostMapping(value = "/create")
    // public OdfPresenter create(@RequestBody OdfPresenterDto createPresenterDto) {
    // return odfPresenterService.create(
    // OdfPresenterMapper.MapPresentDto(new OdfPresenter(), createPresenterDto,
    // odfAgendaTopicService));
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
    // return ResponseEntity.ok(orderService.delete(id));
    // }

    // @GetMapping("subject/{subjectId}")
    // public List<OdfPresenter> findByAgendaTopic(@PathVariable("subjectId") Long
    // id) {
    // return odfPresenterService.findByAgendaTopicId(id);
    // }

    // @PostMapping(value = "/update/{id}")
    // public Boolean update(@PathVariable("id") Long id, @RequestBody OdfOrderDto
    // editOrderDto) {
    // return orderService.update(id, editOrderDto);
    // }
}
