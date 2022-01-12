package com.nsia.officems.gop.profile_travel;

import java.util.List;

import com.nsia.officems.gop.profile_travel.dto.TravelDto;

public interface TravelService {
    public List<Travel> findAll();
    public Travel findById(Long id);
    public Travel create(TravelDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, TravelDto dto); 
    public List<Travel> findByProfile_id(Long id);
}
