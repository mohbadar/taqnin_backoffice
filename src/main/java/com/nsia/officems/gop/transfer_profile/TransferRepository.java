package com.nsia.officems.gop.transfer_profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    public List<Transfer> findByProfile_idAndDeletedIsFalseOrDeletedIsNullOrderByMaktubDateDesc(Long id);

}
