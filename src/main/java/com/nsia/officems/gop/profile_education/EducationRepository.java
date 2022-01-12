package com.nsia.officems.gop.profile_education;

import java.util.List;

import com.nsia.officems.gop.profile.Dto.PrintEducationDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

public interface EducationRepository extends JpaRepository<Education, Long>, RevisionRepository<Education, Long, Integer>  {
    public List<Education> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    @Query(value = "SELECT * from public.education ed inner join public.education_level le on ed.education_level=le.id where ed.profile_id=:proId and ed.deleted is not true order by le.id Desc", nativeQuery = true)
    public List<Education> findByProfile_idOrderByLevel_idDesc(@Param("proId") long proId);

    public List<PrintEducationDto> findByProfile_id(Long id);

    @Query(value = "SELECT * from public.education ed inner join public.education_level el on el.id = ed.education_level where ed.profile_id=:proId and ed.deleted is not true order by el.id desc limit 1", nativeQuery = true)
    public Education findbyLatestEducationProfile(@Param("proId") long proId); 

    public List<Education> findByProfileJob_id(Long id);

}
