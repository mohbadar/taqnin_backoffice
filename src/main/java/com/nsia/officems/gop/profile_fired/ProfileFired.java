package com.nsia.officems.gop.profile_fired;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems.gop.employeeStatus.EmployeeStatus;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile_fired_type.ProfileFiredType;
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
@Entity(name = "ProfileFired")
@Table(name = "profile_fired")
public class ProfileFired extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_fired_tbl_generator")
	@SequenceGenerator(name="profile_fired_tbl_generator", sequenceName = "profile_fired_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String maktubNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date maktubDate;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true, referencedColumnName = "id")
    private EmployeeStatus status;


    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = true, referencedColumnName = "id")
    private ProfileFiredType type;

    @ManyToOne
    @JoinColumn(name = "profile_job", nullable = true, referencedColumnName = "id")
    private ProfileJob profileJob;
    
}
