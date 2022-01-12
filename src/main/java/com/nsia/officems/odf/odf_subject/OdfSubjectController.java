package com.nsia.officems.odf.odf_subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._admin.shura.ShuraService;
import com.nsia.officems.odf.odf_resolution.OdfResolutionService;
import com.nsia.officems.odf.odf_resolution.dto.OdfResolutionDto;
import com.nsia.officems.odf.odf_resolution.dto.OdfResolutionMapper;
import com.nsia.officems.odf.odf_subject.dto.OdfSubjectDto;
import com.nsia.officems.odf.odf_subject.dto.OdfSubjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
public class OdfSubjectController {

    @Autowired
    private OdfSubjectService odfSubjectService;

    @Autowired
    private OdfResolutionService odfResolutionService;

    @GetMapping
    public List<OdfSubject> findAll() {
        return odfSubjectService.findAll();
    }

    @GetMapping("resolution/{resolutionId}")
    public List<OdfSubject> findByResolutionId(@PathVariable("resolutionId") Long id) {
        return odfSubjectService.findByResolutionId(id);
    }

    @GetMapping(path = { "/{id}" })
    public OdfSubject findById(@PathVariable("id") Long id) {
        return odfSubjectService.findById(id);
    }

    @PostMapping(value = "/create")
    public OdfSubject create(@RequestBody OdfSubjectDto createSubjectDto) {
        return odfSubjectService
                .create(OdfSubjectMapper.MapSubjectDto(new OdfSubject(), createSubjectDto, odfResolutionService));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(odfSubjectService.delete(id));
    }

    @PostMapping(value = "/update/{id}")
    public Boolean update(@PathVariable("id") Long id, @RequestBody OdfSubjectDto odfSubjectDto) {
        return odfSubjectService.update(id, odfSubjectDto);
    }

}
