package com.nsia.officems.gop.suggestionType.impl;

import java.util.List;

import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems._util.LookupProjection;
import com.nsia.officems.gop.suggestionType.SuggestionType;
import com.nsia.officems.gop.suggestionType.SuggestionTypeRepository;
import com.nsia.officems.gop.suggestionType.SuggestionTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestionServiceImpl implements SuggestionTypeService{
    
    @Autowired
    private SuggestionTypeRepository suggestionTypeRepository;
    @Override
    public List<LookupProjection> findAll() {
        String lang = "dr";

        switch (lang) {
            case "en":
                return suggestionTypeRepository.getByNameEn();
            case "dr":
                return suggestionTypeRepository.getByNameFa();
            case "ps":
                return suggestionTypeRepository.getByNamePs();
            default:
                return suggestionTypeRepository.getByNameEn();
        }
    }
}
