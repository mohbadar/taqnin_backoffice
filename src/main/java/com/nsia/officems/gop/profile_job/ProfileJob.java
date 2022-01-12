package com.nsia.officems.gop.profile_job;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nsia.officems.gop.accountability.Accountability;
import com.nsia.officems._admin.authority.Authority;
import com.nsia.officems._admin.commission.Commission;
import com.nsia.officems.gop.education_level.EducationLevel;
import com.nsia.officems.gop.employeeGrade.EmployeeGrade;
import com.nsia.officems.gop.employeePosition.EmployeePosition;
import com.nsia.officems.gop.employeeStatus.EmployeeStatus;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGrade;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems._admin.ministry.Ministry;
import com.nsia.officems.gop.panelty.Panelty;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile_education.Education;
import com.nsia.officems.gop.transfer_profile.Transfer;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity(name = "profile_job")
@Table(name = "ProfileJob")
public class ProfileJob extends BaseEntity{ 
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "initial_profile_job_crime_tbl_generator")
	@SequenceGenerator(name="initial_profile_job_crime_tbl_generator", sequenceName = "initial_profile_job_crime_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id; 

    private String positionTitle;

    private String maktubNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date maktubDate;
    
    @NotAudited
    @ManyToOne
    @JoinColumn(name = "employee_grade", nullable = true, referencedColumnName = "id")
    private EmployeeGrade grade;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "military_grade", nullable = true, referencedColumnName = "id")
    private EmployeeMilitaryGrade militaryGrade;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "employee_position", nullable = true, referencedColumnName = "id")
    private EmployeePosition position;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "military_position", nullable = true, referencedColumnName = "id")
    private EmployeeMilitaryGrade militaryPosition;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "ministry", nullable = true, referencedColumnName = "id")
    private Ministry ministry;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "authority", nullable = true, referencedColumnName = "id")
    private Authority authority;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "commission", nullable = true, referencedColumnName = "id")
    private Commission commission;


    @NotAudited
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false, referencedColumnName = "id")
    private Profile profile;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "accountability_id", nullable = true, referencedColumnName = "id")
    private Accountability accountability;

    @NotAudited
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "transfer_id", nullable = true, referencedColumnName = "id")
    private Transfer transfer;

    @NotAudited
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "panelty_id", nullable = true, referencedColumnName = "id")
    private Panelty panelty;

    private Boolean intialJob;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    private Boolean isReward;
    private Boolean isPanelty;

    private Boolean jobBreak;
    
    @Column(columnDefinition = "TEXT")
    private String breakDetail;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true, referencedColumnName = "id")
    private EmployeeStatus status;
    
}
