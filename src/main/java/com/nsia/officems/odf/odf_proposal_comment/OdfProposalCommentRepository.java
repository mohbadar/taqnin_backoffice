package com.nsia.officems.odf.odf_proposal_comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdfProposalCommentRepository extends JpaRepository<OdfProposalComment, Long>{
    public List<OdfProposalComment> findByProposal_idAndDeletedIsFalseOrDeletedIsNull(Long id);
}
