package com.nsia.officems.gop.suggestionSubject.impl;

import java.util.List;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubject;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubjectRepository;
import com.nsia.officems.gop.suggestionSubject.SuggestionSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestionSubjectServiceIml implements SuggestionSubjectService{
    
    @Autowired
    private SuggestionSubjectRepository suggestionSubjectRepository;
    @Override
    public List<SuggestionSubject> findAll(){
        return suggestionSubjectRepository.findAll();
    }

    @Override
    public SuggestionSubject findById(Long id){
        Optional<SuggestionSubject> OPSuggestion = suggestionSubjectRepository.findById(id);
        if(OPSuggestion.isPresent()){
            return OPSuggestion.get();
        
        }else{
            return null;
        }
    }

}
