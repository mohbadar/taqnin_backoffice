package com.nsia.officems.gop.promotion.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.gop.promotion.Promotion;
import com.nsia.officems.gop.promotion.PromotionRepository;
import com.nsia.officems.gop.promotion.PromotionService;
import com.querydsl.core.QueryFlag.Position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private PromotionRepository  promotionRepository;

    @Autowired
    UserService userService;
    
    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    // @Autowired
    // private DataTablesUtil dataTablesUtil;

    // public Object getList(DataTablesInput input, Map<String, String> filters) {
    //     String joinClause = "";
    //     // To have first AND with no error
    //     String whereClause = dataTablesUtil.whereClause(filters);
    //     String groupByClause = "";
    //     return dataTablesUtil.getDataList("public.profile pro", null, joinClause, whereClause, groupByClause, input);
    // }

    public Promotion findById(Long id) {
        System.out.println(" Resignation.findById()" + id);
        Optional<Promotion> objection = promotionRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Promotion: " + objection);
            return objection.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<Promotion> document = promotionRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    // @Override
    // public Promotion save(Promotion promotion) {
    //     return this.promotionRepository.save(promotion);
    // }
 

    @Override
    public Promotion create(Promotion promotion) {
        promotion.setDeleted(false);
      //  promotion.setCreatedBy(userService.getLoggedInUser().getUsername());
        return promotionRepository.save(promotion);
    }
}
