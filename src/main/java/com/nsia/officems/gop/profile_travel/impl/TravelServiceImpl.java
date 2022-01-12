package com.nsia.officems.gop.profile_travel.impl;

import com.nsia.officems.gop.profile_travel.Travel;
import com.nsia.officems.gop.profile_travel.TravelRepository;
import com.nsia.officems.gop.profile_travel.TravelService;
import com.nsia.officems.gop.profile_travel.dto.TravelDto;
import com.nsia.officems.gop.profile_travel.dto.TravelDtoMapper;
import com.nsia.officems._admin.province.ProvinceService;
import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelServiceImpl implements TravelService{
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CountryService countryService;

    @Autowired 
    private ProvinceService provinceService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private ProfileJobService profilejobService;

    @Autowired
    private UserService userService;

    @Override
    public List<Travel> findAll() {
        return travelRepository.findAll();
    }

    @Override
    public List<Travel> findByProfile_id(Long id) {
        return travelRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByDateDesc(id);
    }

    @Override
    public Travel findById(Long id) {
        Optional<Travel> optionalObj = travelRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Travel create(TravelDto dto) {
        Travel newTravel = new Travel();
        Travel travel = TravelDtoMapper.MapTravelDto(newTravel, dto, profileService, countryService, provinceService, profilejobService);
        travel.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!travel.equals(null)) {

            profileChecklistService.update(travel.getProfile().getId(), titles.getTravel());
            return travelRepository.save(travel);
        }
        return null;
    }

    public Boolean update(Long id, TravelDto dto) {
        Optional<Travel> objection = travelRepository.findById(id);
        if (objection.isPresent()) {
            Travel travel = TravelDtoMapper.MapTravelDto(objection.get(), dto, profileService, countryService, provinceService, profilejobService);
            travel.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (!travel.equals(null)) {
                travelRepository.save(travel);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Travel> travel = travelRepository.findById(id);

        if (travel.isPresent()) {
            Travel travel2 = travel.get();
            travel2.setDeleted(true);
            travel2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            travelRepository.save(travel2);
            return true;
        }

        return false;
    }
    
}
