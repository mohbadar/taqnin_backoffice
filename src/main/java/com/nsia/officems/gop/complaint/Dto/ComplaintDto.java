package com.nsia.officems.gop.complaint.Dto;

import java.util.Date;

import com.nsia.officems.gop.Complaints_Docs_type.ComplaintDocsType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintDto {

    private String name;

    private String fatherName;

    private String lastName;

    private String entryNumber;

    private ComplaintDocsType complaintDocsType;

    private String complaintType;

    private Date complaintDate;

    private String accused;

    private String explanations;

    private String profileCode;

}
