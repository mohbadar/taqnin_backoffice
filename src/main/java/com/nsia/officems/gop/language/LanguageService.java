package com.nsia.officems.gop.language;

import java.util.List;

public interface LanguageService {
    public List<Language> findAll();
    public Language findById(Long id);
    public Language create(Language language);
    public Boolean delete(Long id);
    public List<Language> findbyIdIn(List<Long> ids);
}
