package com.nsia.officems.gop.profile;

import java.util.List;

import com.nsia.officems._util.MapData;
import com.nsia.officems.gop.profile.Dto.ProfileViewDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>, RevisionRepository<Profile, Long, Integer> {

    public List<Profile> findByFirstNameAndLastNameAndFatherName(String firstName, String lastName, String fatherName);

    @Query("select p.id as id ,p.firstName as firstName,p.lastName as lastName,p.fatherName as fatherName  from Profile p where p.firstName like %:term% or p.lastName like %:term% or p.fatherName like %:term%")
    public List<ProfileProjection> findByColumnsContaining(String term);

    @Query(value = "select * from profile where first_name like %?1% or last_name like %?1% or father_name like %?1%", nativeQuery = true)
    public List<Profile> searchByValue(String value);

    @Query(value = "select count(*) from public.profile where deleted is not true", nativeQuery = true)
    public long count();

    @Query(value = "select count(*) from public.profile p inner join public.employee_status s on p.employee_status = s.id  where s.name_dr = 'برحال' and p.deleted is not true", nativeQuery = true)
    public long countActive();

    @Query("SELECT p.ethnic.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.deleted is not true GROUP BY p.ethnic.nameDr ORDER BY p.ethnic.nameDr DESC") 
    List getProfileCountByEthnic();

    @Query("SELECT p.gender.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.deleted is not true GROUP BY p.gender.nameDr ORDER BY p.gender.nameDr ASC") 
    List getProfileCountByGender();

    @Query("SELECT p.sect.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.deleted is not true GROUP BY p.sect.nameDr ORDER BY p.sect.nameDr ASC") 
    List getProfileCountBySect();


    @Query(value="select level,count(*) from (select distinct t.profile_id, (select max(education_level) as level from public.education k where k.profile_id = t.profile_id) from public.education t inner join public.profile p on t.profile_id = p.id inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.deleted is not true and t.deleted is not true) edt group by level", nativeQuery = true)
    List getProfileEducation();


    @Query("SELECT p.ministry.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and  p.deleted is not true AND p.ministry is not null GROUP BY p.ministry.nameDr ORDER BY p.ministry.nameDr ASC") 
    List getProfileCountByMinistry();

    @Query("SELECT p.authority.nameDr as name , count(*) as count from Profile p WHERE  p.status.nameDr='برحال' and p.deleted is not true AND p.authority is not null GROUP BY p.authority.nameDr ORDER BY p.authority.nameDr ASC") 
    List getProfileCountByAuthority();

    @Query("SELECT p.commission.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and  p.deleted is not true AND p.commission is not null GROUP BY p.commission.nameDr ORDER BY p.commission.nameDr ASC") 
    List getProfileCountByCommission();


    @Query(value = "select count(*) as count, date_part as age from (select EXTRACT(YEAR FROM age(cast(dob_gregorian as date))) from public.profile p inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.deleted is not true) profiledob group by date_part order by date_part DESC", nativeQuery = true)
    List getProfileCountByAge();

    @Query("SELECT "+ "new com.nsia.officems._util.MapData(p.originalProvince.nameDr,count(*))" + 
    " from Profile p WHERE p.status.nameDr='برحال' and p.deleted is not true GROUP BY p.originalProvince.nameDr ORDER BY p.originalProvince.nameDr ASC")
    List<MapData> getMapData();


    @Query("SELECT p.ethnic.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.ministry.id=:proId and p.deleted is not true GROUP BY p.ethnic.nameDr ORDER BY p.ethnic.nameDr DESC") 
    List getEthnicByMinistry(@Param("proId") long proId);

    @Query("SELECT p.ethnic.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.authority.id=:proId and p.deleted is not true GROUP BY p.ethnic.nameDr ORDER BY p.ethnic.nameDr DESC") 
    List getEthnicByAuthority(@Param("proId") long proId);

    @Query("SELECT p.ethnic.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.commission.id=:proId and p.deleted is not true GROUP BY p.ethnic.nameDr ORDER BY p.ethnic.nameDr DESC") 
    List getEthnicByCommission(@Param("proId") long proId);


    @Query("SELECT p.ethnic.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.ministry is not null and p.authority is null and p.commission is null and p.deleted is not true GROUP BY p.ethnic.nameDr ORDER BY p.ethnic.nameDr DESC") 
    List getEthnicByAllMinistries();

    @Query("SELECT p.ethnic.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.authority is not null and p.ministry is null and p.commission is null and p.deleted is not true GROUP BY p.ethnic.nameDr ORDER BY p.ethnic.nameDr DESC") 
    List getEthnicByAllAuthorities();

    @Query("SELECT p.ethnic.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.commission is not null and p.ministry is null and p.authority is null and p.deleted is not true GROUP BY p.ethnic.nameDr ORDER BY p.ethnic.nameDr DESC") 
    List getEthnicByAllCommissions();


    @Query("SELECT p.sect.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.ministry is not null and p.authority is null and p.commission is null and p.deleted is not true GROUP BY p.sect.nameDr ORDER BY p.sect.nameDr ASC") 
    List getSectByAllMinistries();

    @Query("SELECT p.sect.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.authority is not null and p.ministry is null and p.commission is null and p.deleted is not true GROUP BY p.sect.nameDr ORDER BY p.sect.nameDr ASC") 
    List getSectByAllAuthories();

    @Query("SELECT p.sect.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.commission is not null and p.ministry is null and p.authority is null and p.deleted is not true GROUP BY p.sect.nameDr ORDER BY p.sect.nameDr ASC") 
    List getSectByAllCommission();

    @Query("SELECT p.sect.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.ministry.id=:proId and p.deleted is not true GROUP BY p.sect.nameDr ORDER BY p.sect.nameDr ASC") 
    List getSectByMinistry(@Param("proId") long proId);

    @Query("SELECT p.sect.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.authority.id=:proId and p.deleted is not true GROUP BY p.sect.nameDr ORDER BY p.sect.nameDr ASC") 
    List getSectByAuthority(@Param("proId") long proId);

    @Query("SELECT p.sect.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.commission.id=:proId and p.deleted is not true GROUP BY p.sect.nameDr ORDER BY p.sect.nameDr ASC") 
    List getSectByCommission(@Param("proId") long proId);


    @Query("SELECT p.gender.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.ministry is not null and p.authority is null and p.commission is null and p.deleted is not true GROUP BY p.gender.nameDr ORDER BY p.gender.nameDr ASC") 
    List getGenderByAllMinistries();

    @Query("SELECT p.gender.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.authority is not null and p.ministry is null and p.commission is null and p.deleted is not true GROUP BY p.gender.nameDr ORDER BY p.gender.nameDr ASC") 
    List getGenderByAllAuthorities();

    @Query("SELECT p.gender.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.commission is not null and p.ministry is null and p.authority is null and p.deleted is not true GROUP BY p.gender.nameDr ORDER BY p.gender.nameDr ASC") 
    List getGenderByAllCommission();

    @Query("SELECT p.gender.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.ministry.id=:proId and p.deleted is not true GROUP BY p.gender.nameDr ORDER BY p.gender.nameDr ASC") 
    List getGenderByMinistry(@Param("proId") long proId);

    @Query("SELECT p.gender.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.authority.id=:proId and p.deleted is not true GROUP BY p.gender.nameDr ORDER BY p.gender.nameDr ASC") 
    List getGenderByAuthority(@Param("proId") long proId);

    @Query("SELECT p.gender.nameDr as name , count(*) as count from Profile p WHERE p.status.nameDr='برحال' and p.commission.id=:proId and p.deleted is not true GROUP BY p.gender.nameDr ORDER BY p.gender.nameDr ASC") 
    List getGenderByCommission(@Param("proId") long proId);
    

    @Query(value = "select count(*) as count, date_part as age from (select EXTRACT(YEAR FROM age(cast(dob_gregorian as date))) from public.profile p inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.first_ministry is not null and p.first_authority is null and p.first_commission is null and p.deleted is not true) profiledob group by date_part order by date_part DESC", nativeQuery = true)
    List getAgeByAllMinistries();

    @Query(value = "select count(*) as count, date_part as age from (select EXTRACT(YEAR FROM age(cast(dob_gregorian as date))) from public.profile p inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.first_authority is not null and p.first_ministry is null and p.first_commission is null and p.deleted is not true) profiledob group by date_part order by date_part DESC", nativeQuery = true)
    List getAgeByAllAuthorities();

    
    @Query(value = "select count(*) as count, date_part as age from (select EXTRACT(YEAR FROM age(cast(dob_gregorian as date))) from public.profile p inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.first_commission is not null and p.first_ministry is null and p.first_authority is null and p.deleted is not true) profiledob group by date_part order by date_part DESC", nativeQuery = true)
    List getAgeByAllCommissions();

    @Query(value = "select count(*) as count, date_part as age from (select EXTRACT(YEAR FROM age(cast(dob_gregorian as date))) from public.profile p inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.first_ministry=:proId and p.deleted is not true) profiledob group by date_part order by date_part DESC", nativeQuery = true)
    List getAgeByMinistry(@Param("proId") long proId);

    @Query(value = "select count(*) as count, date_part as age from (select EXTRACT(YEAR FROM age(cast(dob_gregorian as date))) from public.profile p inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.first_authority=:proId and p.deleted is not true) profiledob group by date_part order by date_part DESC", nativeQuery = true)
    List getAgeByAuthority(@Param("proId") long proId);

    @Query(value = "select count(*) as count, date_part as age from (select EXTRACT(YEAR FROM age(cast(dob_gregorian as date))) from public.profile p inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and first_commission=:proId and p.deleted is not true) profiledob group by date_part order by date_part DESC", nativeQuery = true)
    List getAgeByCommission(@Param("proId") long proId);


    @Query(value="select level,count(*) from (select distinct t.profile_id, (select max(education_level) as level from public.education k where k.profile_id = t.profile_id) from public.education t inner join public.profile p on t.profile_id = p.id inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.first_ministry is not null and p.first_authority is null and p.first_commission is null and p.deleted is not true and t.deleted is not true) edt group by level", nativeQuery = true)
    List getEducationByAllMinistries();

    @Query(value="select level,count(*) from (select distinct t.profile_id, (select max(education_level) as level from public.education k where k.profile_id = t.profile_id) from public.education t inner join public.profile p on t.profile_id = p.id inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.first_authority is not null and p.first_ministry is null and p.first_commission is null and p.deleted is not true and t.deleted is not true) edt group by level", nativeQuery = true)
    List getEducationByAllAuthorities();

    @Query(value="select level,count(*) from (select distinct t.profile_id, (select max(education_level) as level from public.education k where k.profile_id = t.profile_id) from public.education t inner join public.profile p on t.profile_id = p.id inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.first_commission is not null and p.first_ministry is null and p.first_authority is null and p.deleted is not true and t.deleted is not true) edt group by level", nativeQuery = true)
    List getEducationByAllCommissions();

    @Query(value="select level,count(*) from (select distinct t.profile_id, (select max(education_level) as level from public.education k where k.profile_id = t.profile_id) from public.education t inner join public.profile p on t.profile_id = p.id inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.first_ministry=:proId and p.deleted is not true and t.deleted is not true) edt group by level", nativeQuery = true)
    List getEducationByMinistry(@Param("proId") long proId);

    @Query(value="select level,count(*) from (select distinct t.profile_id, (select max(education_level) as level from public.education k where k.profile_id = t.profile_id) from public.education t inner join public.profile p on t.profile_id = p.id inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.first_authority=:proId and p.deleted is not true and t.deleted is not true) edt group by level", nativeQuery = true)
    List getEducationByAuthority(@Param("proId") long proId);

    @Query(value="select level,count(*) from (select distinct t.profile_id, (select max(education_level) as level from public.education k where k.profile_id = t.profile_id) from public.education t inner join public.profile p on t.profile_id = p.id inner join public.employee_status s on p.employee_status = s.id where s.name_dr = 'برحال' and p.first_commission=:proId and p.deleted is not true and t.deleted is not true) edt group by level", nativeQuery = true)
    List getEducationByCommission(@Param("proId") long proId);


    @Query("SELECT "+ "new com.nsia.officems._util.MapData(p.originalProvince.nameDr,count(*))" + 
    " from Profile p WHERE p.status.nameDr='برحال' and p.ministry is not null and p.authority is null and p.commission is null and p.deleted is not true GROUP BY p.originalProvince.nameDr ORDER BY p.originalProvince.nameDr ASC")
    List<MapData> getMapDataByAllMinistries();

    @Query("SELECT "+ "new com.nsia.officems._util.MapData(p.originalProvince.nameDr,count(*))" + 
    " from Profile p WHERE p.status.nameDr='برحال' and p.authority is not null and p.ministry is null and p.commission is null and p.deleted is not true GROUP BY p.originalProvince.nameDr ORDER BY p.originalProvince.nameDr ASC")
    List<MapData> getMapDataByAllAuthorities();

    @Query("SELECT "+ "new com.nsia.officems._util.MapData(p.originalProvince.nameDr,count(*))" + 
    " from Profile p WHERE p.status.nameDr='برحال' and p.commission is not null and p.authority is null and p.ministry is null and p.deleted is not true GROUP BY p.originalProvince.nameDr ORDER BY p.originalProvince.nameDr ASC")
    List<MapData> getMapDataByAllCommissions();


    @Query("SELECT "+ "new com.nsia.officems._util.MapData(p.originalProvince.nameDr,count(*))" + 
    " from Profile p WHERE p.status.nameDr='برحال' and p.ministry.id=:proId and p.deleted is not true GROUP BY p.originalProvince.nameDr ORDER BY p.originalProvince.nameDr ASC")
    List<MapData> getMapDataByMinstry(@Param("proId") long proId);

    @Query("SELECT "+ "new com.nsia.officems._util.MapData(p.originalProvince.nameDr,count(*))" + 
    " from Profile p WHERE p.status.nameDr='برحال' and p.authority.id=:proId and p.deleted is not true GROUP BY p.originalProvince.nameDr ORDER BY p.originalProvince.nameDr ASC")
    List<MapData> getMapDataByAuthority(@Param("proId") long proId);

    @Query("SELECT "+ "new com.nsia.officems._util.MapData(p.originalProvince.nameDr,count(*))" + 
    " from Profile p WHERE p.status.nameDr='برحال' and p.commission.id=:proId and p.deleted is not true GROUP BY p.originalProvince.nameDr ORDER BY p.originalProvince.nameDr ASC")
    List<MapData> getMapDataByCommission(@Param("proId") long proId);


}