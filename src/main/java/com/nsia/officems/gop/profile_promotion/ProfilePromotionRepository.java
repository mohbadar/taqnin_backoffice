package com.nsia.officems.gop.profile_promotion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfilePromotionRepository extends JpaRepository<ProfilePromotion, Long> {
    public List<ProfilePromotion> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByMaktubDateDesc(Long id);

    @Query(value = "SELECT * from public.profile_promotion where profile_id=:proId and deleted is not true order by maktub_date desc limit 1", nativeQuery = true)
    public ProfilePromotion findbyLatestPromotion(@Param("proId") long proId);

}
