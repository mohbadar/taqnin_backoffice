package com.nsia.officems.gop.profile_setting.impl;

import com.nsia.officems.gop.profile_setting.ProfileSetting;
import com.nsia.officems.gop.profile_setting.ProfileSettingService;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems.gop.profile.Profile;
import com.nsia.officems.gop.profile.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileSettingServiceImpl implements ProfileSettingService{
   private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Override
    public Boolean create(Long proId, ProfileSetting data){
        Profile objection = profileService.findByIdWithoutRelation(proId);
        if(!objection.equals(null)){
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getTraining(), data.getTraining());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getReward(), data.getReward());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getPanelty(), data.getPanelty());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getPublication(), data.getPublication());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getHononary_service(), data.getHononary());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getMedal(), data.getMedal());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getMilitary(), data.getMilitary());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getTravel(), data.getTravel());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getTransfer(), data.getTransfer());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getPromotion(), data.getPromotion());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getAcademic_degree(), data.getAcademic());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getAccountability(), data.getAccountability());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getOff_duty_crime(), data.getCrime());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getFired_from_duty(), data.getFired());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getPolitical_party(), data.getParty());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getSalary(), data.getSalary());
            profileChecklistService.updateprofileSetting(objection.getId(), titles.getFamily_members(), data.getFamily());
            

            return true;
        }

        return false;
    }
    
}
