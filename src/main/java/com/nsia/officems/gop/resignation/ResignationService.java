package com.nsia.officems.gop.resignation;

import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface ResignationService {
    public Resignation save(Resignation obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Resignation findById(Long id);
    public boolean delete(long id);
    public Long create(String data) throws JSONException;
}
