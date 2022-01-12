package com.nsia.officems.gop.family_member.impl;

import java.util.List;
import java.util.Optional;
import java.time.ZoneId;
import java.util.Date;
import com.nsia.officems.gop.Profile_checklist.ChecklistTitle;
import com.nsia.officems.gop.Profile_checklist.ProfileChecklistService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.gop.family_member.FamilyMember;
import com.nsia.officems.gop.family_member.FamilyMemberRepository;
import com.nsia.officems.gop.family_member.FamilyMemberService;
import com.nsia.officems.gop.family_member.dto.FamilyMemberDto;
import com.nsia.officems.gop.family_member.dto.FamilyMemberDtoMapper;
import com.nsia.officems.gop.profile.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyMemberServiceImpl implements FamilyMemberService{
    private ChecklistTitle titles = new ChecklistTitle();

    @Autowired
    private FamilyMemberRepository familyMemberRepository;


    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileChecklistService profileChecklistService;

    @Autowired
    private UserService userService;

    
    @Override
    public List<FamilyMember> findAll() {
        return familyMemberRepository.findAll();
    }

    @Override
    public List<FamilyMember> findByProfile_id(Long id) {
        return familyMemberRepository.findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(id);
    }

    @Override
    public FamilyMember findById(Long id) {
        Optional<FamilyMember> optionalObj = familyMemberRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public FamilyMember create(FamilyMemberDto dto) {
        FamilyMember newFamily = new FamilyMember();
        FamilyMember family = FamilyMemberDtoMapper.MapFamilyDto(newFamily, dto, profileService);
        family.setCreatedBy(userService.getLoggedInUser().getUsername());
        if (!family.equals(null)) {

            profileChecklistService.update(family.getProfile().getId(), titles.getFamily_members());
            return familyMemberRepository.save(family);
        }
        return null;
    }

    public Boolean update(Long id, FamilyMemberDto dto) {
        Optional<FamilyMember> objection = familyMemberRepository.findById(id);
        if (objection.isPresent()) {
            FamilyMember family = FamilyMemberDtoMapper.MapFamilyDto(objection.get(), dto, profileService);
            family.setUpdatedBy(userService.getLoggedInUser().getUsername());
            if (!family.equals(null)) {
                familyMemberRepository.save(family);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<FamilyMember> family = familyMemberRepository.findById(id);

        if (family.isPresent()) {
            FamilyMember familyMember = family.get();
            familyMember.setDeleted(true);
            familyMember.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            familyMemberRepository.save(familyMember);
            return true;
        }

        return false;
    }
}
