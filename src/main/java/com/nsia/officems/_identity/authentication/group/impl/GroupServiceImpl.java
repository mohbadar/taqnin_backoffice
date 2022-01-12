package com.nsia.officems._identity.authentication.group.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._identity.authentication.group.Group;
import com.nsia.officems._identity.authentication.group.GroupRepository;
import com.nsia.officems._identity.authentication.group.GroupService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Group create(Group group) {

		return groupRepository.saveAndFlush(group);
	}

	@Override
	public List<Group> delete(Long id) {

		Optional<Group> group = groupRepository.findById(id);
		if (group.isPresent()) {
			groupRepository.delete(group.get());
			groupRepository.flush();
		}

		return groupRepository.findAll();

	}

	@Override
	public List<Group> findAll() {
		return groupRepository.findAll();
	}

	@Override
	public List<Group> findByIdNotIn(List<Long> groupIds) {
		return groupRepository.findByIdNotIn(groupIds);
	}

	@Override
	public Group findById(Long id) {

		return groupRepository.findById(id).get();
	}

	@Override
	public boolean update(Long id, Group group) {
		if (id != null) {
			group.setId(id);
		}
		groupRepository.save(group);
		return true;
	}

}
