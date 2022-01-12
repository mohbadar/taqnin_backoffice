package com.nsia.officems.odf.odf_order;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems.odf.odf_order.dto.OdfOrderDto;
import com.nsia.officems.odf.odf_order.dto.OdfOrderMapper;
import com.nsia.officems.odf.odf_proposal.dto.OdfProposalDto;
import com.nsia.officems.odf.odf_subject.OdfSubjectService;

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
@RequestMapping("/api/odf/odf-order")
public class OdfOrderController {
    @Autowired
    private OdfOrderService orderService;

    @Autowired
    private OdfSubjectService odfSubjectService;

    @Autowired
    private DepartmentService departmentService;

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
            return orderService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    public List<OdfOrder> findAll() {
        return orderService.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public OdfOrder findById(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }

    @PostMapping(value = "/create")
    public OdfOrder create(@RequestBody OdfOrderDto createOrderDto) {
        return orderService.create(
            OdfOrderMapper.MapOrderDto(new OdfOrder(), createOrderDto, odfSubjectService, departmentService));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.delete(id));
    }

    @GetMapping("subject/{subjectId}")
    public List<OdfOrder> findBySubjectId(@PathVariable("subjectId") Long id) {
        return orderService.findBySubjectId(id);
    }

    @PostMapping(value = "/update/{id}")
    public Boolean update(@PathVariable("id") Long id, @RequestBody OdfOrderDto editOrderDto) {
        return orderService.update(id, editOrderDto);
    }
}
