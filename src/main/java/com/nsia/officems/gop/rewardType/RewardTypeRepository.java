package com.nsia.officems.gop.rewardType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardTypeRepository extends JpaRepository<RewardType, Long> {
    public List<RewardType> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

}
