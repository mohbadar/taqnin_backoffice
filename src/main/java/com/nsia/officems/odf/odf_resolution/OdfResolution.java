package com.nsia.officems.odf.odf_resolution;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._admin.shura.Shura;
import com.nsia.officems.infrastructure.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odf_resolution")
@Entity(name = "OdfResolution")
public class OdfResolution extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odf_resolution_tbl_generator")
    @SequenceGenerator(name = "odf_resolution_tbl_generator", sequenceName = "odf_resolution_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String resolutionNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date resolutionDate;

    @ManyToOne
    @JoinColumn(name = "shura_id", nullable = true, referencedColumnName = "id")
    private Shura shura;

    @Column(columnDefinition = "TEXT")
    private String components;

}
