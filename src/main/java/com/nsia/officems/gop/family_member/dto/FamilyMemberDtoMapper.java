package com.nsia.officems.gop.family_member.dto;

import com.nsia.officems.gop.family_member.FamilyMember;
import com.nsia.officems.gop.profile.ProfileService;

public class FamilyMemberDtoMapper {
    public static FamilyMember MapFamilyDto(FamilyMember family, FamilyMemberDto dto, ProfileService profileService){ 

        try{
            family.setActive(true);
            family.setFamilytotal(dto.getFamilytotal() == null? null: dto.getFamilytotal());
            family.setFemale(dto.getFemale() == null? null: dto.getFemale());
            family.setMale(dto.getMale() == null? null: dto.getMale());
            family.setProfile(dto.getProfile() == null? null: profileService.findByIdWithoutRelation(dto.getProfile()));
            return family;

        }
        catch(Exception e){
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
