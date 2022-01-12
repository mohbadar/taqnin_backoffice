package com.nsia.officems.gop.retirement;

import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface RetirementService {
    public Retirement save(Retirement obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Object getRetirementList(DataTablesInput input, Map<String, String> filters);
    public Retirement findById(Long id);
    public boolean delete(long id);
    public Long create(String data) throws JSONException;
}
