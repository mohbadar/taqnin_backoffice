package com.nsia.officems._admin.country;
import java.util.List;

public interface CountryService {
    public List<Country> findAll();
    public Country findById(Long id);
    public Country create(Country country);
    public Boolean delete(Long id);
    public Country update(Long id, Country country);
}