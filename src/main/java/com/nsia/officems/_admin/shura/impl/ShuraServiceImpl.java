package com.nsia.officems._admin.shura.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.shura.Shura;
import com.nsia.officems._admin.shura.ShuraRepository;
import com.nsia.officems._admin.shura.ShuraService;
import com.nsia.officems._util.DataTablesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class ShuraServiceImpl implements ShuraService {

    @Autowired
    private ShuraRepository shuraRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = "shura.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.shura shura", null, joinClause, whereClause, groupByClause, input);
    }

    @Override
    public List<Shura> findAll() {
        return shuraRepository.findAll();
    }

    @Override
    public Shura findById(Long id) {
        Optional<Shura> optionalObj = shuraRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Shura create(Shura shura) {
        shura.setDeleted(false);
        return shuraRepository.save(shura);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Shura> shura = shuraRepository.findById(id);

        if (shura.isPresent()) {
            Shura shuraToBeDeleted = shura.get();
            shuraToBeDeleted.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            shuraToBeDeleted.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            shuraRepository.save(shuraToBeDeleted);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, Shura shuraNewValue) {
        Optional<Shura> shura = shuraRepository.findById(id);
        if (shura.isEmpty()) {
            return false;
        }

        Shura shuraToBeUpdated = shura.get();

        shuraToBeUpdated.setNameEn(shuraNewValue.getNameEn());
        shuraToBeUpdated.setNameDr(shuraNewValue.getNameDr());
        shuraToBeUpdated.setNamePs(shuraNewValue.getNamePs());

        shuraRepository.save(shuraToBeUpdated);
        return true;
    }

}
