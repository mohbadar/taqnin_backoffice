package com.nsia.officems.gop.panelty;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nsia.officems._admin.authority.Authority;
import com.nsia.officems._admin.commission.Commission;
import com.nsia.officems.gop.employeePosition.EmployeePosition;
import com.nsia.officems.gop.employeeStatus.EmployeeStatus;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGrade;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems._admin.ministry.Ministry;
import com.nsia.officems.gop.paneltyType.PaneltyType;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile_job.ProfileJob;

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
@Entity(name = "Panelty")
@Table(name = "panelty")
public class Panelty extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "panelty_tbl_generator")
	@SequenceGenerator(name="panelty_tbl_generator", sequenceName = "panelty_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "panelty_type", nullable = true, referencedColumnName = "id")
    private PaneltyType type;

    private String suggestedNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date suggestedDate;
    private String suggestedSource;
    private String decreeNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date decreeDate;
    private String degreeSource; 

    @ManyToOne
    @JoinColumn(name = "employee_position", nullable = true, referencedColumnName = "id")
    private EmployeePosition position;

    @ManyToOne
    @JoinColumn(name = "military_position", nullable = true, referencedColumnName = "id")
    private EmployeeMilitaryGrade militaryPosition;

    private String positionTitle;

    @ManyToOne
    @JoinColumn(name = "ministry", nullable = true, referencedColumnName = "id")
    private Ministry ministry;

    @ManyToOne
    @JoinColumn(name = "authority", nullable = true, referencedColumnName = "id")
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "commission", nullable = true, referencedColumnName = "id")
    private Commission commission;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true, referencedColumnName = "id")
    private EmployeeStatus status;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private Profile profile;
    
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "profile_job", nullable = true, referencedColumnName = "id")
    private ProfileJob profileJob;
}
