package com.nsia.officems.taqnin.step;


import java.util.List;

public interface StepService {
    public List<Step> findAll();
    public Step findById(Long id);
    public Step create(Step step);
    public Boolean delete(Long id);
    public Step update(Long id, Step step);
    public List<Step> findbyIdIn(List<Long> ids);
}
