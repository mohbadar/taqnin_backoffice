package com.nsia.officems.taqnin.taqnin_resolution;

import java.util.List;
import java.util.Map;

import com.nsia.officems.taqnin.taqnin_resolution.dto.TaqninResolutionDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface TaqninResolutionService {
    public Object getList(DataTablesInput input, Map<String, String> filters);

    public List<TaqninResolution> findAll();

    public TaqninResolution findById(Long id);

    public TaqninResolution create(TaqninResolution resolution);

    public Boolean delete(Long id);

    public boolean update(Long id, TaqninResolutionDto TaqninResolutionDto);

}
