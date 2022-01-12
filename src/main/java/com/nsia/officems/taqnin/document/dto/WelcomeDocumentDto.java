package com.nsia.officems.taqnin.document.dto;

import com.nsia.officems._admin.document_type.DocType;
import com.nsia.officems.taqnin.decision.Decision;
import com.nsia.officems.taqnin.workflow.Workflow;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WelcomeDocumentDto {
    private Long id;
    private String title;
    private String body;
    private String document_no;
    private boolean approved;
    private String document_file;
    private String created_at;
    private Workflow workflow;
    private Decision decision;
    private boolean receivedByMoJ;
    private DocType doctype;
}
