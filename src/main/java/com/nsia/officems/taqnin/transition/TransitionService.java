package com.nsia.officems.taqnin.transition;

import java.util.List;

public interface TransitionService {
    public List<Transition> findAll();
    public Transition findById(Long id);
    public Transition create(Transition transition);
    public Boolean delete(Long id);
    public Transition update(Long id, Transition transition);
    public List<Transition> findAllByWorkflow(Long workflow_id);
}
