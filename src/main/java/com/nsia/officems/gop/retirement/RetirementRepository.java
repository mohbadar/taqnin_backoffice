package com.nsia.officems.gop.retirement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetirementRepository extends JpaRepository<Retirement, Long>  {
    
}
