package com.nsia.officems.gop.Profile_checklist;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileChecklistRepository extends JpaRepository<ProfileChecklist, Long> {
    public List<ProfileChecklist> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<ProfileChecklist> findByProfile_id(Long id);
    public ProfileChecklist findByProfile_idAndTitle(Long id, String title);

}
