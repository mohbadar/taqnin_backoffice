package com.nsia.officems.gop.proposal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long>, RevisionRepository<Proposal, Long, Integer> {

    Proposal findByProposalNumber(String proposalNumber);

    @Query(value = "select count(*) from public.proposal where deleted is not true", nativeQuery = true)
    public long count();
}