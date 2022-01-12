package com.nsia.officems.odf.odf_agenda.odf_agenda_topic.impl;

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
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.gop.proposal.ProposalService;
import com.nsia.officems.odf.odf_authority_agreement.OdfAuthorityAgreementService;
import com.nsia.officems.odf.odf_presenters.OdfPresenterService;
import com.nsia.officems.odf.odf_proposal.OdfProposalService;
import com.nsia.officems.odf.odf_agenda.OdfAgendaService;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopic;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopicRepository;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.OdfAgendaTopicService;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.dto.OdfAgendaTopicDto;
import com.nsia.officems.odf.odf_agenda.odf_agenda_topic.dto.OdfAgendaTopicMapper;

import org.springframework.stereotype.Service;

@Service
public class OdfAgendaTopicServiceImpl implements OdfAgendaTopicService {
    @Autowired
    private OdfAgendaTopicRepository odfAgendaTopicRepository;

    @Autowired
    private OdfAuthorityAgreementService odfAuthorityAgreementService;

    @Autowired
    private OdfProposalService proposalService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private OdfPresenterService odfPresenterService;

    @Autowired
    private OdfAgendaService odfAgendaService;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = " agt.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.odf_agenda_topic agt", null, joinClause, whereClause, groupByClause,
                input);
    }

    @Override
    public List<OdfAgendaTopic> findAll() {
        return odfAgendaTopicRepository.findAll();
    }

    @Override
    public OdfAgendaTopic findById(Long id) {
        Optional<OdfAgendaTopic> optionalObj = odfAgendaTopicRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public List<OdfAgendaTopic> findbyIdIn(List<Long> ids) {
        return odfAgendaTopicRepository.findByIdIn(ids);
    }

    @Override
    public OdfAgendaTopic create(OdfAgendaTopic odfAgendaTopic) {
        odfAgendaTopic.setDeleted(false);
        return odfAgendaTopicRepository.save(odfAgendaTopic);
    }

    @Override
    public Boolean update(Long id, OdfAgendaTopicDto dto) {
        Optional<OdfAgendaTopic> odfAgendaTopic = odfAgendaTopicRepository.findById(id);
        if (odfAgendaTopic.isPresent()) {
            OdfAgendaTopic agendaTopic = OdfAgendaTopicMapper.MapAgendaTopicDto(odfAgendaTopic.get(), dto,
                    odfAgendaService, proposalService, odfAgendaTopic.get().getAgenda().getId());
            if (!agendaTopic.equals(null)) {
                odfAgendaTopicRepository.save(agendaTopic);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<OdfAgendaTopic> pOptional = odfAgendaTopicRepository.findById(id);

        if (pOptional.isPresent()) {
            OdfAgendaTopic agendaTopic = pOptional.get();
            agendaTopic.setDeleted(true);
            agendaTopic.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            odfAgendaTopicRepository.save(agendaTopic);
            return true;
        }

        return false;
    }

    @Override
    public List<OdfAgendaTopic> findByAgendaId(Long agendaId) {
        return odfAgendaTopicRepository.findByAgenda_id(agendaId);
    }

    // @Override
    // public List<OdfAgendaTopic> findByAgendaId(Long id) {
    // return odfAgendaTopicRepository.findByAgenda_id(id);
    // }
}
