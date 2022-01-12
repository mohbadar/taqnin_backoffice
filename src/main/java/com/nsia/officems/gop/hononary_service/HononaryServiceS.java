package com.nsia.officems.gop.hononary_service;
import java.util.List;
import com.nsia.officems.gop.hononary_service.dto.HononaryServiceDto;

public interface HononaryServiceS {
    public List<HononaryService> findAll();
    public HononaryService findById(Long id);
    public HononaryService create(HononaryServiceDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, HononaryServiceDto dto); 
    public List<HononaryService> findByProfile_id(Long id); 
}
