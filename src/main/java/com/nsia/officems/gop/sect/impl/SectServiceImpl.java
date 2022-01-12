package com.nsia.officems.gop.sect.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems.gop.sect.Sect;
import com.nsia.officems.gop.sect.SectRepository;
import com.nsia.officems.gop.sect.SectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectServiceImpl implements SectService{

    
    @Autowired
    private SectRepository sectRepository;
    
    @Override
    public List<Sect> findAll() {
        return sectRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Sect findById(Long id) {
        Optional<Sect> optionalObj = sectRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Sect create(Sect sect) {
        sect.setDeleted(false);
        return sectRepository.save(sect);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Sect> sect = sectRepository.findById(id);

        if (sect.isPresent()) {
            Sect sect2 = sect.get();
            sect2.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            sect2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            sectRepository.save(sect2);
            return true;
        }

        return false;
    }
    
}
