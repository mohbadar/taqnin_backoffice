package com.nsia.officems.gop.publication.impl;

import com.nsia.officems.gop.publication.Publication;

import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.publication.PublicationRepository;
import com.nsia.officems.gop.publication.PublicationService;
import com.nsia.officems.gop.publication.dto.PublicationDto;
import com.nsia.officems.gop.publication.dto.PublicationMapperDto;
import com.nsia.officems.gop.publication_type.PublicationTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private PublicationTypeService publicationTypeService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    UserService userService;

    @Override
    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }

    @Override
    public List<Publication> findByProfile_id(Long id) {
        return publicationRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByMaktubDateDesc(id);
    }

    @Override
    public Publication findById(Long id) {
        Optional<Publication> optionalObj = publicationRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Publication create(PublicationDto dto) {
        Publication newPublication = new Publication();
        Publication publication = PublicationMapperDto.MapPublicationDto(newPublication, dto, profileService,
                publicationTypeService);
                publication.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!publication.equals(null)) {

            profileChecklistService.update(publication.getProfile().getId(), titles.getPublication());
            return publicationRepository.save(publication);
        }
        return null;
    }

    public Boolean update(Long id, PublicationDto dto) {
        Optional<Publication> objection = publicationRepository.findById(id);
        if (objection.isPresent()) {
            Publication publication = PublicationMapperDto.MapPublicationDto(objection.get(), dto, profileService,
                    publicationTypeService);
                    publication.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (!publication.equals(null)) {
                publicationRepository.save(publication);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Publication> publication = publicationRepository.findById(id);

        if (publication.isPresent()) {
            Publication publication2 = publication.get();
            publication2.setDeleted(true);
            publication2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            publicationRepository.save(publication2);
            return true;
        }

        return false;
    }

    
}
