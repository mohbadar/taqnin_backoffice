package com.nsia.officems._admin.country.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._admin.country.Country;
import com.nsia.officems._admin.country.CountryRepository;
import com.nsia.officems._admin.country.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    UserService userService;

    @Override
    public List<Country> findAll() {
        return countryRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Country findById(Long id) {
        Optional<Country> optionalObj = countryRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Country create(Country country) {
        country.setDeleted(false);
        country.setCreatedBy(userService.getLoggedInUser().getUsername());
        return countryRepository.save(country);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Country> oCountry = countryRepository.findById(id);

        if (oCountry.isPresent()) {
            Country country = oCountry.get();
            country.setDeleted(true);
            country.setDeletedBy(userService.getLoggedInUser().getUsername());
            country.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            countryRepository.save(country);
            return true;
        }

        return false;
    }

    @Override
    public Country update(Long id, Country country) {
        if(id != null ) {
            Optional<Country> newCountry = countryRepository.findById(id);
            if(newCountry.isPresent())
            {   Country updatedCountry = newCountry.get();
                updatedCountry.setNameDr(country.getNameDr());
                updatedCountry.setNamePs(country.getNamePs());
                updatedCountry.setNameEn(country.getNameEn());
                return countryRepository.save(updatedCountry);
            }
            return null;
            
        }

        return null;
    }
}
