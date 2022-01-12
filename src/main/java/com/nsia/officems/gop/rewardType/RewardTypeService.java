package com.nsia.officems.gop.rewardType;

import java.util.List;

public interface RewardTypeService {
    public List<RewardType> findAll();
    public RewardType findById(Long id);
    public RewardType create(RewardType type);
    public Boolean delete(Long id);
}
