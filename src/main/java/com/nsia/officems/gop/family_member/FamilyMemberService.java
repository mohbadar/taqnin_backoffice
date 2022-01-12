package com.nsia.officems.gop.family_member;

import java.util.List;

import com.nsia.officems.gop.family_member.dto.FamilyMemberDto;

public interface FamilyMemberService {
    public List<FamilyMember> findAll();
    public FamilyMember findById(Long id);
    public FamilyMember create(FamilyMemberDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, FamilyMemberDto dto); 
    public List<FamilyMember> findByProfile_id(Long id); 
}
