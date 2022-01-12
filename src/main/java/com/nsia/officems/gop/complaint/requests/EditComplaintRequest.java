package com.nsia.officems.gop.complaint.requests;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.nsia.officems.gop.Complaints_Docs_type.ComplaintDocsType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditComplaintRequest {

    private String name;

    private String fatherName;

    private String lastName;

    private String entryNumber;

    private Long complaintDocsType;

    private String complaintType;

    private String complaintDate;

    private String accused;

    private String explanations;

    private String profileCode;

}