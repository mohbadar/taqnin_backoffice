package com.nsia.officems.gop.profile_promotion_type;

import java.util.List;

public interface ProfilePromotionTypeService {
    public List<ProfilePromotionType> findAll();
    public ProfilePromotionType findById(Long id);
    public ProfilePromotionType create(ProfilePromotionType type);
    public Boolean delete(Long id);
}
