package com.nsia.officems.odf.odf_resolution;

import java.util.List;
import java.util.Map;

import com.nsia.officems.odf.odf_resolution.dto.OdfResolutionDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface OdfResolutionService {
    public Object getList(DataTablesInput input, Map<String, String> filters);

    public List<OdfResolution> findAll();

    public OdfResolution findById(Long id);

    public OdfResolution create(OdfResolution resolution);

    public Boolean delete(Long id);

    public boolean update(Long id, OdfResolutionDto odfResolutionDto);

}
