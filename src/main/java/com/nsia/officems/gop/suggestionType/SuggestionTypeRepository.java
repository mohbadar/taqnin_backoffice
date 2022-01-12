package com.nsia.officems.gop.suggestionType;

import java.util.List;

import com.nsia.officems._util.LookupProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SuggestionTypeRepository  extends JpaRepository<SuggestionType, Long>{
    @Query(nativeQuery = true, value = "SELECT st.id as id, st.name_en as name, st.code as code from suggestion_type st")
    List<LookupProjection> getByNameEn();

    @Query(nativeQuery = true, value = "SELECT st.id as id, st.name_ps as name, st.code as code from  suggestion_type st")
    List<LookupProjection> getByNamePs();

    @Query(nativeQuery = true, value = "SELECT st.id as id, st.name_dr as name , st.code as code from  suggestion_type st")
    List<LookupProjection> getByNameFa();
    
}
