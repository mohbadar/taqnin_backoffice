package com.nsia.officems.odf.odf_subject;

import java.util.Date;

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
import com.nsia.officems.odf.odf_resolution.OdfResolution;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odf_subject")
@Entity(name = "OdfSubject")
public class OdfSubject extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odf_subject_tbl_generator")
    @SequenceGenerator(name = "odf_subject_tbl_generator", sequenceName = "odf_subject_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String subjectAbstract;

    @Column(columnDefinition = "TEXT")
    private String details;

    @ManyToOne
    @JoinColumn(name = "odf_resolution_id", nullable = true, referencedColumnName = "id")
    private OdfResolution resolution;

}
