package com.nsia.officems.gop.profile_training;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._admin.country.Country;
import com.nsia.officems.infrastructure.base.BaseEntity;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.nsia.officems.gop.profile.Profile;
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

@Audited
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Training")
@Table(name = "training")
public class Training extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "raining_tbl_generator")
	@SequenceGenerator(name="raining_tbl_generator", sequenceName = "raining_tbl_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    private String type;
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    private String seminarType;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "province", nullable = true, referencedColumnName = "id")
    private Province province;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "country", nullable = true, referencedColumnName = "id")
    private Country country;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = true, referencedColumnName = "id")
    private Profile profile;
    
}
