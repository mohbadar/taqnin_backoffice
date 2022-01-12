package com.nsia.officems.gop.political_party;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Long> {
    public List<PoliticalParty> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByIdDesc(Long id);

    public PoliticalParty findFirstByType(String type);

}
