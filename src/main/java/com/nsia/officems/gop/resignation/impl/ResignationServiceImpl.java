package com.nsia.officems.gop.resignation.impl;

import com.nsia.officems.gop.resignation.Resignation;
import com.nsia.officems.gop.resignation.ResignationRepository;
import com.nsia.officems.gop.resignation.ResignationService;

import java.util.Map;
import java.util.Optional;

import com.nsia.officems._util.DataTablesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class ResignationServiceImpl implements ResignationService {
    @Autowired
    private ResignationRepository  resignationRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.profile pro", null, joinClause, whereClause, groupByClause, input);
    }

    public Resignation findById(Long id) {
        System.out.println(" Resignation.findById()" + id);
        Optional<Resignation> objection = resignationRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Resignation: " + objection);
            return objection.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<Resignation> document = resignationRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public Resignation save(Resignation obj) {
        return this.resignationRepository.save(obj);
    }

    @Override
    public Long create(String data) throws JSONException {
        JSONObject obj = new JSONObject(data);
        // TODO Auto-generated method stub
        return null;
    }
}
