package com.nsia.officems.odf.odf_agenda;

import java.util.List;
import java.util.Map;

import com.nsia.officems.odf.odf_agenda.dto.OdfAgendaDto;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface OdfAgendaService {
    public List<OdfAgenda> findAll();

    public OdfAgenda findById(Long id);

    public OdfAgenda create(OdfAgenda odfAgenda);

    public Boolean delete(Long id);

    public Boolean update(Long id, OdfAgendaDto dto);

    public Object getList(DataTablesInput input, Map<String, String> filters);

    public List<OdfAgenda> findbyIdIn(List<Long> ids);
}
