package com.nsia.officems._admin.shura;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface ShuraService {

    public Object getList(DataTablesInput input, Map<String, String> filters);

    public List<Shura> findAll();

    public Shura findById(Long id);

    public Shura create(Shura shura);

    public Boolean delete(Long id);

    public boolean update(Long id, Shura shura);

}
