package com.nsia.officems._admin.commission.impl;

import com.nsia.officems._admin.commission.Commission;
import com.nsia.officems._admin.commission.CommissionRepository;
import com.nsia.officems._admin.commission.CommissionService;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionServiceImpl implements CommissionService {
    @Autowired
    private CommissionRepository commissionRepository;

    @Override
    public List<Commission> findAll() {
        return commissionRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Commission findById(Long id) {
        Optional<Commission> optionalObj = commissionRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Commission create(Commission commission) {
        commission.setDeleted(false);
        return commissionRepository.save(commission);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Commission> commission = commissionRepository.findById(id);

        if (commission.isPresent()) {
            Commission commission2 = commission.get();
            commission2.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            commission2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            commissionRepository.save(commission2);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, Commission commission) {
        Optional<Commission> commissionToBeUpdated = commissionRepository.findById(id);
        if (commissionToBeUpdated.isEmpty()) {
            return false;
        }

        Commission editedCommission = commissionToBeUpdated.get();

        editedCommission.setNameEn(commission.getNameEn());
        editedCommission.setNameDr(commission.getNameDr());
        editedCommission.setNamePs(commission.getNamePs());

        commissionRepository.save(editedCommission);
        return true;
    }
}
