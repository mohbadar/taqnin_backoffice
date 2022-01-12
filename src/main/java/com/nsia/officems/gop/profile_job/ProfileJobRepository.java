package com.nsia.officems.gop.profile_job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

public interface ProfileJobRepository extends JpaRepository<ProfileJob, Long>, RevisionRepository<ProfileJob, Long, Integer>   {
    public List<ProfileJob> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByMaktubDateDesc(Long id);
    public List<ProfileJob> findByProfile_idAndEndDateIsNullAndDeletedIsFalseOrDeletedIsNull(Long id);
    public ProfileJob findByProfile_idAndIntialJobTrue(Long id);
    public ProfileJob findByProfile_idAndTransfer_id(Long pid, Long tid);
    public ProfileJob findByProfile_idAndPanelty_id(Long pid, Long tid);
    public ProfileJob findByProfile_idAndAccountability_id(Long pid, Long aid);
    
    @Query(value = "SELECT * from public.profile_job where profile_id=:proId and deleted is not true order by maktub_date desc limit 1", nativeQuery = true)
    public ProfileJob findbyLastJobProfile(@Param("proId") long proId);

    @Query(value = "SELECT sum(days) from (select ((case when end_date is not null then end_date when end_date is null then CURRENT_DATE end)\\:\\:date - maktub_date\\:\\:date) AS days from public.profile_job where profile_id=:proId and job_break is null and accountability_id is null and deleted is not true) work", nativeQuery = true)
    public Long calculateWorkExperience(@Param("proId") Long proId);

}
