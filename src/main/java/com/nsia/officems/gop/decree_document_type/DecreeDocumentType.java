package com.nsia.officems.gop.decree_document_type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "decreeDocumentType")
@Table(name = "decree_document_type")
public class DecreeDocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "decree_document_type_tbl_generator")
    @SequenceGenerator(name = "decree_document_type_tbl_generator", sequenceName = "decree_document_type_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String namePs;
    @Column
    private String nameDr;
    @Column
    private String nameEn;

    @Column(nullable = false)
    private String code;

}
