package com.nsia.officems.taqnin.announcement;

import com.nsia.officems._admin.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long>{
    public List<Announcement> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    @Query(value = "select * from public.announcement where deleted is not true order by id desc", nativeQuery = true)
    public List<Announcement> findAllOrderByIdDesc();

    @Query(value = "select count(*) from public.announcement where deleted is not true", nativeQuery = true)
    public Integer getCount();

    @Query(value = "select * from public.announcement where deleted is not true and title Like %:query%", nativeQuery = true)
    public List<Announcement> findAllFilteredAnnouncements(@Param("query") String query);

    @Query(value = "select * from public.announcement where deleted is not true order by id desc limit 8", nativeQuery = true)
    public List<Announcement> findLastTenRecords();

}
