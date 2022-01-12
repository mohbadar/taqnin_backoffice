package com.nsia.officems.gop.promotion_type;
import java.util.List;

public interface PromotionTypeService {
    public List<PromotionType> findAll();
    public PromotionType findById(Long id);
    public PromotionType create(PromotionType promotionType);
    public Boolean delete(Long id);
    public PromotionType update(Long id, PromotionType promotionType);
}