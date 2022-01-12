package com.nsia.officems.gop.publication_type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationTypeRepository extends JpaRepository<PublicationType, Long> {
    public List<PublicationType> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

}
