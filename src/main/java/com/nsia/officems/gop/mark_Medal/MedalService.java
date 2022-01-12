package com.nsia.officems.gop.mark_Medal;

import java.util.List;

import com.nsia.officems.gop.mark_Medal.dto.MedalDto;

public interface MedalService {
    public List<Medal> findAll();
    public Medal findById(Long id);
    public Medal create(MedalDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, MedalDto dto); 
    public List<Medal> findByProfile_id(Long id); 
}
