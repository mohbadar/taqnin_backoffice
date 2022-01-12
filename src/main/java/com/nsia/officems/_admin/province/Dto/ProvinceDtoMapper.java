package com.nsia.officems._admin.province.Dto;

import com.nsia.officems._admin.province.Province;
import com.nsia.officems._admin.country.CountryService;

public class ProvinceDtoMapper {
    public static Province MapProvinceDto(ProvinceDto dto, Province province, CountryService countryService) {
        province.setActive(true);
        province.setNameDr(dto.getNameDr()== null? null: dto.getNameDr());
        province.setNameEn(dto.getNameEn()== null? null: dto.getNameEn());
        province.setNamePs(dto.getNamePs()== null? null: dto.getNamePs());
        province.setCode(dto.getCode()== null? null: dto.getCode());
        province.setCountry(dto.getCountry() == null? null: countryService.findById(dto.getCountry()));
        return province;
    }

    
}
