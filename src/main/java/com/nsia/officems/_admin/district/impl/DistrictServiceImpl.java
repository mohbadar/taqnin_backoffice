package com.nsia.officems._admin.district.impl;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._admin.district.District;
import com.nsia.officems._admin.district.DistrictRepository;
import com.nsia.officems._admin.district.DistrictService;
import com.nsia.officems._admin.district.Dto.DistrictDto;
import com.nsia.officems._admin.district.Dto.DistrictDtoMapper;
import com.nsia.officems._admin.province.ProvinceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProvinceService provinceService;

    @Override
    public List<District> findAll() {
        return districtRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public List<District> findByProvince(Long id) {
        return districtRepository.findByProvince_id(id);
    }

    @Override
    public District findById(Long id) {
        Optional<District> optionalObj = districtRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public District create(DistrictDto dto) {
        District newDistrict = new District();
        District district = DistrictDtoMapper.MapDistrictDto(dto, newDistrict, provinceService);
        // district.setCreatedBy(userService.getLoggedInUser().getUsername());
        // district.setEnvSlug(userAuthService.getCurrentEnv());

        if (!district.equals(null)) {
            return districtRepository.save(district);
        }
        return null;
    }

    public Boolean update(Long id, DistrictDto dto) {
        Optional<District> objection = districtRepository.findById(id);
        if (objection.isPresent()) {
            District district = DistrictDtoMapper.MapDistrictDto( dto, objection.get(), provinceService);
            if(!district.equals(null))
            {
                districtRepository.save(district);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        Optional<District> oCountry = districtRepository.findById(id);

        if (oCountry.isPresent()) {
            District country = oCountry.get();
            country.setDeleted(true);
            country.setDeletedBy(userService.getLoggedInUser().getUsername());
            country.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            districtRepository.save(country);
            return true;
        }

        return false;
    }

    @Override
    public District save(District obj) {
        return this.districtRepository.save(obj);
    }
    
}
