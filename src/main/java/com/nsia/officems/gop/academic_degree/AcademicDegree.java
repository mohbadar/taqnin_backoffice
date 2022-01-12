package com.nsia.officems.gop.academic_degree;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems.gop.academic_degree_type.AcademicDecreeType;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.profile.Profile;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "AcademicDegree")
@Table(name = "academic_degree")
public class AcademicDegree extends BaseEntity{ 
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "academic_degree_tbl_generator")
	@SequenceGenerator(name="academic_degree_tbl_generator", sequenceName = "academic_degreetbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String shumara;
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "academic_type", nullable = true, referencedColumnName = "id")
    private AcademicDecreeType type;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private Profile profile;
    
}
