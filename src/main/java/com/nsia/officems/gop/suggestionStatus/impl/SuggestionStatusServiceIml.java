package com.nsia.officems.gop.suggestionStatus.impl;

import java.util.List;

import com.nsia.officems._util.LookupProjection;
import com.nsia.officems.gop.suggestionStatus.SuggestionStatus;
import com.nsia.officems.gop.suggestionStatus.SuggestionStatusRepository;
import com.nsia.officems.gop.suggestionStatus.SuggestionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestionStatusServiceIml implements SuggestionStatusService {

    @Autowired
    private SuggestionStatusRepository suggestionStatusRepository;

    @Override
    public List<LookupProjection> findAll() {
        String lang = "dr";

        switch (lang) {
        case "en":
            return suggestionStatusRepository.getByNameEn();
        case "dr":
            return suggestionStatusRepository.getByNameFa();
        case "ps":
            return suggestionStatusRepository.getByNamePs();
        default:
            return suggestionStatusRepository.getByNameEn();
        }
    }

    @Override
    public SuggestionStatus findById(Long id) {
        return suggestionStatusRepository.findById(id).get();
    }
}
