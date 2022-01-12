package com.nsia.officems.odf.odf_proposal;

import java.util.List;
import java.util.Map;

import com.nsia.officems.odf.odf_proposal.dto.OdfProposalDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface OdfProposalService {
    public List<OdfProposal> findAll();
    public OdfProposal findById(Long id);
    public OdfProposal create(OdfProposalDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, OdfProposalDto dto);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public List<OdfProposal> findbyIdIn(List<Long> ids);
}
