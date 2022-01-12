package com.nsia.officems.gop.decree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
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
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.io.File;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import com.nsia.officems.gop.decree.Dto.DecreeDto;
import com.nsia.officems.gop.decree.Dto.DecreeViewDto;
import com.nsia.officems.gop.decree_individual.domain.DecreeIndividualDTO;
import com.nsia.officems.file.FileDownloadUtil;
import com.nsia.officems.gop.profile.ProfileProjection;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;

@RestController
@RequestMapping(value = "/api/decrees")
public class DecreeController {

    @Autowired
    private DecreeService decreeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

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
            return decreeService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(value = "/{decreeId}")
    public ResponseEntity<Map<String, Object>> findById(
            @PathVariable(name = "decreeId", required = true) long decreeId) {
        return ResponseEntity.ok(decreeService.findById(decreeId));
    }

    @GetMapping(value = "/{proposalNo}/proposal-details")
    public ResponseEntity<Map<String, Object>> getProposalDetails(
            @PathVariable(name = "proposalNo", required = true) String proposalNo) {
        return ResponseEntity.ok(decreeService.getProposalDetails(proposalNo));
    }

    @GetMapping(value = "/downloadFile/{decreeNumber}")
    public void downloadAttachment(@PathVariable(name = "decreeNumber", required = true) String decreeNumber,
            HttpServletResponse response) throws Exception {
        File file = decreeService.downloadAttachment(decreeNumber);
        fileDownloadUtil.fileDownload(file, response);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Decree> create(@RequestParam("data") String data,
            @RequestParam("profiles") String profiles,
            @RequestParam(name = "attachment", required = false) MultipartFile attachment) throws Exception {
        Gson g = new Gson();
        DecreeDto dto = g.fromJson(data, DecreeDto.class);
        Type profileListType = new TypeToken<List<DecreeIndividualDTO>>() {
        }.getType();

        List<DecreeIndividualDTO> decreeIndividualDTOs = g.fromJson(profiles, profileListType);
        return ResponseEntity.ok(decreeService.save(dto, decreeIndividualDTOs, attachment));
    }

    @GetMapping("/profiles/by-term")
    public ResponseEntity<List<ProfileProjection>> findProfilesByName(@RequestParam("term") String term) {
        return ResponseEntity.ok(decreeService.findProfilesByName(term));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(decreeService.delete(id));
    }

    @GetMapping(value = "/{id}/history", produces = MediaType.ALL_VALUE)
    public ResponseEntity<List<RevisionDTO>> getHistory(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(decreeService.getDecreeLog(id));
    }

}
