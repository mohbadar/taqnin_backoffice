package com.nsia.officems.gop.political_party;

import java.util.List;

public interface PoliticalPartyService {
    public List<PoliticalParty> findAll();

    public PoliticalParty findById(Long id);

    public PoliticalParty create(String data);

    public Boolean delete(Long id);

    public Boolean update(Long id, String data);

    public List<PoliticalParty> findByProfile_id(Long id);

    public Boolean typeExists(String type);
}
