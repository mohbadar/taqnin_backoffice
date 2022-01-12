package com.nsia.officems.gop.national_language;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.profile.Profile;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Entity(name = "NationalLanguage")
@Table(name = "national_language")
public class NationalLanguage extends BaseEntity{

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "national_language_tbl_generator")
	@SequenceGenerator(name="national_language_tbl_generator", sequenceName = "national_language_tbl_seq", allocationSize = 1)
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
    
    @ManyToMany(mappedBy = "nationalLanguages")
	@JsonIgnore
	private Collection<Profile> profile;
    
}
