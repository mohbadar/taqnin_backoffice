package com.nsia.officems.gop.profile_promotion_type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePromotionTypeRepository extends JpaRepository<ProfilePromotionType, Long> {
    public List<ProfilePromotionType> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

}
