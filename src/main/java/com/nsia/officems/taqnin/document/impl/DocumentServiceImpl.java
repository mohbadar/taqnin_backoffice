package com.nsia.officems.taqnin.document.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._admin.document_type.DocType;
import com.nsia.officems._admin.document_type.DocTypeService;
import com.nsia.officems._admin.organization.OrganizationService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._util.DateTimeChange;

import com.nsia.officems.file.FileUploadUtil;
import com.nsia.officems.taqnin.decision.Decision;
import com.nsia.officems.taqnin.decision.DecisionService;
import com.nsia.officems.taqnin.dochistory.Dochistory;
import com.nsia.officems.taqnin.dochistory.DochistoryRepository;
import com.nsia.officems.taqnin.dochistory.DochistoryService;
import com.nsia.officems.taqnin.document.Document;
import com.nsia.officems.taqnin.document.DocumentRepository;
import com.nsia.officems.taqnin.document.DocumentService;
import com.nsia.officems.taqnin.document.dto.DocumentDto;
import com.nsia.officems.taqnin.document.dto.DocumentMapper;
import com.nsia.officems.taqnin.document.dto.WelcomeDocumentDto;
import com.nsia.officems.taqnin.workflow.Workflow;
import com.nsia.officems.taqnin.workflow.WorkflowService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Value("${app.upload.taqnin.document}")
    private String uploadDir;

    DateTimeChange changeDate = new DateTimeChange();
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private DocumentRepository repo;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    FileUploadUtil fileUploadUtil;

    @Autowired
    UserService userService;

    @Autowired
    WorkflowService workflowService;
    @Autowired
    DecisionService decisionService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DochistoryService dochistoryService;

    @Autowired
    DochistoryRepository dochistoryRepository;

    @Autowired
    DocTypeService doctypeService;

    @Override
    public Document create(String data, MultipartFile file) {
        try {

            JsonNode root = mapper.readTree(data);
            Document document = new Document();

            document.setTitle(root.get("title").asText());
            document.setBody(root.get("body").asText());
            document.setNumber(root.get("number").asText());

            document.setDate(root.get("date") == null ? null
                    : changeDate.convertPersianDateToGregorianDate(root.get("date").asText()));
                 
            document.setProcessStartDate(root.get("date") == null ? null
                    : changeDate.convertPersianDateToGregorianDate(root.get("date").asText()));
                 

            document.setOrganization(root.get("organization_id") == null ? null
                    : organizationService.findById(root.get("organization_id").asLong()));

            document.setDoctype(root.get("doctype_id") == null ? null 
                    : doctypeService.findById(root.get("doctype_id").asLong()));  

            document.setUser(userService.getLoggedInUser());

            Document newObj = repo.save(document);

            //history record ....
            Dochistory dochistory = new Dochistory();
                dochistory.setDate(root.get("date") == null ? null
                        : changeDate.convertPersianDateToGregorianDate(root.get("date").asText()));

                dochistory.setNumber(root.get("number").asText());
                dochistory.setIsImport(false);
                dochistory.setIsExport(false);
                dochistory.setIsCreated(true);
                dochistory.setRemarks("DOCUMENT_CREATED");
                dochistory.setDocument(newObj);
                dochistory.setUser(userService.getLoggedInUser());
                dochistoryRepository.save(dochistory);
            //end of History record

            String fileName = fileUploadUtil.saveAttachment(file, uploadDir, newObj.getId().toString(), "document");
            if (newObj != null) {
                newObj.setFileName(fileName);
                return repo.save(newObj);
            }
            return null;

        } catch (Exception e) {
            System.out.println("exception occured in creating documentUpload");
            return null;
        }
    }

    @Override
    public Document update(Long id, String data, MultipartFile file) {
        try {

            DateTimeChange changeDate = new DateTimeChange();
            JsonNode root = mapper.readTree(data);
            Optional<Document> newDocument = repo.findById(id);

            if (newDocument.isPresent()) {
                Document document = newDocument.get();
                document.setTitle(root.get("title").asText());
                document.setBody(root.get("body").asText());
                document.setNumber(root.get("number").asText());

                document.setDate(root.get("date") == null ? null
                        : changeDate.convertPersianDateToGregorianDate(root.get("date").asText()));

                document.setProcessStartDate(root.get("date") == null ? null
                        : changeDate.convertPersianDateToGregorianDate(root.get("date").asText()));

                document.setOrganization(root.get("organization_id") == null ? null
                        : organizationService.findById(root.get("organization_id").asLong()));

                document.setDoctype(root.get("doctype_id") == null ? null 
                    : doctypeService.findById(root.get("doctype_id").asLong())); 

                document.setUser(userService.getLoggedInUser());
                Document newObj = repo.save(document);
                String fileName = fileUploadUtil.saveAttachment(file, uploadDir, newObj.getId().toString(), "document");
                if (newObj != null) {
                    newObj.setFileName(fileName);
                    return repo.save(newObj);
                }

                //update history record as well...
                return newObj;
            }
            return null;
        } catch (Exception e) {
            System.out.println("exception occured in creating documentUpload");
            return null;
        }
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        Boolean isClient = userService.getLoggedInUser().getIsClient();
        Boolean isWorkflow = userService.getLoggedInUser().getIsWorkflow();
        Boolean isAdmin = userService.getLoggedInUser().getIsAdmin();

        
        // Long departmentUserId = userService.getLoggedInUser().getDepartment().getId();

        String joinClause = "left join public.organization org on org.id=doc.organization_id left join public.taqnin_document_type type on type.id=doc.doctype_id ";

        // String joinClause = "inner join public.user_tbl user on
        // user.workflow_id=doc.workflow_id";
        // To have first AND with no error
        String whereClause = "";
        if (isClient) {
            Long organizationId = userService.getLoggedInUser().getEntity().getId();
            whereClause = " doc.deleted is not true and doc.organization_id = " + organizationId;
        } else if (isWorkflow) {
            Long workflowUserId = userService.getLoggedInUser().getWorkflow().getId();
            whereClause = " doc.deleted is not true and doc.workflow_id IN (SELECT DISTINCT workflow_id from public.dochistory where workflow_id ="+ workflowUserId +")"; // + workflowUserId;
        } else if(isClient == false && isWorkflow == false && isAdmin == false) {
            //get by department id 
            Long departmentId = userService.getLoggedInUser().getDepartment().getId();
            whereClause = " doc.deleted is not true and doc.department_id = " + departmentId;
        } else {
            whereClause = " doc.deleted is not true";
        }

        String groupByClause = "";
        return dataTablesUtil.getDataList("public.taqnin_documents doc", null, joinClause, whereClause, groupByClause, input);
    }

    @Override
    public Document findById(long id) {
        Optional<Document> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public boolean approveDocument(Long id, DocumentDto dto) {
        if (id != null) {
            // Boolean isMoJ = false;
            // if(userService.getLoggedInUser().getIsClient() == false && userService.getLoggedInUser().getIsWorkflow() == false){
            //     isMoJ = true;
            // }

            Optional<Document> newDocument = repo.findById(id);
            if (newDocument.isPresent()) {
                Document updatedDocument = newDocument.get();
                updatedDocument.setReceivedByMoJ(true); //this will be true once the document approved ...
                updatedDocument.setApproved(true);

                Workflow workflow = dto.getWorkflow_id()==null?null:workflowService.findById(dto.getWorkflow_id());
                if (workflow != null)
                    updatedDocument.setWorkflow(workflow);
                Decision decision = decisionService.findById(dto.getDecision_id());
                if (decision != null)
                    updatedDocument.setDecision(decision);
                updatedDocument.setNumber(dto.getNumber());
                updatedDocument.setDate(dto.getDate() == null ? null : changeDate.convertPersianDateToGregorianDate(dto.getDate()));
                
                updatedDocument.setWorkflowAssignDate(dto.getDate() == null ? null : changeDate.convertPersianDateToGregorianDate(dto.getDate()));

                Dochistory dochistory = new Dochistory();
                dochistory.setDate(
                        dto.getDate() == null ? null : changeDate.convertPersianDateToGregorianDate(dto.getDate()));
                dochistory.setNumber(dto.getNumber());
                dochistory.setIsImport(true);
                dochistory.setIsExport(false);
                dochistory.setRemarks(dto.getRemarks());

                dochistory.setWorkflow(workflow);
                dochistory.setDocument(updatedDocument);
                dochistoryRepository.save(dochistory);

                repo.save(updatedDocument);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean assignDocument(Long id, String data, MultipartFile file) {
        try{
        if (id != null) {
            // Boolean isMoJ = false;
            // if(userService.getLoggedInUser().getIsClient() == false && userService.getLoggedInUser().getIsWorkflow() == false){
            //     isMoJ = true;
            // }

            JsonNode root = mapper.readTree(data);

            Optional<Document> newDocument = repo.findById(id);
            if (newDocument.isPresent()) {
                Document updatedDocument = newDocument.get();
                Workflow workflow = root.get("workflow_id") == null ? null: workflowService.findById(root.get("workflow_id").asLong());
                if (workflow != null)
                    updatedDocument.setWorkflow(workflow);

                Decision decision = root.get("decision_id") == null ? null: decisionService.findById(root.get("decision_id").asLong());
                if (decision != null)
                    updatedDocument.setDecision(decision);

                updatedDocument.setNumber(root.get("number").asText());

                updatedDocument.setDate(root.get("date") == null ? null
                        : changeDate.convertPersianDateToGregorianDate(root.get("date").asText()));

                updatedDocument.setApproved(false);

                if(userService.getLoggedInUser().getIsClient() == false && userService.getLoggedInUser().getIsWorkflow() == false){
                    updatedDocument.setWorkflowAssignDate(null);
                }
                
                Dochistory dochistory = new Dochistory();
                dochistory.setDate(root.get("date") == null ? null
                : changeDate.convertPersianDateToGregorianDate(root.get("date").asText()));
                dochistory.setNumber(root.get("number").asText());
                dochistory.setIsImport(false);
                dochistory.setIsExport(true);
                dochistory.setRemarks(root.get("remarks").asText());

                dochistory.setWorkflow(workflow);
                dochistory.setDocument(updatedDocument);
                Dochistory newDocHistory = dochistoryRepository.save(dochistory);
                repo.save(updatedDocument);

                String fileName = fileUploadUtil.saveAttachment(file, uploadDir, updatedDocument.getId().toString()+"_v", "document_v");
                if (dochistory != null) {
                    dochistory.setFileName(fileName);
                    dochistoryRepository.save(dochistory);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
        } catch (Exception e) {
            System.out.println("exception occured in creating documentUpload");
            return false;
        }
    }

    @Override
    public boolean documentCompletion(Long id, DocumentDto dto) {
        if (id != null) {
            // Boolean isMoJ = false;
            // if(userService.getLoggedInUser().getIsClient() == false && userService.getLoggedInUser().getIsWorkflow() == false){
            //     isMoJ = true;
            // }

            Optional<Document> newDocument = repo.findById(id);
            if (newDocument.isPresent()) {
                Document updatedDocument = newDocument.get();
                Workflow workflow = dto.getWorkflow_id()==null?null:workflowService.findById(dto.getWorkflow_id());
                if (workflow != null)
                    updatedDocument.setWorkflow(workflow);
                Decision decision = decisionService.findById(dto.getDecision_id());
                if (decision != null)
                updatedDocument.setDecision(decision);
                updatedDocument.setNumber(dto.getNumber());
                updatedDocument.setDate(dto.getDate() == null ? null : changeDate.convertPersianDateToGregorianDate(dto.getDate()));

                updatedDocument.setCompleted(true);
                updatedDocument.setProcessEndDate(dto.getDate() == null ? null : changeDate.convertPersianDateToGregorianDate(dto.getDate()));
                updatedDocument.setCompletionRemarks(dto.getRemarks());

                if(userService.getLoggedInUser().getIsClient() == false && userService.getLoggedInUser().getIsWorkflow() == false){
                    updatedDocument.setWorkflowAssignDate(null);
                }
                
                Dochistory dochistory = new Dochistory();
                dochistory.setDate(
                        dto.getDate() == null ? null : changeDate.convertPersianDateToGregorianDate(dto.getDate()));
                dochistory.setNumber(dto.getNumber());
                dochistory.setIsImport(false);
                dochistory.setIsExport(false);
                dochistory.setIsCompeted(true);
                dochistory.setRemarks(dto.getRemarks());

                dochistory.setWorkflow(workflow);
                dochistory.setDocument(updatedDocument);
                dochistoryRepository.save(dochistory);
                repo.save(updatedDocument);

                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        Optional<Document> oDocument = repo.findById(id);
        if (oDocument.isPresent()) {
            Document document = oDocument.get();
            document.setDeletedBy(userService.getLoggedInUser().getUsername());
            document.setDeleted(true);
            document.setDeletedAt(LocalDateTime.now());
            repo.save(document);
            return true;
        }
        return false;
    }

    @Override
    public List<Document> findAll() {
        return repo.findAll();
    }

    public List<WelcomeDocumentDto> getApprovedDocuments() {
        List<WelcomeDocumentDto> list = new ArrayList<WelcomeDocumentDto>();
        List<Document> documents = repo.findByDeletedFalseOrDeletedIsNullAndApprovedTrue();
        documents.forEach((doc) -> {
            WelcomeDocumentDto welcomeDocumentDto = new WelcomeDocumentDto();
            DocumentMapper.MapDocumentToWelcomeDocumentDto(doc, welcomeDocumentDto);
            list.add(welcomeDocumentDto);
        });
        return list;
    }

    public List<WelcomeDocumentDto> getFilteredApprovedDocuments(String query) {
        List<WelcomeDocumentDto> list = new ArrayList<WelcomeDocumentDto>();
        List<Document> documents = repo.findAllFilteredDocuments(query);
        documents.forEach((doc) -> {
            WelcomeDocumentDto welcomeDocumentDto = new WelcomeDocumentDto();
            DocumentMapper.MapDocumentToWelcomeDocumentDto(doc, welcomeDocumentDto);
            list.add(welcomeDocumentDto);
        });

        return list;
    }

    @Override
    public File downloadAttachment(Long id) throws Exception {
        Optional<Document> document = repo.findById(id);
        if (document.isPresent()) {

            String fileName = document.get().getFileName();

            String saveDirectory = uploadDir + "/" + id + "/" + fileName;
            if (new File(saveDirectory).exists()) {
                return new File(saveDirectory);
            }
        }

        return null;
    }

    public long countTotalDocuments() {
        return repo.countTotalDocuments();
    }

    public long countApprovedDocuments() {
        return repo.countApprovedDocuments();
    }

    public long countNotApprovedDocuments() {
        return repo.countNotApprovedDocuments();
    }

    // public long countInProgressDocuments(){
    // return repo.countInProgressDocuments();
    // }

    @Override
    public Document assignDepartment(Long doc_id, Long dep_id) {
        Optional<Document> newDocument = repo.findById(doc_id);
        if (newDocument.isPresent()) {
            Document document = newDocument.get();
            document.setDepartment(departmentService.findById(dep_id));
            return repo.save(document);
        }

        return null;
    }

}
