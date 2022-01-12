package com.nsia.officems._admin.country;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems._admin.province.Province;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Country")
@Table(name = "country")
public class Country extends BaseEntity{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_tbl_generator")
	@SequenceGenerator(name="country_tbl_generator", sequenceName = "country_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;
	
	@Column
	private String namePs;
	@Column
	private String nameDr;
	@Column
	private String nameEn;

	@Column( nullable =  true)
	private String nationality;
	
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    @JsonIgnore
	private Collection<Province> provinces;
	
}
