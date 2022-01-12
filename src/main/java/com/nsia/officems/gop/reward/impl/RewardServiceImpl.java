package com.nsia.officems.gop.reward.impl;

import com.nsia.officems.gop.reward.Reward;
import com.nsia.officems.gop.reward.RewardRepository;
import com.nsia.officems.gop.reward.RewardService;
import com.nsia.officems.gop.reward.dto.RewardDto;
import com.nsia.officems.gop.reward.dto.RewardMapperDto;
import com.nsia.officems.gop.rewardType.RewardTypeService;

import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile.Dto.RevisionDTO;
import com.nsia.officems.gop.profile_job.ProfileJob;
import com.nsia.officems.gop.profile_job.ProfileJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

@Service
public class RewardServiceImpl implements RewardService{
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private RewardTypeService rewardTypeService;

    @Autowired
    private ProfileJobService profileJobService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    UserService userService;

    public List<RevisionDTO>  getRewardLog(Long id){
        Revisions<Integer, Reward> indList = rewardRepository.findRevisions(id);
        List<Revision<Integer, Reward>> trainings = indList.getContent();

        List<RevisionDTO> dtos= new ArrayList<>();
        
        for(Revision revision: trainings){
                dtos.add(new RevisionDTO(revision.getEntity()));
        }

        return dtos;
    }


    @Override
    public List<Reward> findAll() {
        return rewardRepository.findAll();
    }

    @Override
    public List<Reward> findByProfile_id(Long id) {
        return rewardRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByDecreeDateDesc(id);
    }

    @Override
    public Reward findById(Long id) {
        Optional<Reward> optionalObj = rewardRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Reward create(RewardDto dto) {
        Reward newReward = new Reward();
        Reward reward = RewardMapperDto.MapRewardDto(newReward, dto, profileService, rewardTypeService, profileJobService);
        reward.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!reward.equals(null)) {

            profileChecklistService.update(reward.getProfile().getId(), titles.getReward());
            if(reward.getProfileJob() != null){
                ProfileJob job = profileJobService.findById(reward.getProfileJob().getId());
                job.setIsReward(true);
                profileJobService.save(job);
            }
            return rewardRepository.save(reward);
        }
        return null;
    }

    public Boolean update(Long id, RewardDto dto) {
        Optional<Reward> objection = rewardRepository.findById(id);
        if (objection.isPresent()) {
            Reward reward = RewardMapperDto.MapRewardDto(objection.get(), dto, profileService, rewardTypeService, profileJobService);
            reward.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (!reward.equals(null)) {
                rewardRepository.save(reward);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Reward> reward = rewardRepository.findById(id);

        if (reward.isPresent()) {
            Reward reward2 = reward.get();
            reward2.setDeleted(true);
            reward2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            rewardRepository.save(reward2);
            if(reward2.getProfileJob() != null){
                ProfileJob job = profileJobService.findById(reward2.getProfileJob().getId());
                job.setIsReward(null);
                profileJobService.save(job);
            }
            return true;
        }

        return false;
    }

    
}
