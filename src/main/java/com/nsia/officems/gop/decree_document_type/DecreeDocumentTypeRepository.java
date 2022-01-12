package com.nsia.officems.gop.decree_document_type;

import java.util.List;

import com.nsia.officems._util.LookupProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DecreeDocumentTypeRepository extends JpaRepository<DecreeDocumentType, Long> {

    @Query(nativeQuery = true, value = "SELECT ddt.id as id, ddt.name_en as name,ddt.code as code from decree_document_type ddt")
    List<LookupProjection> getByNameEn();

    @Query(nativeQuery = true, value = "SELECT ddt.id as id, ddt.name_ps as name,ddt.code as code from decree_document_type ddt")
    List<LookupProjection> getByNamePs();

    @Query(nativeQuery = true, value = "SELECT ddt.id as id, ddt.name_dr as name,ddt.code as code from decree_document_type ddt")
    List<LookupProjection> getByNameFa();
}
