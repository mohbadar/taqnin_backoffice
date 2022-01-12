package com.nsia.officems.taqnin.document_import;
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
@Table(name = "document_imports")
@Entity(name = "ImportDocument")
public class ImportDocument extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_imports_tbl_generator")
    @SequenceGenerator(name="document_imports_tbl_generator", sequenceName = "document_imports_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String import_number;
    
    @Column 
    private String import_date;
    
    @Column 
    private String body;


    @ManyToOne
    @JoinColumn(name = "document_id", nullable = true, referencedColumnName = "id")
    private Document document;

}
