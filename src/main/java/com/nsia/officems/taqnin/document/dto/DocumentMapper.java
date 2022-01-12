package com.nsia.officems.taqnin.document.dto;
import com.nsia.officems._admin.document_type.DocTypeService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.taqnin.document.Document;
public class DocumentMapper {
    public  static Document MapDocumentDto(Document document, DocumentDto dto, DocTypeService doctypeService){
        DateTimeChange changeDate = new DateTimeChange();
        try {
            document.setNumber(dto.getNumber());
            document.setApproved(false);
            document.setTitle(dto.getTitle());
            document.setBody(dto.getBody());
            document.setReceivedByMoJ(false);
            document.setDoctype(doctypeService.findById(dto.getDoctype_id()));
            document.setCompleted(false);
            return document;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        } 
    }

    public static WelcomeDocumentDto MapDocumentToWelcomeDocumentDto(Document document, WelcomeDocumentDto welcomeDocumentDto){
        DateTimeChange changeDate = new DateTimeChange();
        try {
            welcomeDocumentDto.setId(document.getId());
            welcomeDocumentDto.setTitle(document.getTitle());
            welcomeDocumentDto.setBody(document.getBody());
            welcomeDocumentDto.setDocument_no(document.getNumber());
            welcomeDocumentDto.setApproved(document.isApproved());
            //welcomeDocumentDto.setReceivedByMoJ(document.isReceivedByMoJ());

            welcomeDocumentDto.setCreated_at(document.getCreatedAt() == null ? null 
                : changeDate.convertGregorianDateToPersianDate(java.sql.Timestamp.valueOf(document.getCreatedAt())));
            welcomeDocumentDto.setWorkflow(document.getWorkflow());
            welcomeDocumentDto.setDecision(document.getDecision());
            welcomeDocumentDto.setDoctype(document.getDoctype());
            return welcomeDocumentDto;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        } 
    }
}
