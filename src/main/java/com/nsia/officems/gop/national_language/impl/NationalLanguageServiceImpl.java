package com.nsia.officems.gop.national_language.impl;

import com.nsia.officems.gop.national_language.NationalLanguage;
import com.nsia.officems.gop.national_language.NationalLanguageRepository;
import com.nsia.officems.gop.national_language.NationalLanguageService;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NationalLanguageServiceImpl implements NationalLanguageService{
    @Autowired
    private NationalLanguageRepository languageRepository;


    @Override
    public List<NationalLanguage> findAll() {
        return languageRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public NationalLanguage findById(Long id) {
        Optional<NationalLanguage> optionalObj = languageRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public NationalLanguage create(NationalLanguage language) {
        return languageRepository.save(language);
    }

    @Override
    public List<NationalLanguage> findbyIdIn(List<Long> ids)
    {
        return languageRepository.findByIdIn(ids);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<NationalLanguage> language = languageRepository.findById(id);

        if (language.isPresent()) {
            NationalLanguage language2 = language.get();
            language2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            language2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            languageRepository.save(language2);
            return true;
        }

        return false;
    }
}
