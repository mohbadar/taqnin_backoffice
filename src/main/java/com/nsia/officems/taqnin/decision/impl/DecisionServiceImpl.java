package com.nsia.officems.taqnin.decision.impl;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.taqnin.decision.Decision;
import com.nsia.officems.taqnin.decision.DecisionRepository;
import com.nsia.officems.taqnin.decision.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DecisionServiceImpl implements DecisionService {
    @Autowired
    private DecisionRepository DecisionRepository;

    @Autowired
    UserService userService;

    @Override
    public List<Decision> findAll() {
        return DecisionRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Decision findById(Long id) {
        Optional<Decision> optionalObj = DecisionRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Decision create(Decision decision) {
        decision.setDeleted(false);
        decision.setCreatedBy(userService.getLoggedInUser().getUsername());
        return DecisionRepository.save(decision);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Decision> oDecision = DecisionRepository.findById(id);

        if (oDecision.isPresent()) {
            Decision decision = oDecision.get();
            decision.setDeleted(true);
            decision.setDeletedBy(userService.getLoggedInUser().getUsername());
            decision.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            DecisionRepository.save(decision);
            return true;
        }

        return false;
    }

    @Override
    public Decision update(Long id, Decision decision) {
        if(id != null ) {
            Optional<Decision> newDecision = DecisionRepository.findById(id);
            if(newDecision.isPresent())
            {   Decision updatedDecision = newDecision.get();
                updatedDecision.setNameDr(decision.getNameDr());
                updatedDecision.setNamePs(decision.getNamePs());
                updatedDecision.setNameEn(decision.getNameEn());
                return DecisionRepository.save(updatedDecision);
            }
            return null;

        }

        return null;
    }
}
