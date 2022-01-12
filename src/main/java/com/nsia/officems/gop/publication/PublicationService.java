package com.nsia.officems.gop.publication;

import java.util.List;

import com.nsia.officems.gop.publication.dto.PublicationDto;

public interface PublicationService {
    public List<Publication> findAll();
    public Publication findById(Long id);
    public Publication create(PublicationDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, PublicationDto dto); 
    public List<Publication> findByProfile_id(Long id);
}
