package com.nsia.officems.gop.promotion_type;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.swing.text.Position;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.promotion.Promotion;
import com.nsia.officems.gop.proposal.Proposal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "PromotionType")
@Table(name = "promotion_type")
public class PromotionType {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_type_tbl_generator")
	@SequenceGenerator(name="promotion_type_tbl_generator", sequenceName = "promotion_type_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;
	
	@Column
	private String namePs;
	@Column
	private String nameDr;
	@Column
	private String nameEn;

	@OneToMany(mappedBy = "promotionType" , fetch = FetchType.LAZY)
    @JsonIgnore
	private Collection<Promotion> promotions;
	
	
	
	

	
}