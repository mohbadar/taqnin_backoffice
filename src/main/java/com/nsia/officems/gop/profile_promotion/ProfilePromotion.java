package com.nsia.officems.gop.profile_promotion;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems.gop.employeeGrade.EmployeeGrade;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGrade;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_promotion_type.ProfilePromotionType;

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
@Entity(name = "ProfilePromotion")
@Table(name = "profile_promotion")
public class ProfilePromotion extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_promotion_tbl_generator")
	@SequenceGenerator(name="profile_promotion_tbl_generator", sequenceName = "profile_promotion_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String maktubNumber;

    private Integer qadamYear;
    private Integer qadamMonth;
    private Integer qadamDay;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date maktubDate;

    @ManyToOne
    @JoinColumn(name = "old_grade", nullable = true, referencedColumnName = "id")
    private EmployeeGrade oldGrade;

    @ManyToOne
    @JoinColumn(name = "old_military_grade", nullable = true, referencedColumnName = "id")
    private EmployeeMilitaryGrade oldMilitaryGrade;

    @ManyToOne
    @JoinColumn(name = "new_grade", nullable = true, referencedColumnName = "id")
    private EmployeeGrade newGrade;

    @ManyToOne
    @JoinColumn(name = "new_military_grade", nullable = true, referencedColumnName = "id")
    private EmployeeMilitaryGrade newMilitaryGrade;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "type", nullable = true, referencedColumnName = "id")
    private ProfilePromotionType type;

    @ManyToOne
    @JoinColumn(name = "profile_job", nullable = true, referencedColumnName = "id")
    private ProfileJob profileJob;

    private Boolean intialJob;

}
