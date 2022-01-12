package com.nsia.officems.gop.national_language;

import java.util.List;

public interface NationalLanguageService{
    public List<NationalLanguage> findAll();
    public NationalLanguage findById(Long id);
    public NationalLanguage create(NationalLanguage language);
    public Boolean delete(Long id);
    public List<NationalLanguage> findbyIdIn(List<Long> ids);
}
