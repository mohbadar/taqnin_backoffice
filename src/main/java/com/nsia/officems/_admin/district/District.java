package com.nsia.officems._admin.district;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name = "District")
@Table(name = "district")
public class District extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_tbl_generator")
	@SequenceGenerator(name="district_tbl_generator", sequenceName = "district_tbl_seq", allocationSize = 1)
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

	private String province_code;
	

	@ManyToOne
	@JoinColumn(name = "province_id", nullable = false, referencedColumnName = "id")
	private Province province;

	// @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    // @JsonIgnore
	// private Collection<Village> villages;
}
