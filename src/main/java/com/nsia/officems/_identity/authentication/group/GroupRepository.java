package com.nsia.officems._identity.authentication.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    public List<Group> findByIdNotIn(List<Long> groupIds);
}
