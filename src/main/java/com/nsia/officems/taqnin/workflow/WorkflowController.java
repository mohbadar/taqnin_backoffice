package com.nsia.officems.taqnin.workflow;

import com.nsia.officems.taqnin.step.StepService;
import com.nsia.officems.taqnin.workflow.dto.WorkflowDto;
import com.nsia.officems.taqnin.workflow.dto.WorkflowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/taqnin/workflows")
public class WorkflowController {

    @Autowired
    private WorkflowService service;

    @Autowired
    private StepService stepService;

    @PreAuthorize("hasAuthority('WORKFLOW_LIST')")
    @GetMapping()
    public List<Workflow> findAll(){
        return service.findAll();
    }

    @PreAuthorize("hasAuthority('WORKFLOW_VIEW')")
    @GetMapping(path = {"/{id}"})
    public Workflow findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PreAuthorize("hasAuthority('WORKFLOW_CREATE')")
    @PostMapping()
    public Workflow create(@RequestBody WorkflowDto workflowDto ){
        return service.create(WorkflowMapper.workflowMapDto(new Workflow(), stepService, workflowDto));
    }

    @PreAuthorize("hasAuthority('WORKFLOW_EDIT')")
    @PutMapping("/{id}")
    public ResponseEntity<Workflow> update(@PathVariable("id") Long id, @RequestBody WorkflowDto workflowDto) {
            return ResponseEntity.ok(service.update(id, WorkflowMapper.workflowMapDto(new Workflow(),stepService,workflowDto)));
    }

    @PreAuthorize("hasAuthority('WORKFLOW_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
