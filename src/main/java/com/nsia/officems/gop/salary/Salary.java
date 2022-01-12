package com.nsia.officems.gop.salary;

import com.nsia.officems.infrastructure.base.BaseEntity;

import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile_job.ProfileJob;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity(name = "salary")
@Table(name = "Salary")
public class Salary extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salary_crime_tbl_generator")
	@SequenceGenerator(name="salary_crime_tbl_generator", sequenceName = "salary_crime_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id; 
    
    private Double originalSalary;
    private Double patentSalary;
    private Double extraSalary;
    private Double macul;
    private Double cadreSalary;

    private Double original;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "profile_job", nullable = true, referencedColumnName = "id")
    private ProfileJob profileJob;

}
