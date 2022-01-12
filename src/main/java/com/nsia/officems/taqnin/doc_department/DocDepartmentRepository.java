package com.nsia.officems.taqnin.doc_department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocDepartmentRepository extends JpaRepository<DocDepartment,Long> {
    public List<DocDepartment> findByDocument_idAndDeletedFalse(Long id);

}
