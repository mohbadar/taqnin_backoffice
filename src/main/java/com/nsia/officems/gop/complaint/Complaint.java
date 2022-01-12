package com.nsia.officems.gop.complaint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems.gop.Complaints_Docs_type.ComplaintDocsType;
import com.nsia.officems.infrastructure.base.BaseEntity;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@Audited
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Complaints")
@Table(name = "complaints")
public class Complaint extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaint_tbl_generator")
    @SequenceGenerator(name = "decree_tbl_generator", sequenceName = "complaint_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column()
    private String name;

    @Column
    private String fatherName;

    @Column
    private String lastName;

    @Column
    private String entryNumber;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "complaint_doc_type", nullable = false, referencedColumnName = "id")
    private ComplaintDocsType complaintDocsType;

    @Column
    private String complaintType;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date complaintDate;

    @Column
    private String accused;

    @Column(columnDefinition = "TEXT")
    private String explanations;

    @Column
    private String profileCode;

    // Attachments
    @Column
    private String objectionAttachment;

    @Column
    private String complaintToResponsibleAuthorityAttachment;

    @Column
    private String complaintToBoardAttachment;

    @Column
    private String courtDecreeAttachment;

    @Column
    private String committeeDecisionAttachment;

    @Column
    private String responsibleAuthorityResponseAttachment;

}