package com.nsia.officems.gop.suggestionStatus;

import java.util.List;

import com.nsia.officems._util.LookupProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SuggestionStatusRepository  extends JpaRepository<SuggestionStatus, Long>{
    @Query(nativeQuery = true, value = "SELECT sst.id as id, sst.name_en as name, sst.code as code from suggestion_status sst")
    List<LookupProjection> getByNameEn();

    @Query(nativeQuery = true, value = "SELECT sst.id as id, pdt.name_ps as name, sst.code as code from  suggestion_status sst")
    List<LookupProjection> getByNamePs();

    @Query(nativeQuery = true, value = "SELECT sst.id as id, sst.name_dr as name, sst.code as code from  suggestion_status sst")
    List<LookupProjection> getByNameFa(); 
}
