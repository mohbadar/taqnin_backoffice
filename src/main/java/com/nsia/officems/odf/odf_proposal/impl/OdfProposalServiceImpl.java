package com.nsia.officems.odf.odf_proposal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._admin.shura.ShuraService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreementService;
import com.nsia.officems.odf.odf_proposal.OdfProposal;
import com.nsia.officems.odf.odf_proposal.OdfProposalRepository;
import com.nsia.officems.odf.odf_proposal.OdfProposalService;
import com.nsia.officems.odf.odf_proposal.dto.OdfProposalDto;
import com.nsia.officems.odf.odf_proposal.dto.OdfProposalMapper;

import org.springframework.stereotype.Service;

@Service
public class OdfProposalServiceImpl implements OdfProposalService {
    @Autowired
    private OdfProposalRepository odfProposalRepository;

    @Autowired
    private OdfAuthorityAgreementService odfAuthorityAgreementService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private ShuraService shuraService;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "left join public.shura s on s.id=pro.shura_id";
        // To have first AND with no error
        String whereClause = " pro.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.odf_proposal pro", null, joinClause, whereClause, groupByClause,
                input);
    }

    @Override
    public List<OdfProposal> findAll() {
        return odfProposalRepository.findAll();
    }

    @Override
    public OdfProposal findById(Long id) {
        Optional<OdfProposal> optionalObj = odfProposalRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public List<OdfProposal> findbyIdIn(List<Long> ids){
        return odfProposalRepository.findByIdIn(ids);
    }

    @Override
    public OdfProposal create(OdfProposalDto dto) {
        OdfProposal newProposal = new OdfProposal();
        OdfProposal proposal = OdfProposalMapper.MapProposalDto(newProposal, dto, odfAuthorityAgreementService,
                departmentService, shuraService);
        if (!proposal.equals(null)) {
            return odfProposalRepository.save(proposal);
        }
        return null;
    }

    @Override
    public Boolean update(Long id, OdfProposalDto dto) {
        Optional<OdfProposal> objection = odfProposalRepository.findById(id);
        if (objection.isPresent()) {
            OdfProposal proposal = OdfProposalMapper.MapProposalDto(objection.get(), dto, odfAuthorityAgreementService,
                    departmentService, shuraService);
            if (!proposal.equals(null)) {
                odfProposalRepository.save(proposal);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<OdfProposal> pOptional = odfProposalRepository.findById(id);

        if (pOptional.isPresent()) {
            OdfProposal proposal = pOptional.get();
            proposal.setDeleted(true);
            proposal.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            odfProposalRepository.save(proposal);
            return true;
        }

        return false;
    }
}
