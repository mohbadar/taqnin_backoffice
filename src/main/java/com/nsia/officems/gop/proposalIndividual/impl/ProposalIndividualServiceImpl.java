package com.nsia.officems.gop.proposalIndividual.impl;

import java.util.List;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.proposal.Proposal;
import com.nsia.officems.gop.proposalIndividual.ProposalIndividual;
import com.nsia.officems.gop.proposalIndividual.ProposalIndividualRepository;
import com.nsia.officems.gop.proposalIndividual.ProposalIndividualService;
import com.nsia.officems.gop.proposalIndividual.Dto.ProposalIndividualDto;
import com.nsia.officems.gop.proposalIndividual.Dto.ProposalIndividualDtoMapper;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalIndividualServiceImpl implements ProposalIndividualService {

    @Autowired
    private ProposalIndividualRepository tempRepository;
    @Autowired
    private SuggestionSubjectService suggestionSubject;

    @Autowired
    private ProfileService profileService;

    @Override
    public List<ProposalIndividual> findAll() {
        return tempRepository.findAll();
    }

    @Override
    public ProposalIndividual create(ProposalIndividualDto[] dto, Proposal prop) {
        ProposalIndividual mapper = null;
        if (dto.length > 0) {
            for (int i = 0; i < dto.length; i++) {
                mapper = ProposalIndividualDtoMapper.MapProposalDto(dto[i], profileService, new ProposalIndividual());
                mapper.setProposal(prop);
                // mapper.setCreatedBy(userService.getLoggedInUser().getUsername());
                // mapper.setEnvSlug(userAuthService.getCurrentEnv());

                if (!mapper.equals(null)) {

                    this.tempRepository.save(mapper);
                }
            }
        }

        return mapper;
    }

    @Override
    public ProposalIndividual update(ProposalIndividualDto[] dto, Proposal prop) {
        ProposalIndividual mapper = null;
        Optional<ProposalIndividual> optionalPI;
        if (dto.length > 0) {
            for (int i = 0; i < dto.length; i++) {
                ProposalIndividual indProp = new ProposalIndividual();
                if(dto[i].getId() !=null){
                    optionalPI = tempRepository.findById(dto[i].getId());
                    if(optionalPI.isPresent()){
                        indProp = optionalPI.get();
                    }

                    if(dto[i].getDeleted() == true){
                        indProp.setDeleted(true);
                    }
                }
                
                mapper = ProposalIndividualDtoMapper.MapProposalDto(dto[i], profileService, indProp);
                mapper.setProposal(prop);
                // mapper.setCreatedBy(userService.getLoggedInUser().getUsername());
                // mapper.setEnvSlug(userAuthService.getCurrentEnv());

                if (!mapper.equals(null)) {

                    this.tempRepository.save(mapper);
                }
            }
        }

        return mapper;
    }
    @Override
    public List<ProposalIndividual> findByProposalId(Proposal proposal){
        return tempRepository.findByProposal(proposal);
    }
}
