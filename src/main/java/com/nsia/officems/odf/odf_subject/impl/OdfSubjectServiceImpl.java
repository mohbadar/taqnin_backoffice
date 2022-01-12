package com.nsia.officems.odf.odf_subject.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.shura.ShuraService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.odf.odf_proposal.dto.OdfProposalMapper;
import com.nsia.officems.odf.odf_resolution.OdfResolution;
import com.nsia.officems.odf.odf_resolution.OdfResolutionRepository;
import com.nsia.officems.odf.odf_resolution.OdfResolutionService;
import com.nsia.officems.odf.odf_resolution.dto.OdfResolutionDto;
import com.nsia.officems.odf.odf_resolution.dto.OdfResolutionMapper;
import com.nsia.officems.odf.odf_subject.OdfSubject;
import com.nsia.officems.odf.odf_subject.OdfSubjectRepository;
import com.nsia.officems.odf.odf_subject.OdfSubjectService;
import com.nsia.officems.odf.odf_subject.dto.OdfSubjectDto;
import com.nsia.officems.odf.odf_subject.dto.OdfSubjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class OdfSubjectServiceImpl implements OdfSubjectService {

    @Autowired
    private OdfSubjectRepository odfSubjectRepository;

    @Autowired
    private OdfResolutionService odfResolutionService;

    @Autowired
    private UserService userService;

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OdfSubject> findAll() {
        return odfSubjectRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public OdfSubject findById(Long id) {
        Optional<OdfSubject> optionalObj = odfSubjectRepository.findById(id);
        if (optionalObj.isPresent()) {
            return optionalObj.get();
        }
        return null;
    }

    @Override
    public OdfSubject create(OdfSubject subject) {
        subject.setDeleted(false);
        return odfSubjectRepository.save(subject);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<OdfSubject> subjectToBeDeleted = odfSubjectRepository.findById(id);

        if (subjectToBeDeleted.isPresent()) {
            OdfSubject getSubjectToBeDeleted = subjectToBeDeleted.get();
            getSubjectToBeDeleted.setDeleted(true);
            getSubjectToBeDeleted.setDeletedBy(userService.getLoggedInUser().getUsername());
            getSubjectToBeDeleted.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            odfSubjectRepository.save(getSubjectToBeDeleted);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, OdfSubjectDto odfSubjectDto) {
        Optional<OdfSubject> subjectToBeUpdated = odfSubjectRepository.findById(id);
        if (subjectToBeUpdated.isPresent()) {
            OdfSubject getSubjectToBeUpdated = subjectToBeUpdated.get();
            OdfSubjectMapper.MapSubjectDto(getSubjectToBeUpdated, odfSubjectDto, odfResolutionService);
            getSubjectToBeUpdated.setUpdatedBy(userService.getLoggedInUser().getUsername());
            getSubjectToBeUpdated.setUpdatedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            odfSubjectRepository.save(getSubjectToBeUpdated);
            return true;
        }
        return false;
    }

    @Override
    public List<OdfSubject> findByResolutionId(Long id) {
        return odfSubjectRepository.findByResolution_id(id);
    }

}
