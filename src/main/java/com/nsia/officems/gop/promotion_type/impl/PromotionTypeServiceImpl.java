package com.nsia.officems.gop.promotion_type.impl;

import java.util.List;
import java.util.Optional;

import com.nsia.officems.gop.promotion_type.PromotionType;
import com.nsia.officems.gop.promotion_type.PromotionTypeRepository;
import com.nsia.officems.gop.promotion_type.PromotionTypeService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.nsia.officems._identity.authentication.user.UserService;

@Service
public class PromotionTypeServiceImpl implements PromotionTypeService{

    @Autowired
    private PromotionTypeRepository promotionTypeRepository;

    @Autowired
    UserService userService;

    @Override
    public List<PromotionType> findAll() {
        return promotionTypeRepository.findAll();
    }

    @Override
    public PromotionType findById(Long id) {
        Optional<PromotionType> optionalObj = promotionTypeRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public PromotionType create(PromotionType promotionType) {
     // promotionType.setCreatedBy(userService.getLoggedInUser().getUsername());
        return promotionTypeRepository.save(promotionType);
    }


   
    public Boolean delete(Long id){
        return false;
    }
    public PromotionType update(Long id, PromotionType promotionType){
        return null;
    }
}
