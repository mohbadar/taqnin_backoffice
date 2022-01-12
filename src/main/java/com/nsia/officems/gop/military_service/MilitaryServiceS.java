package com.nsia.officems.gop.military_service;

import java.util.List;

public interface MilitaryServiceS {
    public List<MilitaryService> findAll();

    public MilitaryService findById(Long id);

    public MilitaryService create(String data);

    public Boolean delete(Long id);

    public Boolean update(Long id, String data);

    public List<MilitaryService> findByProfile_id(Long id);

    public Boolean typeExists(String type);
}
