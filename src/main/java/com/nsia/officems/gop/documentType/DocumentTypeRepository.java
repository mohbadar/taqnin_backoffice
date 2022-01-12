package com.nsia.officems.gop.documentType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.nsia.officems._util.LookupProjection;

public interface DocumentTypeRepository  extends JpaRepository<DocumentType, Long>{
    
    @Query(nativeQuery = true, value = "SELECT pdt.id as id, pdt.name_en as name from document_type pdt")
    List<LookupProjection> getByNameEn();

    @Query(nativeQuery = true, value = "SELECT pdt.id as id, pdt.name_ps as name from  document_type pdt")
    List<LookupProjection> getByNamePs();

    @Query(nativeQuery = true, value = "SELECT pdt.id as id, pdt.name_dr as name from  document_type pdt")
    List<LookupProjection> getByNameFa();
}
