package com.nsia.officems.gop.position.impl;

import com.nsia.officems.gop.position.Position;
import com.nsia.officems.gop.position.PositionRepository;
import com.nsia.officems.gop.position.PositionService;


import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService{
    @Autowired
    private PositionRepository positionRepository;
    
    @Override
    public List<Position> findAll() {
        return positionRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Position findById(Long id) {
        Optional<Position> optionalObj = positionRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Position create(Position position) {
        position.setDeleted(false);
        return positionRepository.save(position);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Position> position = positionRepository.findById(id);

        if (position.isPresent()) {
            Position position2 = position.get();
            position2.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            position2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            positionRepository.save(position2);
            return true;
        }

        return false;
    }

}
