package com.nsia.officems.gop.publication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    public List<Publication> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<Publication> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByMaktubDateDesc(Long id);

}
