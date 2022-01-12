package com.nsia.officems._admin.authority.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.authority.Authority;
import com.nsia.officems._admin.authority.AuthorityRepository;
import com.nsia.officems._admin.authority.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Authority findById(Long id) {
        Optional<Authority> optionalObj = authorityRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Authority create(Authority authority) {
        authority.setDeleted(false);
        return authorityRepository.save(authority);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Authority> authority = authorityRepository.findById(id);

        if (authority.isPresent()) {
            Authority authority2 = authority.get();
            authority2.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            authority2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            authorityRepository.save(authority2);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, Authority authority) {
        Optional<Authority> authorityToBeUpdated = authorityRepository.findById(id);
        if (authorityToBeUpdated.isEmpty()) {
            return false;
        }

        Authority editedAuthority = authorityToBeUpdated.get();

        editedAuthority.setNameEn(authority.getNameEn());
        editedAuthority.setNameDr(authority.getNameDr());
        editedAuthority.setNamePs(authority.getNamePs());

        authorityRepository.save(editedAuthority);
        return true;
    }

}
