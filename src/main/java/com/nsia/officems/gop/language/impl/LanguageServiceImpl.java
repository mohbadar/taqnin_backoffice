package com.nsia.officems.gop.language.impl;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import com.nsia.officems.gop.language.Language;
import com.nsia.officems.gop.language.LanguageRepository;
import com.nsia.officems.gop.language.LanguageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService{

    @Autowired
    private LanguageRepository languageRepository;


    @Override
    public List<Language> findAll() {
        return languageRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Language findById(Long id) {
        Optional<Language> optionalObj = languageRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Language create(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public List<Language> findbyIdIn(List<Long> ids)
    {
        return languageRepository.findByIdIn(ids);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<Language> language = languageRepository.findById(id);

        if (language.isPresent()) {
            Language language2 = language.get();
            language2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            language2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            languageRepository.save(language2);
            return true;
        }

        return false;
    }
    
}
