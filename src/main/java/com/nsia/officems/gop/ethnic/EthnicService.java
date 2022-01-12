package com.nsia.officems.gop.ethnic;

import java.util.List;

public interface EthnicService {
    public List<Ethnic> findAll();
    public Ethnic findById(Long id);
    public Ethnic create(Ethnic ethnic);
    public Boolean delete(Long id);
}
