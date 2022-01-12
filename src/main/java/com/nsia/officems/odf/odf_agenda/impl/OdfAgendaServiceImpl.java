package com.nsia.officems.odf.odf_agenda.impl;

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
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreementService;
import com.nsia.officems.odf.odf_presenters.OdfPresenterService;
import com.nsia.officems.odf.odf_proposal.OdfProposalService;
import com.nsia.officems.odf.odf_agenda.OdfAgenda;
import com.nsia.officems.odf.odf_agenda.OdfAgendaRepository;
import com.nsia.officems.odf.odf_agenda.OdfAgendaService;
import com.nsia.officems.odf.odf_agenda.dto.OdfAgendaDto;
import com.nsia.officems.odf.odf_agenda.dto.OdfAgendaMapper;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopicService;

import org.springframework.stereotype.Service;

@Service
public class OdfAgendaServiceImpl implements OdfAgendaService {
    @Autowired
    private OdfAgendaRepository odfAgendaRepository;

    @Autowired
    private OdfAgendaService odfAgendaService;

    @Autowired
    private OdfAgendaTopicService odfAgendaTopicService;

    @Autowired
    private OdfAuthorityAgreementService odfAuthorityAgreementService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private OdfProposalService odfProposalService;

    @Autowired
    private OdfPresenterService odfPresenterService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private UserService userService;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = " ag.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.odf_agenda ag", null, joinClause, whereClause, groupByClause, input);
    }

    @Override
    public List<OdfAgenda> findAll() {
        return odfAgendaRepository.findAll();
    }

    @Override
    public OdfAgenda findById(Long id) {
        Optional<OdfAgenda> optionalObj = odfAgendaRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public List<OdfAgenda> findbyIdIn(List<Long> ids) {
        return odfAgendaRepository.findByIdIn(ids);
    }

    @Override
    public OdfAgenda create(OdfAgenda odfAgenda) {
        odfAgenda.setDeleted(false);
        if (!odfAgenda.equals(null)) {
            odfAgenda.setStatus("DRAFT");
        }
        return odfAgendaRepository.save(odfAgenda);
    }

    @Override
    public Boolean update(Long id, OdfAgendaDto dto) {
        Optional<OdfAgenda> odfAgenda = odfAgendaRepository.findById(id);
        if (odfAgenda.isPresent()) {
            OdfAgenda odfAgendaToBeUpdated = odfAgenda.get();
            OdfAgendaMapper.MapAgendaDto(odfAgendaToBeUpdated, dto, odfAgendaService, odfAgendaTopicService,
                    odfProposalService, odfPresenterService);
            odfAgendaToBeUpdated.setUpdatedBy(userService.getLoggedInUser().getUsername());
            odfAgendaToBeUpdated.setUpdatedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            odfAgendaRepository.save(odfAgendaToBeUpdated);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<OdfAgenda> pOptional = odfAgendaRepository.findById(id);

        if (pOptional.isPresent()) {
            OdfAgenda agenda = pOptional.get();
            agenda.setDeleted(true);
            agenda.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            odfAgendaRepository.save(agenda);
            return true;
        }

        return false;
    }

}
