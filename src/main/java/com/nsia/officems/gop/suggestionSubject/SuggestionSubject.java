package com.nsia.officems.gop.suggestionSubject;


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
import com.nsia.officems._admin.country.Country;
import com.nsia.officems._admin.district.District;
import com.nsia.officems.infrastructure.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "suggestionSubject")
@Table(name = "suggestion_subject")
public class SuggestionSubject {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suggestion_subject_tbl_generator")
	@SequenceGenerator(name="suggestion_subject_tbl_generator", sequenceName = "suggestion_subject_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;
	
	@Column
	private String namePs;
	@Column
	private String nameDr;
	@Column
	private String nameEn;
	@Column
	private String code;

}
