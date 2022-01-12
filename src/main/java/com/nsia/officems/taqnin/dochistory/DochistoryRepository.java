package com.nsia.officems.taqnin.dochistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DochistoryRepository extends JpaRepository<Dochistory,Long> {
    public List<Dochistory> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<Dochistory> findByDocument_idAndDeletedFalse(Long id);

}