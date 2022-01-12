package com.nsia.officems.odf.odf_follow_up;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OdfFollowUpRepository extends JpaRepository<OdfFollowUp, Long>{
    public List<OdfFollowUp> findByOrder_idAndDeletedIsFalseOrDeletedIsNullOrderByDateDesc(Long id);
    @Query("SELECT od.type.nameDr as name , count(*) as count from OdfFollowUp od WHERE od.deleted is not true and od.order.id=:orId GROUP BY od.type.nameDr ORDER BY od.type.nameDr") 
    List getFollowUpCountByType(@Param("orId") long orId);
}
