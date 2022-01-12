package com.nsia.officems._admin.district;

import java.util.List;

import com.nsia.officems._admin.district.Dto.DistrictDto;

public interface DistrictService {
    public List<District> findAll();
    public District findById(Long id);
    public boolean delete(Long id);
    public List<District> findByProvince(Long id);
    public District create(DistrictDto dto);
    public District save(District obj);
    public Boolean update(Long id, DistrictDto dto); 
}
