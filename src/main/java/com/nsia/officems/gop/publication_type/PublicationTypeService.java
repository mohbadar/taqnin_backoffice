package com.nsia.officems.gop.publication_type;

import java.util.List;

public interface PublicationTypeService {
    public List<PublicationType> findAll();
    public PublicationType findById(Long id);
    public PublicationType create(PublicationType type);
    public Boolean delete(Long id);
}
