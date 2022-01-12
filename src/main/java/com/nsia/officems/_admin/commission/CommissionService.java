package com.nsia.officems._admin.commission;

import java.util.List;

public interface CommissionService {
    public List<Commission> findAll();

    public Commission findById(Long id);

    public Commission create(Commission commission);

    public Boolean delete(Long id);

    public boolean update(Long id, Commission commission);
}
