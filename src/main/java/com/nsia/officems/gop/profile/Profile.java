package com.nsia.officems.gop.profile;

import java.time.LocalDateTime;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.authority.Authority;
import com.nsia.officems._admin.commission.Commission;
import com.nsia.officems._admin.country.Country;
import com.nsia.officems._admin.district.District;
import com.nsia.officems.gop.employeeGrade.EmployeeGrade;
import com.nsia.officems.gop.employeePosition.EmployeePosition;
import com.nsia.officems.gop.employeeStatus.EmployeeStatus;
import com.nsia.officems.gop.employee_militrary_grade.EmployeeMilitaryGrade;
import com.nsia.officems.gop.ethnic.Ethnic;
import com.nsia.officems.gop.gender.Gender;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.language.Language;
import com.nsia.officems._admin.ministry.Ministry;
import com.nsia.officems.gop.national_language.NationalLanguage;
import com.nsia.officems.gop.nationality.Nationality;
import com.nsia.officems._admin.province.Province;
import com.nsia.officems.gop.religion.Religion;
import com.nsia.officems.gop.sect.Sect;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author HPardess
 */
@Audited
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile")
@Entity(name = "Profile")
public class Profile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_tbl_generator")
    @SequenceGenerator(name = "profile_tbl_generator", sequenceName = "profile_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;
    private String profileCode;

    private String firstName;
    private String lastName;
    private String fatherName;
    private String grandFatherName;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "ethnic", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Ethnic ethnic;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "nationality", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Nationality nationality;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "religion", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Religion religion;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "sect", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Sect sect;

    private String blood;
    private String dob;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dobGregorian;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "birth_country", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Country birthCountry;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "birth_province", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Province birthProvince;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "birth_district", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private District birthDistrict;

    private String birthVillage;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "original_country", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Country originalCountry;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "original_province", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Province originalProvince;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "original_district", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private District originalDistrict;

    private String originalVillage;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "current_country", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Country currentCountry;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "current_province", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Province currentProvince;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "current_district", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private District currentDistrict;

    private String currentVillage;
    private String tazkiraNumber;
    private String tazkiraTog;
    private String tazkiraRegister;
    private String tazkiraPage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date tazkiraDate;
    private String tazkiraPlace;
    private String enid;

    @NotAudited
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "profile_language",
         joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"), 
         inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"))
    @JsonIgnore
    private Collection<Language> language;

    @NotAudited
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "profile_national_language",
         joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"), 
         inverseJoinColumns = @JoinColumn(name = "national_language_id", referencedColumnName = "id"))
    @JsonIgnore
    private Collection<NationalLanguage> nationalLanguages;

    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date year;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date appointDate;
    private String decreeNumber;
    private String positionTitle;

    private String appointType;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "employee_grade", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private EmployeeGrade grade;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "military_grade", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private EmployeeMilitaryGrade militaryGrade;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "employee_position", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private EmployeePosition position;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "military_position", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private EmployeeMilitaryGrade militaryPosition;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "employee_status", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private EmployeeStatus status;

    private Integer qadamYear;
    private Integer qadamMonth;
    private Integer qadamDay;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "first_ministry", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Ministry ministry;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "first_authority", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Authority authority;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "first_commission", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Commission commission;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "gender", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Gender gender;

    private String suggestionNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date suggestionDate;
    private String avatar;

    private Boolean disable;
    private Boolean approve;

}
