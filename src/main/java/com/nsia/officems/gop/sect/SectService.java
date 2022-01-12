package com.nsia.officems.gop.sect;

import java.util.List;

public interface SectService {
    public List<Sect> findAll();
    public Sect findById(Long id);
    public Sect create(Sect sect);
    public Boolean delete(Long id);
    
}
