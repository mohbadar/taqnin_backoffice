package com.nsia.officems.gop.publication.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.publication.Publication;
import com.nsia.officems.gop.publication_type.PublicationTypeService;

public class PublicationMapperDto {
    public static Publication MapPublicationDto(Publication publication, PublicationDto dto,
     ProfileService profileService, PublicationTypeService publicationTypeService){ 
        DateTimeChange changeDate = new DateTimeChange();
        try{
            publication.setActive(true);
            publication.setType(dto.getType() == null? null: publicationTypeService.findById(dto.getType()));
            publication.setTitle(dto.getTitle() == null? null: dto.getTitle());
            publication.setPublishDate(dto.getPublishDate() == null? null : changeDate.convertPersianDateToGregorianDate(dto.getPublishDate()));
            publication.setApprovedAuthority(dto.getApprovedAuthority() == null? null : dto.getApprovedAuthority());
            publication.setMaktubShumara(dto.getMaktubShumara() == null? null: dto.getMaktubShumara());
            publication.setMaktubDate(dto.getMaktubDate() == null? null:changeDate.convertPersianDateToGregorianDate(dto.getMaktubDate()));
            publication.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            return publication;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
    
}
