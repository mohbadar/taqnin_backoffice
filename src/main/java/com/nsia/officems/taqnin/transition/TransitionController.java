package com.nsia.officems.taqnin.transition;
import com.nsia.officems.taqnin.step.StepService;
import com.nsia.officems.taqnin.transition.dto.TransitionDto;
import com.nsia.officems.taqnin.transition.dto.TransitionMapper;
import com.nsia.officems.taqnin.workflow.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/taqnin/transitions")
public class TransitionController {

    @Autowired
    private TransitionService transitionService;

    @Autowired
    private StepService stepService;

    @Autowired
    private WorkflowService workflowService;

    @GetMapping()
    public List<Transition> findAll(){
        return transitionService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Transition findById(@PathVariable("id") Long id){
        return transitionService.findById(id);
    }

    @PostMapping()
    public Transition create(@RequestBody TransitionDto dto ){
        return transitionService.create(TransitionMapper.transitionMapDto(new Transition(),stepService,workflowService,dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transition> update(@PathVariable("id") Long id, @RequestBody TransitionDto dto) {

            return ResponseEntity.ok(transitionService.update(id,TransitionMapper.transitionMapDto(new Transition(),stepService,workflowService,dto) ));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(transitionService.delete(id));
    }

    @GetMapping("/workflows/{workflow_id}")
    public List<Transition> findAllByWorkflow(@PathVariable("workflow_id") Long workflow_id){
        return transitionService.findAllByWorkflow(workflow_id);
    }

}
