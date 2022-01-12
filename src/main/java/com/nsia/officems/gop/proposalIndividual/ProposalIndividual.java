package com.nsia.officems.gop.proposalIndividual;

import java.util.Collection;
import java.util.HashSet;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems.infrastructure.base.BaseEntity;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.proposal.Proposal;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubject;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Where(clause = "deleted is not true")
@Entity(name = "proposalIndividual")
@Table(name = "proposal_individual")
public class ProposalIndividual extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "temporary_profile_tbl_generator")
	@SequenceGenerator(name = "temporary_profile_tbl_generator", sequenceName = "temporary_profile_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String fatherName;
	@Column
	private String suggestionNo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "proposal_individual_suggestion_subject", joinColumns = @JoinColumn(name = "proposal_individual_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "suggestion_subject_id", referencedColumnName = "id"))
	private Collection<SuggestionSubject> suggestionSubjects = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proposal_id", referencedColumnName = "id", nullable = false)
	private Proposal proposal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = true)
	private Profile profile;

}
