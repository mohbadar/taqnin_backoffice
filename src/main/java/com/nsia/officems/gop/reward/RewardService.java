package com.nsia.officems.gop.reward;

import java.util.List;

import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.reward.dto.RewardDto;

public interface RewardService {
    public List<Reward> findAll();
    public Reward findById(Long id);
    public Reward create(RewardDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, RewardDto dto); 
    public List<Reward> findByProfile_id(Long id);
    public List<RevisionDTO>  getRewardLog(Long id);
}
