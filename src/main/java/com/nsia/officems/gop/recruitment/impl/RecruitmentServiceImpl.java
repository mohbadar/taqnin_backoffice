package com.nsia.officems.gop.recruitment.impl;

import java.util.Map;

import java.util.Optional;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.gop.recruitment.Recruitment;
import com.nsia.officems.gop.recruitment.RecruitmentRepository;
import com.nsia.officems.gop.recruitment.RecruitmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentServiceImpl implements RecruitmentService{
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.Recruitment pro", null, joinClause, whereClause, groupByClause, input);
    }

    public Recruitment findById(Long id) {
        System.out.println("Recruitment.findById()" + id);
        Optional<Recruitment> objection = recruitmentRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Recruitment: " + objection);
            return objection.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<Recruitment> document = recruitmentRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public Recruitment save(Recruitment obj) {
        return this.recruitmentRepository.save(obj);
    }

    @Override
    public Long create(String data) throws JSONException {
        JSONObject obj = new JSONObject(data);
        // TODO Auto-generated method stub
        return null;
    }
    
}
