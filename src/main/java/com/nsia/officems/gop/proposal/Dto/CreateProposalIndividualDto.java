package com.nsia.officems.gop.proposal.Dto;

import java.util.List;

import com.nsia.officems.gop.suggestionSubject.SuggestionSubject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProposalIndividualDto {
	private Long id;
	private Long profileId;
	private String firstName;
	private String lastName;
	private String fatherName;
	private List<SuggestionSubject> suggestionSubjects;
	private Boolean deleted;
}
