package com.nsia.officems.gop.resignation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResignationRepository extends JpaRepository<Resignation, Long>  {
    
}