package com.nsia.officems.gop.paneltyType;

import java.util.List;

public interface PaneltyTypeService {
    public List<PaneltyType> findAll();
    public PaneltyType findById(Long id);
    public PaneltyType create(PaneltyType type);
    public Boolean delete(Long id); 
}
