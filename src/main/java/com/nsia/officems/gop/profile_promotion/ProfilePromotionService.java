package com.nsia.officems.gop.profile_promotion;

import java.util.List;

import com.nsia.officems.gop.profile_promotion.dto.ProfilePromotionDto;

public interface ProfilePromotionService {
    public List<ProfilePromotion> findAll();
    public ProfilePromotion findById(Long id);
    public ProfilePromotion create(ProfilePromotionDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, ProfilePromotionDto dto); 
    public List<ProfilePromotion> findByProfile_id(Long id);
    public ProfilePromotion findbyLatestPromotion(Long id);
}
