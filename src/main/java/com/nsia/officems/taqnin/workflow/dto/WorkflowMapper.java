package com.nsia.officems.taqnin.workflow.dto;

import com.nsia.officems.taqnin.step.Step;
import com.nsia.officems.taqnin.step.StepService;
import com.nsia.officems.taqnin.workflow.Workflow;

import java.util.HashSet;
import java.util.Set;

public class WorkflowMapper {

    public static Workflow workflowMapDto(Workflow workflow, StepService stepService, WorkflowDto dto){
        workflow.setNameDr(dto.getNameDr());
        workflow.setNameEn(dto.getNameEn());
        workflow.setNamePs(dto.getNamePs());
        workflow.setSerialNo(dto.getSerialNo());
        workflow.setProcessDays(dto.getProcessDays());
        // if (dto.getStepsIds() != null) {
        //     Set<Step> steps = new HashSet<>();
        //     dto.getStepsIds().forEach((element) -> {
        //         steps.add(stepService.findById(element));
        //     });
        //     workflow.setSteps(steps);
        // }
        return workflow;
    }
}
