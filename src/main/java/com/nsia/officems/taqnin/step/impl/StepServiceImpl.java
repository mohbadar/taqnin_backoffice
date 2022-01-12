package com.nsia.officems.taqnin.step.impl;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.taqnin.step.Step;
import com.nsia.officems.taqnin.step.StepRepository;
import com.nsia.officems.taqnin.step.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StepServiceImpl implements StepService {
    @Autowired
    private StepRepository repo;

    @Autowired
    UserService userService;

    @Override
    public List<Step> findAll() {
        return repo.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Step findById(Long id) {
        Optional<Step> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Step create(Step step) {
        step.setDeleted(false);
        step.setCreatedBy(userService.getLoggedInUser().getUsername());
        return repo.save(step);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Step> oStep = repo.findById(id);

        if (oStep.isPresent()) {
            Step step = oStep.get();
            step.setDeleted(true);
            step.setDeletedBy(userService.getLoggedInUser().getUsername());
            step.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(step);
            return true;
        }

        return false;
    }

    @Override
    public Step update(Long id, Step step) {
        if(id != null ) {
            Optional<Step> newStep = repo.findById(id);
            if(newStep.isPresent())
            {   Step updatedStep = newStep.get();
                updatedStep.setNameDr(step.getNameDr());
                updatedStep.setNamePs(step.getNamePs());
                updatedStep.setNameEn(step.getNameEn());
                return repo.save(updatedStep);
            }
            return null;
        }
        return null;
    }

    @Override
    public List<Step> findbyIdIn(List<Long> ids) {
        return repo.findByIdIn(ids);
    }
}
