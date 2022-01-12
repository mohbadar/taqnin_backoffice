package com.nsia.officems.taqnin.dochistory.dto;

import com.nsia.officems._admin.organization.OrganizationService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.taqnin.decision.DecisionService;
import com.nsia.officems.taqnin.dochistory.Dochistory;
import com.nsia.officems.taqnin.document.Document;
import com.nsia.officems.taqnin.document.DocumentRepository;
import com.nsia.officems.taqnin.document.DocumentService;
import com.nsia.officems.taqnin.workflow.WorkflowService;

public class DochistoryMapper {
  
    public static Dochistory MapDochistoryDto(Dochistory dochistory,
            DochistoryDto dto, DocumentService documentService, WorkflowService workflowService, DocumentRepository documentRepository, DecisionService decisionService) {
        try {
            DateTimeChange changeDate = new DateTimeChange();
            dochistory.setDate(changeDate.convertPersianDateToGregorianDate(dto.getDate()));
            dochistory.setNumber(dto.getNumber());
            Document document = documentService.findById(dto.getDocument_Id());
            dochistory.setDocument(dto.getDocument_Id() == null ? null : document);
            dochistory.setWorkflow(dto.getWorkflow_id()==null?null:workflowService.findById(dto.getWorkflow_id()));
            document.setApproved(true);
            document.setDecision(decisionService.findById(dto.getDecision_id()));
            dochistory.setRemarks(dto.getRemarks());
            documentRepository.save(document);
            return dochistory;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
