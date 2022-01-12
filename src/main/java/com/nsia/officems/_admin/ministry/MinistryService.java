package com.nsia.officems._admin.ministry;

import java.util.List;

public interface MinistryService {
    public List<Ministry> findAll();

    public Ministry findById(Long id);

    public Ministry create(Ministry ministry);

    public Boolean delete(Long id);

    public boolean update(Long id, Ministry ministry);

}
