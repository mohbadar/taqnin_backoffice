package com.nsia.officems.gop.rewardType.impl;

import com.nsia.officems.gop.rewardType.RewardType;
import com.nsia.officems.gop.rewardType.RewardTypeRepository;
import com.nsia.officems.gop.rewardType.RewardTypeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class RewardTypeServiceImpl implements RewardTypeService{
    @Autowired
    private RewardTypeRepository rewardTypeRepository;

    @Override
    public List<RewardType> findAll() {
        return rewardTypeRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public RewardType findById(Long id) {
        Optional<RewardType> opionalObj = rewardTypeRepository.findById(id);
        if (opionalObj.isPresent())
            return opionalObj.get();
        return null;
    }

    @Override
    public RewardType create(RewardType type) {
        return rewardTypeRepository.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<RewardType> type = rewardTypeRepository.findById(id);

        if (type.isPresent()) {
            RewardType type2 = type.get();
            type2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            type2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            rewardTypeRepository.save(type2);
            return true;
        }

        return false;
    }
}
