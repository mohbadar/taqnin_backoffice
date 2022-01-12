package com.nsia.officems.odf.odf_follow_up.impl;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems.odf.odf_follow_up.OdfFollowUp;
import com.nsia.officems.odf.odf_follow_up.OdfFollowUpRepository;
import com.nsia.officems.odf.odf_follow_up.OdfFollowUpService;
import com.nsia.officems.odf.odf_follow_up.dto.OdfFollowUpDto;
import com.nsia.officems.odf.odf_follow_up.dto.OdfFollowUpMapper;
import com.nsia.officems.odf.odf_follow_up_type.OdfFollowUpTypeService;
import com.nsia.officems.odf.odf_order.OdfOrderService;

import org.springframework.stereotype.Service;

@Service
public class OdfFollowUpServiceImpl implements OdfFollowUpService{
    @Autowired
    private OdfFollowUpRepository repo;

    @Autowired
    private OdfFollowUpTypeService typeService;

    @Autowired
    private OdfOrderService orderService;

    @Override
    public List<OdfFollowUp> findAll() {
        return repo.findAll();
    }

    public List<OdfFollowUp> findByOrder_id(Long id){
        return repo.findByOrder_idAndDeletedIsFalseOrDeletedIsNullOrderByDateDesc(id);
    }

    public List getFollowUpCountByType(Long id){
        return repo.getFollowUpCountByType(id);
    }


    @Override
    public OdfFollowUp findById(Long id) {
        Optional<OdfFollowUp> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public OdfFollowUp create(OdfFollowUpDto dto) {
        OdfFollowUp newFollow = new OdfFollowUp();
        OdfFollowUp follow = OdfFollowUpMapper.MapProposalDto(newFollow, dto, orderService, typeService);
        if (!follow.equals(null)) {
            return repo.save(follow);
        }
        return null;
    }

    @Override
    public Boolean update(Long id, OdfFollowUpDto dto) {
        Optional<OdfFollowUp> objection = repo.findById(id);
        if (objection.isPresent()) {
            OdfFollowUp follow = OdfFollowUpMapper.MapProposalDto(objection.get(), dto, orderService, typeService);
            if (!follow.equals(null)) {
                repo.save(follow);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<OdfFollowUp> pOptional = repo.findById(id);

        if (pOptional.isPresent()) {
            OdfFollowUp proposal = pOptional.get();
            proposal.setDeleted(true);
            proposal.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(proposal);
            return true;
        }

        return false;
    }


}
