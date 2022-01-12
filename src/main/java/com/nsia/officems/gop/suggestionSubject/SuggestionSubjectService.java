package com.nsia.officems.gop.suggestionSubject;

import java.util.List;


public interface SuggestionSubjectService {
    public List<SuggestionSubject> findAll();
    public SuggestionSubject findById(Long id);
    }
