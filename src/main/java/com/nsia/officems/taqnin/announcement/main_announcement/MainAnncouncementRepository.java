package com.nsia.officems.taqnin.announcement.main_announcement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainAnncouncementRepository extends JpaRepository<MainAnnouncement, Long> {

    @Query(value = "SELECT * FROM public.taqnin_main_announcement LIMIT 1", nativeQuery = true)
    public MainAnnouncement getMainAnnouncement();

}
