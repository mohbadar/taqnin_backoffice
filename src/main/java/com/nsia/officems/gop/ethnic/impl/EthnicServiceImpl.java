package com.nsia.officems.gop.ethnic.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems.gop.ethnic.Ethnic;
import com.nsia.officems.gop.ethnic.EthnicRepository;
import com.nsia.officems.gop.ethnic.EthnicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EthnicServiceImpl implements EthnicService {

    @Autowired
    private EthnicRepository ethnicRepository;

    // @Autowired
    // UserService userService;

    @Override
    public List<Ethnic> findAll() {
        return ethnicRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Ethnic findById(Long id) {
        Optional<Ethnic> optionalObj = ethnicRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Ethnic create(Ethnic ethnic) {
        ethnic.setDeleted(false);
        return ethnicRepository.save(ethnic);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Ethnic> oEthnic = ethnicRepository.findById(id);

        if (oEthnic.isPresent()) {
            Ethnic ethnic = oEthnic.get();
            ethnic.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            ethnic.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            ethnicRepository.save(ethnic);
            return true;
        }

        return false;
    }
}