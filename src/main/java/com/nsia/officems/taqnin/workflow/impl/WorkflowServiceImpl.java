package com.nsia.officems.taqnin.workflow.impl;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.taqnin.workflow.Workflow;
import com.nsia.officems.taqnin.workflow.WorkflowRepository;
import com.nsia.officems.taqnin.workflow.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WorkflowServiceImpl implements WorkflowService {
    @Autowired
    private WorkflowRepository repo;

    @Autowired
    UserService userService;

    @Override
    public List<Workflow> findAll() {
        return repo.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Workflow findById(Long id) {
        Optional<Workflow> optionalObj = repo.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Workflow create(Workflow workflow) {
        workflow.setDeleted(false);
        return repo.save(workflow);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Workflow> oWorkflow = repo.findById(id);

        if (oWorkflow.isPresent()) {
            Workflow workflow = oWorkflow.get();
            workflow.setDeleted(true);
            workflow.setDeletedBy(userService.getLoggedInUser().getUsername());
            workflow.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            repo.save(workflow);
            return true;
        }

        return false;
    }

    @Override
    public Workflow update(Long id, Workflow workflow) {
        if(id != null ) {
            Optional<Workflow> newWorkflow = repo.findById(id);
            if(newWorkflow.isPresent())
            {   Workflow updatedStep = newWorkflow.get();
                updatedStep.setNameDr(workflow.getNameDr());
                updatedStep.setNamePs(workflow.getNamePs());
                updatedStep.setNameEn(workflow.getNameEn());
                updatedStep.setSerialNo(workflow.getSerialNo());
                updatedStep.setProcessDays(workflow.getProcessDays());
                return repo.save(updatedStep);
            }
            return null;
        }
        return null;
    }
}
