package com.nsia.officems.taqnin.document.document_attachment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.taqnin.document.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "document_attachment")
@Entity(name = "DocumentAttachment")
public class DocumentAttachment extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_attachment_tbl_generator")
    @SequenceGenerator(name = "document_attachment_tbl_generator", sequenceName = "document_attachment_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String fileName;

    @Column(columnDefinition = "varchar(800)")
    private String attachmentName;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = true, referencedColumnName = "id")
    private Document document;

}
