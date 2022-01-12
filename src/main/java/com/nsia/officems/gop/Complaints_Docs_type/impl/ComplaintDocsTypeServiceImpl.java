package com.nsia.officems.gop.Complaints_Docs_type.impl;

import com.nsia.officems.gop.Complaints_Docs_type.ComplaintDocsType;
import com.nsia.officems.gop.Complaints_Docs_type.ComplaintDocsTypeRepository;
import com.nsia.officems.gop.Complaints_Docs_type.ComplaintDocsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintDocsTypeServiceImpl implements ComplaintDocsTypeService {

    @Autowired
    private ComplaintDocsTypeRepository complaintDocsTypeRepository;

    @Override
    public List<ComplaintDocsType> findAll() {
        return complaintDocsTypeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public ComplaintDocsType findById(Long id) {
        Optional<ComplaintDocsType> optionalObj = complaintDocsTypeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ComplaintDocsType create(ComplaintDocsType level) {
        return complaintDocsTypeRepository.save(level);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<ComplaintDocsType> type = complaintDocsTypeRepository.findById(id);

        if (type.isPresent()) {
            ComplaintDocsType type2 = type.get();
            type2.setDeleted(true);
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            complaintDocsTypeRepository.save(type2);
            return true;
        }

        return false;
    }

}
