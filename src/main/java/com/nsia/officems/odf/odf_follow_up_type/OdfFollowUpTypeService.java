package com.nsia.officems.odf.odf_follow_up_type;

import java.util.List;

public interface OdfFollowUpTypeService {
    public List<OdfFollowUpType> findAll();
    public OdfFollowUpType findById(Long id);
    public OdfFollowUpType create(OdfFollowUpType type);
    public Boolean delete(Long id);
}
