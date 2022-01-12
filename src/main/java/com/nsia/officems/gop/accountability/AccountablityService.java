package com.nsia.officems.gop.accountability;

import java.util.List;

import com.nsia.officems.gop.accountability.dto.Accountabilitydto;

public interface AccountablityService {
    public List<Accountability> findAll();
    public Accountability findById(Long id);
    public Accountability create(Accountabilitydto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, Accountabilitydto dto); 
    public List<Accountability> findByProfile_id(Long id);
}
