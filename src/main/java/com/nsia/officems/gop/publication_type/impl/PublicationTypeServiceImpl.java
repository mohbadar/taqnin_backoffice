package com.nsia.officems.gop.publication_type.impl;

import java.util.List;
import java.util.Optional;
import com.nsia.officems.gop.publication_type.PublicationType;
import com.nsia.officems.gop.publication_type.PublicationTypeRepository;
import com.nsia.officems.gop.publication_type.PublicationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class PublicationTypeServiceImpl implements PublicationTypeService{
    @Autowired
    private PublicationTypeRepository publicationTypeRepository;

    @Override
    public List<PublicationType> findAll() {
        return publicationTypeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public PublicationType findById(Long id) {
        Optional<PublicationType> optionalObj = publicationTypeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public PublicationType create(PublicationType type) {
        return publicationTypeRepository.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<PublicationType> type = publicationTypeRepository.findById(id);

        if (type.isPresent()) {
            PublicationType type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            publicationTypeRepository.save(type2);
            return true;
        }

        return false;
    }
}
