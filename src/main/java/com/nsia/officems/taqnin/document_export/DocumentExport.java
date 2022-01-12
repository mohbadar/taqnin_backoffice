package com.nsia.officems.taqnin.document_export;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.taqnin.document.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "document_exports")
@Entity(name = "DocumentExport")
public class DocumentExport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_exports_tbl_generator")
    @SequenceGenerator(name="document_exports_tbl_generator", sequenceName = "document_exports_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String export_number;
    
    @Column 
    private String export_date;
    
    @Column 
    private String body;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = true, referencedColumnName = "id")
    private Document document;

}
