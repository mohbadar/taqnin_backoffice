package com.nsia.officems.gop.profile_training.impl;

import com.nsia.officems.gop.profile_training.Training;
import com.nsia.officems.gop.profile_training.TrainingRepository;
import com.nsia.officems.gop.profile_training.TrainingService;
import com.nsia.officems.gop.profile_training.dto.TrainingDto;
import com.nsia.officems.gop.profile_training.dto.TrainingMapper;
import com.nsia.officems._admin.province.ProvinceService;
import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImpl implements TrainingService{
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CountryService countryService;

    @Autowired 
    private ProvinceService provinceService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Override
    public List<RevisionDTO>  getTrainingLog(Long id){
        Revisions<Integer, Training> indList = trainingRepository.findRevisions(id);
        List<Revision<Integer, Training>> trainings = indList.getContent();

        List<RevisionDTO> dtos= new ArrayList<>();
        
        for(Revision revision: trainings){
                dtos.add(new RevisionDTO(revision.getEntity()));
        }

        return dtos;
    }

    @Override
    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> findByProfile_id(Long id) {
        return trainingRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByEndDateDesc(id);
    }

    @Override
    public Training findById(Long id) {
        Optional<Training> optionalObj = trainingRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Training create(TrainingDto dto) {
        Training newTraining = new Training();
        Training training = TrainingMapper.MapTrainingDto(newTraining, dto, profileService, countryService, provinceService);
        training.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!training.equals(null)) {

            profileChecklistService.update(training.getProfile().getId(), titles.getTraining());
            return trainingRepository.save(training);
        }
        return null;
    }

    public Boolean update(Long id, TrainingDto dto) {
        Optional<Training> objection = trainingRepository.findById(id);
        if (objection.isPresent()) {
            Training training = TrainingMapper.MapTrainingDto(objection.get(), dto, profileService, countryService, provinceService);
            training.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (!training.equals(null)) {
                trainingRepository.save(training);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Training> training = trainingRepository.findById(id);

        if (training.isPresent()) {
            Training training2 = training.get();
            training2.setDeleted(true);
            training2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            trainingRepository.save(training2);
            return true;
        }

        return false;
    }
    
}
