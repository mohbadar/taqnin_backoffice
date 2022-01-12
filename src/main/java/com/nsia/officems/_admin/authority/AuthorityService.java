package com.nsia.officems._admin.authority;

import java.util.List;

public interface AuthorityService {
    public List<Authority> findAll();

    public Authority findById(Long id);

    public Authority create(Authority authority);

    public Boolean delete(Long id);

    public boolean update(Long id, Authority authority);
}
