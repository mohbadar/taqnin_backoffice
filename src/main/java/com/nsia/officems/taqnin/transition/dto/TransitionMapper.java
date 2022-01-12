package com.nsia.officems.taqnin.transition.dto;

import com.nsia.officems.taqnin.step.Step;
import com.nsia.officems.taqnin.step.StepService;
import com.nsia.officems.taqnin.transition.Transition;
import com.nsia.officems.taqnin.workflow.Workflow;
import com.nsia.officems.taqnin.workflow.WorkflowService;

public class TransitionMapper {

    public static Transition transitionMapDto(Transition transition, StepService stepService, WorkflowService workflowService, TransitionDto dto){

        Step fromStep = stepService.findById(dto.getFrom_step_id());
        if( fromStep != null) transition.setFromStep(fromStep);

        Step toStep = stepService.findById(dto.getTo_step_id());
        if( toStep != null) transition.setToStep(toStep);

        Workflow workflow = workflowService.findById(dto.getWorkflow_id());
        transition.setWorkflow(workflow);
        transition.setTransition_number(dto.getTransition_number());
        transition.setIsLastTransition(dto.getIsLastTransition());

        return transition;
    }
}
