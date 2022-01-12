package com.nsia.officems.taqnin.workflow;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems.taqnin.step.Step;

import java.util.List;

public interface WorkflowService {
     List<Workflow> findAll();
     Workflow findById(Long id);
     Workflow create(Workflow workflow);
     Boolean delete(Long id);
     Workflow update(Long id, Workflow workflow);

}
