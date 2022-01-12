package com.nsia.officems.gop.recruitment;

import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface RecruitmentService {
    public Recruitment save(Recruitment obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Recruitment findById(Long id);
    public boolean delete(long id);
    public Long create(String data) throws JSONException;
    
}
