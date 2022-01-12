package com.nsia.officems.odf.odf_agenda;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdfAgendaRepository extends JpaRepository<OdfAgenda, Long>{
    public List<OdfAgenda> findByIdIn(List<Long> agendas);
}
