package com.nsia.officems.gop.religion;

import java.util.List;

public interface ReligionService {
    public List<Religion> findAll();
    public Religion findById(Long id);
    public Religion create(Religion ethnic);
    public Boolean delete(Long id);
    
}
