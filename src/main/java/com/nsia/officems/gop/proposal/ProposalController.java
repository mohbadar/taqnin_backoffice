package com.nsia.officems.gop.proposal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nsia.officems.gop.documentType.DocumentType;
import com.nsia.officems.gop.documentType.DocumentTypeService;
import com.nsia.officems.file.FileDownloadUtil;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.proposal.Dto.CreateProposalDto;
import com.nsia.officems.gop.proposal.Dto.ProposalDto;
import com.nsia.officems.gop.proposalIndividual.ProposalIndividual;
import com.nsia.officems.gop.proposalIndividual.ProposalIndividualService;
import com.nsia.officems.gop.proposalIndividual.Dto.ProposalIndividualDto;
import com.nsia.officems.gop.suggestionStatus.SuggestionStatus;
import com.nsia.officems.gop.suggestionStatus.SuggestionStatusService;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubject;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubjectService;
import com.nsia.officems.gop.suggestionType.SuggestionType;
import com.nsia.officems.gop.suggestionType.SuggestionTypeService;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
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

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {

    @Value("${app.upload.proposal}")
    private String uploadDir;

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SuggestionTypeService suggestionType;
    @Autowired
    private SuggestionStatusService suggestionStatus;
    @Autowired
    private SuggestionSubjectService suggestionSubject;
    @Autowired
    private DocumentTypeService documentType;

    @Autowired
    private ProposalIndividualService proposalProfile;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @GetMapping("/load-data")
    public ResponseEntity<Map<String, Object>> getLoadData() {
        Map<String, Object> map = new HashMap<>();
        map.put("documentType", documentType.findAll());
        map.put("suggestionType", suggestionType.findAll());
        map.put("suggestionStatus", suggestionStatus.findAll());
        map.put("suggestionSubject", suggestionSubject.findAll());
        return ResponseEntity.ok(map);
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
            return proposalService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(value = "/{proposalId}")
    public ResponseEntity<Object> findById(@PathVariable(name = "proposalId", required = true) long proposalId) {
        System.out.println("Proposal " + proposalId);
        Map<String, Object> data = new HashMap<>();
        Profile profile = new Profile();
        Proposal proposal = proposalService.findById(proposalId);
        List<ProposalIndividual> indProposal = new ArrayList();
        List<ProposalIndividual> indProp = proposalProfile.findByProposalId(proposal);
        for (ProposalIndividual proposalIndividual : indProp) {
            if (proposalIndividual.getProfile() != null && proposalIndividual.getProfile().getId() != null) {
                System.out.println("profile ID: " + proposalIndividual.getProfile().getId().toString());
                System.out.println("%%%%%%%prfoile%%%%%%%%%%%"
                        + profileService.findByIdWithoutRelation(proposalIndividual.getProfile().getId()).getId());
                profile.setId(profileService.findByIdWithoutRelation(proposalIndividual.getProfile().getId()).getId());
                proposalIndividual.setProfile(profile);
            }
            indProposal.add(proposalIndividual);
        }

        data.put("proposal", proposal);
        data.put("individual", indProposal);
        return ResponseEntity.ok(data);
    }

    @PostMapping(value = "/update/{id}")
    public Proposal update(@PathVariable("id") Long id, @RequestBody CreateProposalDto createProposalDto)
            throws Exception {
        return proposalService.update(createProposalDto, id);
    }

    @PostMapping(value = "/create")
    public Proposal create(@RequestBody CreateProposalDto createProposalDto) {
        return proposalService.create(createProposalDto);
    }

    @GetMapping(value = "/download-attachment/{proposalId}")
    public void downloadAttachment(@PathVariable(name = "proposalId", required = true) Long id,
            HttpServletResponse response) throws Exception {
        File file = proposalService.downloadAttachment(id);
        fileDownloadUtil.fileDownload(file, response);
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<Proposal> deleteRecord(@PathVariable("id") Long id) {
        return ResponseEntity.ok(proposalService.deleteRecord(id));
    }

    @GetMapping(path = "/attachment/{id}")
    public Map<String, String> findAllFileNames(@PathVariable("id") Long id) {
        Proposal proposal = proposalService.findById(id);
        Map<String, String> fileName = new HashMap<String, String>();
        fileName.put("proposalAttachment", proposal.getAttachment());
        return fileName;
    }

    @PostMapping(path = "/upload/attachment/{id}")
    public String uploadComplaintDocuments(@PathVariable(value = "id") Long id, @RequestBody MultipartFile file)
            throws IOException {
        Proposal proposal = proposalService.findById(id);
        String uploadDirectory = uploadDir + "/" + proposal.getId().toString();
        String fileLocation = proposalService.saveAttachment(uploadDirectory, file);
        Boolean fieldUpdated = proposalService.updateFileLocation(id, fileLocation);
        if (fieldUpdated) {
            return fileLocation;
        } else
            return null;
    }

    @DeleteMapping(path = "/delete-attachment/{id}")
    public ResponseEntity<Boolean> deleteFile(@PathVariable("id") Long id) {
        return ResponseEntity.ok(proposalService.deleteAttachment(id));
    }

    @GetMapping(value = "/{id}/history", produces = MediaType.ALL_VALUE)
    public ResponseEntity<List<RevisionDTO>> getHistory(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(proposalService.getProposalLog(id));
    }
}