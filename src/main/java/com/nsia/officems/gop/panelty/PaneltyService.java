package com.nsia.officems.gop.panelty;

import java.util.List;

import com.nsia.officems.gop.panelty.dto.PaneltyDto;

public interface PaneltyService {
    public List<Panelty> findAll();
    public Panelty findById(Long id);
    public Panelty create(PaneltyDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, PaneltyDto dto); 
    public List<Panelty> findByProfile_id(Long id);
}
