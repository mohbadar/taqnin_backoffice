package com.nsia.officems.taqnin.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.document_type.DocType;
import com.nsia.officems._admin.organization.Organization;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.taqnin.decision.Decision;
import com.nsia.officems.taqnin.workflow.Workflow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "taqnin_documents")
@Entity(name = "Document")
public class Document extends BaseEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taqnin_documents_tbl_generator")
   @SequenceGenerator(name = "taqnin_documents_tbl_generator", sequenceName = "taqnin_documents_tbl_seq", allocationSize = 1)
   @Column(unique = true, updatable = false, nullable = false)
   private Long id;

   private String title;

   @Column(columnDefinition = "TEXT")
   private String body;

   @Column(unique = true)
   private String number;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kabul")
   private Date date;

   private boolean approved;

   @Column(columnDefinition = "varchar(800)")
   private String fileName;

   @Column(nullable = true)
   private boolean receivedByMoJ;
   
   @ManyToOne
   @JoinColumn(name = "organization_id", nullable = true, referencedColumnName = "id")
   private Organization organization;


   @ManyToOne
   @JoinColumn(name = "user_id", nullable = true, referencedColumnName = "id")
   private User user;
   

   @OneToOne(targetEntity = Workflow.class, fetch = FetchType.EAGER)
   @JoinColumn(nullable = true, name = "workflow_id", referencedColumnName = "id")
   private Workflow workflow;

   @OneToOne(targetEntity = Decision.class, fetch = FetchType.EAGER)
   @JoinColumn(nullable = true, name = "decision_id", referencedColumnName = "id")
   private Decision decision;

   @ManyToOne
   @JoinColumn(name = "department_id", nullable = true, referencedColumnName = "id")
   private Department department;

   @ManyToOne
   @JoinColumn(name = "doctype_id", nullable = true, referencedColumnName = "id")
   private DocType doctype;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kabul")
   private Date workflowAssignDate;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kabul")
   private Date processStartDate;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kabul")
   private Date processEndDate;

   @Column(nullable = true)
   private boolean completed;

   @Column(nullable = true)
   private String completionRemarks;

}
