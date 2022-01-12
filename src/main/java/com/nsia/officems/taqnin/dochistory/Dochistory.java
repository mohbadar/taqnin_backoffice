package com.nsia.officems.taqnin.dochistory;

import com.nsia.officems._admin.organization.Organization;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.taqnin.document.Document;
import com.nsia.officems.taqnin.workflow.Workflow;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.time.LocalDateTime;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dochistory")
@Entity(name = "Dochistory")
public class Dochistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dochistory_tbl_generator")
    @SequenceGenerator(name="dochistory_tbl_generator", sequenceName = "dochistory_tbl_seq", allocationSize = 1)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column
    private Date date;

    @Column
    private String number;

    @Column
    private Boolean isImport;

    @Column
    private Boolean isExport;

    @Column(nullable = true)
    private Boolean isCreated;

    @Column(nullable = true)
    private Boolean isCompeted;

    @Column(nullable = true)
    private String Remarks;
 
    @ManyToOne
    @JoinColumn(name = "document_id", nullable = true, referencedColumnName = "id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true, referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "workflow_id", nullable = true, referencedColumnName = "id")
    private Workflow workflow;

    @Column(nullable=true, columnDefinition = "varchar(800)")
   private String fileName;
    
}
