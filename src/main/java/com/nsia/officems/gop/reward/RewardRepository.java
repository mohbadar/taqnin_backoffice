package com.nsia.officems.gop.reward;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface RewardRepository extends JpaRepository<Reward, Long>, RevisionRepository<Reward, Long, Integer> {
    public List<Reward> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<Reward> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByDecreeDateDesc(Long id);

}
