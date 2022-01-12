package com.nsia.officems.odf.odf_subject;

import java.util.List;
import java.util.Map;

import com.nsia.officems.odf.odf_resolution.dto.OdfResolutionDto;
import com.nsia.officems.odf.odf_subject.dto.OdfSubjectDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface OdfSubjectService {
    public Object getList(DataTablesInput input, Map<String, String> filters);

    public List<OdfSubject> findAll();

    public OdfSubject findById(Long id);

    public OdfSubject create(OdfSubject odfSubject);

    public Boolean delete(Long id);

    public boolean update(Long id, OdfSubjectDto odfSubjectDto);

    public List<OdfSubject> findByResolutionId(Long id);

}
