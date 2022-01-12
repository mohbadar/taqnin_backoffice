package com.nsia.officems.odf.odf_presenters;

import java.util.List;
import java.util.Map;

import com.nsia.officems.odf.odf_presenters.dto.OdfPresenterDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface OdfPresenterService {
    public Object getList(DataTablesInput input, Map<String, String> filters);

    public List<OdfPresenter> findAll();

    public OdfPresenter findById(Long id);

    public OdfPresenter create(OdfPresenter odfPresenter);

    public Boolean delete(Long id);

    public Boolean update(Long id, OdfPresenterDto dto);

    public List<OdfPresenter> findByAgendaTopicId(Long id);

}
