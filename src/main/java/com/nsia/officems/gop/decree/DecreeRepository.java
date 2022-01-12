package com.nsia.officems.gop.decree;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

public interface DecreeRepository extends JpaRepository<Decree, Long>, RevisionRepository<Decree, Long, Integer> {

    public List<Decree> findAll();

    Decree findByDecreeNumber(String decreeNumber);

    @Query(value = "select count(*) from public.decree where deleted is not true", nativeQuery = true)
    public long count();
}