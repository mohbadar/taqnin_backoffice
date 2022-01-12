package com.nsia.officems.taqnin.decision;
import java.util.List;
public interface DecisionService {
    public List<Decision> findAll();
    public Decision findById(Long id);
    public Decision create(Decision decision);
    public Boolean delete(Long id);
    public Decision update(Long id, Decision decision);
}
