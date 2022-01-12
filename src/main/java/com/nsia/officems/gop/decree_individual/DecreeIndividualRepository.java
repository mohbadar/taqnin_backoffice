package com.nsia.officems.gop.decree_individual;

import java.util.List;

import com.nsia.officems.gop.decree.Decree;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DecreeIndividualRepository extends JpaRepository<DecreeIndividual, Long> {
    List<DecreeIndividual> findByDecree(Decree decree);
}