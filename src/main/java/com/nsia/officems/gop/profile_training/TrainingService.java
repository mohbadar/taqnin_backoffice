package com.nsia.officems.gop.profile_training;

import java.util.List;

import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.profile_training.dto.TrainingDto;

public interface TrainingService {
    public List<Training> findAll();
    public Training findById(Long id);
    public Training create(TrainingDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, TrainingDto dto); 
    public List<Training> findByProfile_id(Long id);
    public List<RevisionDTO>  getTrainingLog(Long id);
}
