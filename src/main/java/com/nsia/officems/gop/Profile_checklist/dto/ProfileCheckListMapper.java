package com.nsia.officems.gop.Profile_checklist.dto;

import java.util.ArrayList;
import java.util.List;

import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklist;
import com.nsia.officems.gop.profile.ProfileService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileCheckListMapper {

    public static List<ProfileChecklist> ProfileCheckListMappList(Long profileId, ProfileService profileService) {
        try {
            List<ProfileChecklist> profilechecklistArray = new ArrayList<ProfileChecklist>();
            ChecklistTitle title = new ChecklistTitle();
            List<String> titles = title.gettitles();
            for (int counter = 0; counter < 23; counter++) {
                ProfileChecklist checklist = new ProfileChecklist();
                checklist.setActive(true);
                checklist.setTitle(titles.get(counter));
                checklist.setStatus(false);
                checklist.setProfile(profileService.findByIdWithoutRelation(profileId));
                profilechecklistArray.add(checklist);
            }
            System.out.println(profilechecklistArray);
            profilechecklistArray.get(0).setStatus(true);

            return profilechecklistArray;
        } catch (Exception e) {
            System.out.println("Exception occured in creating ProfileChecklist:");
            return null;
        }
    }
}
