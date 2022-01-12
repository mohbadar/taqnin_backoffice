package com.nsia.officems.gop.family_member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {
    public List<FamilyMember> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(Long id);

}
