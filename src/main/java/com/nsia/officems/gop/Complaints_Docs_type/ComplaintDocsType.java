package com.nsia.officems.gop.Complaints_Docs_type;

import com.nsia.officems.infrastructure.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "ComplaintDocsType")
@Table(name = "complaint_docs_type")
public class ComplaintDocsType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaintDocsType_tbl_generator")
    @SequenceGenerator(name = "complaintsDocsType_tbl_generator", sequenceName = "complaintDocsType_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String code;

    @Column
    private String namePs;
    @Column
    private String nameDr;
    @Column
    private String nameEn;
}
