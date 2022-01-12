package com.nsia.officems.gop.recruitment;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recruitments")
public class RecruitmentController {
    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private ObjectMapper objectMapper;

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
            return recruitmentService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(value = "/{profileId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "profileId", required = true) long objNumber) {
        System.out.println("Objection " + objNumber);
        Map<String, Object> data = new HashMap<String, Object>();
        Recruitment objection = recruitmentService.findById(objNumber);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Recruitment> update(@PathVariable(name = "id", required = true) Long id,
            @RequestBody(required = true) Recruitment recruitment) {
        System.out.println("ID: " + id + " Profile: " + recruitment);
        // TODO
        return null;
    }

    @PostMapping()
    public ResponseEntity<Long> create(@RequestBody String body) throws JSONException {
        return ResponseEntity.ok(recruitmentService.create(body));
    }

}