package com.nsia.officems.gop.suggestionStatus;

import java.util.List;

import com.nsia.officems._util.LookupProjection;
public interface SuggestionStatusService {
    public List<LookupProjection> findAll();

    public SuggestionStatus findById(Long id);
}
