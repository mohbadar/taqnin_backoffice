package com.nsia.officems.gop.retirement.impl;

import com.nsia.officems.gop.retirement.Retirement;
import com.nsia.officems.gop.retirement.RetirementRepository;
import com.nsia.officems.gop.retirement.RetirementService;

import java.util.Map;
import java.util.Optional;

import com.nsia.officems._util.DataTablesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class RetirementServiceImpl implements RetirementService {
    @Autowired
    private RetirementRepository retirementRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "left join public.profile po on po.id=re.profile_id left join public.profile_retirement_type ty on ty.id=re.type";
        // To have first AND with no error
        String whereClause = " re.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.profile_retirement re", null, joinClause, whereClause, groupByClause, input);
    }

    public Object getRetirementList(DataTablesInput input, Map<String, String> filters){
        String joinClause = "left join public.employee_status s on s.id=pro.employee_status left join public.ministry m on m.id=pro.first_ministry left join public.authority a on a.id=pro.first_authority left join public.commission c on c.id=pro.first_commission left join (SELECT sum(days) as days, profile_id from (select ((case when jo.end_date is not null then jo.end_date when jo.end_date is null then CURRENT_DATE end)\\:\\:date - jo.maktub_date\\:\\:date) AS days, jo.profile_id from public.profile_job jo inner join public.profile on profile.id = jo.profile_id where jo.job_break is null and jo.accountability_id is null) work group by profile_id ) pwork on pwork.profile_id = pro.id";
        // To have first AND with no error
        String whereClause = " pro.deleted is not true and (s.name_dr='برحال' or s.name_dr='انتظار با معاش') and (date_part('year',age(pro.dob_gregorian))>65 or pwork.days>14600)";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.profile pro", null, joinClause, whereClause, groupByClause, input);

    }

    public Retirement findById(Long id) {
        System.out.println("Retirement.findById()" + id);
        Optional<Retirement> objection = retirementRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Retirement: " + objection);
            return objection.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<Retirement> document = retirementRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public Retirement save(Retirement obj) {
        return this.retirementRepository.save(obj);
    }

    @Override
    public Long create(String data) throws JSONException {
        JSONObject obj = new JSONObject(data);
        // TODO Auto-generated method stub
        return null;
    }
}
