package com.nsia.officems.gop.profile_travel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._admin.country.Country;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems._admin.province.Province;

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
@Entity(name = "Travel")
@Table(name = "travel")
public class Travel extends BaseEntity{

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travel_tbl_generator")
	@SequenceGenerator(name="travel_tbl_generator", sequenceName = "travel_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;
    private String type;
    private String MaktubNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private String purpose;

    @ManyToOne
    @JoinColumn(name = "province", nullable = true, referencedColumnName = "id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "country", nullable = true, referencedColumnName = "id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "profile_job", nullable = true, referencedColumnName = "id")
    private ProfileJob profileJob;
    
}
