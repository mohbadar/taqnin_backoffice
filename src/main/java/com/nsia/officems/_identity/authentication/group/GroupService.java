package com.nsia.officems._identity.authentication.group;

import java.util.List;

import com.nsia.officems._identity.authentication.user.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface GroupService {

    public Group create(Group group);

    public List<Group> delete(Long id);

    public List<Group> findAll();

    public List<Group> findByIdNotIn(List<Long> groupIds);

    public Group findById(Long id);

    public boolean update(Long id, Group group);
}
