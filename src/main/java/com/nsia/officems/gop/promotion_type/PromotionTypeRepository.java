package com.nsia.officems.gop.promotion_type;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionTypeRepository extends JpaRepository<PromotionType, Long> {
    public List<PromotionType> findAll();
}
