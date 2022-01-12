package com.nsia.officems._admin.province.impl;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._admin.province.Province;
import com.nsia.officems._admin.province.ProvinceRepository;
import com.nsia.officems._admin.province.ProvinceService;
import com.nsia.officems._admin.province.Dto.ProvinceDto;
import com.nsia.officems._admin.province.Dto.ProvinceDtoMapper;
import com.nsia.officems._admin.country.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService{

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    UserService userService;

    @Autowired
    CountryService countryService;

    @Override
    public List<Province> findAll() {
        return provinceRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Province findById(Long id) {
        Optional<Province> optionalObj = provinceRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Province save(Province obj) {
        return this.provinceRepository.save(obj);
    }

    @Override
    public Province create(ProvinceDto dto) {
        Province newProvince = new Province();
        Province province = ProvinceDtoMapper.MapProvinceDto(dto, newProvince, countryService);
        // province.setCreatedBy(userService.getLoggedInUser().getUsername());
        // province.setEnvSlug(userAuthService.getCurrentEnv());

        if (!province.equals(null)) {
            return provinceRepository.save(province);
        }
        return null;
    }

    public Boolean update(Long id, ProvinceDto dto) {
        Optional<Province> objection = provinceRepository.findById(id);
        if (objection.isPresent()) {
            Province province = ProvinceDtoMapper.MapProvinceDto( dto, objection.get(), countryService);
            if(!province.equals(null))
            {
                provinceRepository.save(province);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public List<Province> findByCountry(Long id) {
        return this.provinceRepository.findAllByCountry_id(id);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Province> oProvince = provinceRepository.findById(id);

        if (oProvince.isPresent()) {
            Province province = oProvince.get();
            province.setDeleted(true);
            province.setDeletedBy(userService.getLoggedInUser().getUsername());
            province.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            provinceRepository.save(province);
            return true;
        }

        return false;
    }
}
