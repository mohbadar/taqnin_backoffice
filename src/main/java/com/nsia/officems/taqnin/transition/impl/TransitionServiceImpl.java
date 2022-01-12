package com.nsia.officems.taqnin.transition.impl;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.taqnin.transition.Transition;
import com.nsia.officems.taqnin.transition.TransitionRepository;
import com.nsia.officems.taqnin.transition.TransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransitionServiceImpl implements TransitionService {

    @Autowired
    private TransitionRepository repo;

    @Autowired
    UserService userService;

    @Override
    public List<Transition> findAll() {
        return repo.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Transition findById(Long id) {
        Optional<Transition> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Transition create(Transition transition) {
        transition.setDeleted(false);
        return repo.save(transition);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Transition> oTransition = repo.findById(id);

        if (oTransition.isPresent()) {
            Transition transition = oTransition.get();
            transition.setDeleted(true);
            transition.setDeletedBy(userService.getLoggedInUser().getUsername());
            transition.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(transition);
            return true;
        }

        return false;
    }

    @Override
    public Transition update(Long id, Transition transition) {
        if(id != null ) {
            Optional<Transition> newTranistion = repo.findById(id);
            if(newTranistion.isPresent())
            {   Transition updatedTransition = newTranistion.get();
                 updatedTransition.setFromStep(transition.getFromStep());
                 updatedTransition.setToStep(transition.getToStep());
                 updatedTransition.setTransition_number(transition.getTransition_number());
                return repo.save(updatedTransition);
            }
            return null;
        }
        return null;
    }
   
    @Override
    public List<Transition> findAllByWorkflow(Long workflow_id)
    {
        return repo.findByWorkflow_idAndDeletedFalse(workflow_id);
    }
 
}
