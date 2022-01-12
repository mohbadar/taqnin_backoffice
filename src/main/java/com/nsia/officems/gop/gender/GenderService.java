package com.nsia.officems.gop.gender;

import java.util.List;

public interface GenderService {
    public List<Gender> findAll();
    public Gender findById(Long id);
    public Gender create(Gender gender);
    public Boolean delete(Long id);
}
