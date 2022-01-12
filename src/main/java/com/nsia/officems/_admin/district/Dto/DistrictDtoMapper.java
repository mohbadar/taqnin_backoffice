package com.nsia.officems._admin.district.Dto;

import com.nsia.officems._admin.district.District;
import com.nsia.officems._admin.province.ProvinceService;

public class DistrictDtoMapper {
    public static District MapDistrictDto(DistrictDto dto, District district, ProvinceService provinceService) {
        district.setActive(true);
        district.setNameDr(dto.getNameDr()== null? null: dto.getNameDr());
        district.setNameEn(dto.getNameEn()== null? null: dto.getNameEn());
        district.setNamePs(dto.getNamePs()== null? null: dto.getNamePs());
        district.setCode(dto.getCode()== null? null: dto.getCode());
        district.setProvince(dto.getProvince() == null? null: provinceService.findById(dto.getProvince()));
        return district;
    }


    
}
