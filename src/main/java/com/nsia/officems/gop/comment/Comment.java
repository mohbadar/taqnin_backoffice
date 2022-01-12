package com.nsia.officems.gop.comment;

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
import com.nsia.officems.gop.proposal.Proposal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Comment")
@Table(name = "comment")
public class Comment  extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_tbl_generator")
	@SequenceGenerator(name="comment_tbl_generator", sequenceName = "comment_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
    private Long id;
    
	
	@Column
	private String namePs;
	@Column
	private String nameDr;
	@Column
	private String nameEn;

	@OneToMany(mappedBy = "comment" , fetch = FetchType.LAZY)
    @JsonIgnore
	private Collection<Proposal> proposal;
}
