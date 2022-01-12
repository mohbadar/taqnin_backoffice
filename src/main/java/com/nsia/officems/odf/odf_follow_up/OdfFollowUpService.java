package com.nsia.officems.odf.odf_follow_up;

import java.util.List;

import com.nsia.officems.odf.odf_follow_up.dto.OdfFollowUpDto;

public interface OdfFollowUpService {
    public List<OdfFollowUp> findAll();
    public OdfFollowUp findById(Long id);
    public OdfFollowUp create(OdfFollowUpDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, OdfFollowUpDto dto);
    public List<OdfFollowUp> findByOrder_id(Long id); 
    public List getFollowUpCountByType(Long id);
}
