package com.nsia.officems.gop.paneltyType.impl;

import com.nsia.officems.gop.paneltyType.PaneltyType;
import com.nsia.officems.gop.paneltyType.PaneltyTypeService;
import com.nsia.officems.gop.paneltyType.paneltyTypeRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class PaneltyTypeServiceImpl implements PaneltyTypeService{
    @Autowired
    private paneltyTypeRepository paneltyTypeRepository;

    @Override
    public List<PaneltyType> findAll() {
        return paneltyTypeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public PaneltyType findById(Long id) {
        Optional<PaneltyType> opionalObj = paneltyTypeRepository.findById(id);
        if (opionalObj.isPresent())
            return opionalObj.get();
        return null;
    }

    @Override
    public PaneltyType create(PaneltyType type) {
        return paneltyTypeRepository.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<PaneltyType> type = paneltyTypeRepository.findById(id);

        if (type.isPresent()) {
            PaneltyType type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            paneltyTypeRepository.save(type2);
            return true;
        }

        return false;
    }

    
}
