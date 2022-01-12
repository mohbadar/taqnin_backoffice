package com.nsia.officems.gop.reward.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.gop.profile.ProfileService;
import com.nsia.officems.gop.profile_job.ProfileJobService;
import com.nsia.officems.gop.reward.Reward;
import com.nsia.officems.gop.rewardType.RewardTypeService;

public class RewardMapperDto {
    public static Reward MapRewardDto(Reward reward, RewardDto dto,
     ProfileService profileService, RewardTypeService rewardTypeService, ProfileJobService profileJobService){ 
        DateTimeChange changeDate = new DateTimeChange();

        try{
            reward.setActive(true);
            reward.setType(dto.getType() == null? null: rewardTypeService.findById(dto.getType()));
            reward.setProfileJob(dto.getProfileJob() == null? null:profileJobService.findById(dto.getProfileJob()));
            reward.setSuggestedNumber(dto.getSuggestedNumber() == null? null: dto.getSuggestedNumber());
            reward.setSuggestedDate(dto.getSuggestedDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getSuggestedDate()));
            reward.setSuggestedSource(dto.getSuggestedSource() == null? null: dto.getSuggestedSource());
            reward.setDecreeNumber(dto.getDecreeNumber() == null? null: dto.getDecreeNumber());
            reward.setDecreeDate(dto.getDecreeDate() == null? null: changeDate.convertPersianDateToGregorianDate(dto.getDecreeDate()));
            reward.setDegreeSource(dto.getDegreeSource() == null? null : dto.getDegreeSource());
            reward.setCashAmount(dto.getCashAmount() == null? null: dto.getCashAmount());
            reward.setAppreciationDegree(dto.getAppreciationDegree() == null? null: dto.getAppreciationDegree());
            reward.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            return reward;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
