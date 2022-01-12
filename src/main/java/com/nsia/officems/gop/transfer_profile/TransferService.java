package com.nsia.officems.gop.transfer_profile;

import java.util.List;

import com.nsia.officems.gop.transfer_profile.dto.TransferDto;

public interface TransferService {
    public List<Transfer> findAll();
    public Transfer findById(Long id);
    public Transfer create(TransferDto dto);
    public Boolean delete(Long id);
    public Boolean update(Long id, TransferDto dto); 
    public List<Transfer> findByProfile_id(Long id);
}
