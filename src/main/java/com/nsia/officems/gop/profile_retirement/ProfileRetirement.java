package com.nsia.officems.gop.profile_retirement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems.gop.employeeGrade.EmployeeGrade;
import com.nsia.officems.gop.employeeStatus.EmployeeStatus;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_retirement_type.ProfileRetirementType;

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
@Entity(name = "ProfileRetirement")
@Table(name = "profile_retirement")
public class ProfileRetirement extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_retirement_tbl_generator")
	@SequenceGenerator(name="profile_retirement_tbl_generator", sequenceName = "profile_retirement_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String maktubNumber;
    private String decreeNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date decreeDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date maktubDate;

    @ManyToOne
    @JoinColumn(name = "type", nullable = true, referencedColumnName = "id")
    private ProfileRetirementType type;

    @Column(columnDefinition = "TEXT")
    private String detail;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true, referencedColumnName = "id")
    private EmployeeStatus status;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "profile_job", nullable = true, referencedColumnName = "id")
    private ProfileJob profileJob;

    
}