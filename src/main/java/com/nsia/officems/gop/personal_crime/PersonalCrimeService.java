package com.nsia.officems.gop.personal_crime;

import java.util.List;

public interface PersonalCrimeService {
    public List<PersonalCrime> findAll();
    public PersonalCrime findById(Long id);
    public PersonalCrime create(String data);
    public Boolean delete(Long id);
    public Boolean update(Long id, String data); 
    public List<PersonalCrime> findByProfile_id(Long id);
}
