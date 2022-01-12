package com.nsia.officems.gop.employee_militrary_grade;


import com.nsia.officems.infrastructure.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity(name = "EmployeeMilitaryGrade")
@Table(name = "employee_military_grade")
public class EmployeeMilitaryGrade extends BaseEntity{

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_military_grade_tbl_generator")
	@SequenceGenerator(name="employee_military_grade_tbl_generator", sequenceName = "employee_military_grade_tbl_seq", allocationSize = 1)
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
