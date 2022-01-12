package com.nsia.officems._identity.authentication.group;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.nsia.officems._identity.authentication.role.Role;
import com.nsia.officems._identity.authentication.role.RoleService;
import com.nsia.officems._identity.authentication.user.UserService;

/**
 * 
 * Class for CRUD operation on Group
 *
 */

@RestController
@RequestMapping("/api/groups")

public class GroupController {

	Logger logger = LoggerFactory.getLogger(GroupController.class);
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService UserService;

	@Autowired
	private GroupAuthService groupAuthService;

	@GetMapping()
	public List<Group> groups() {
		// fetch all the groups and
		// String envSlug = UserService.getCurrentEnv();
		return groupAuthService.findAll();
	}

	@PostMapping()
	public Group create(@Valid @RequestBody String group) {

		Gson gson = new Gson();
		Group newGroup = gson.fromJson(group, Group.class);
		return groupAuthService.create(newGroup);
	}

	@GetMapping(path = "/{id}")
	public ObjectNode getGroupById(@PathVariable(value = "id") Long id) {
		// given the group, find it from database and return it
		Group group = groupAuthService.findById(id);
		List<Role> allRoles = roleService.findAll();
		ArrayNode allRolesTree = mapper.valueToTree(allRoles);
		JsonNode groupJson = mapper.convertValue(group, JsonNode.class);
		JsonNode rolesJson = mapper.convertValue(group.getRoles(), JsonNode.class);
		ObjectNode groupObj = mapper.createObjectNode();
		groupObj.set("group", groupJson);
		groupObj.set("roles", rolesJson);
		groupObj.set("allRoles", allRolesTree);
		return groupObj;
	}

	@PutMapping(path = "/{id}")
	public boolean updateGroupById(@PathVariable(value = "id") Long id, @Valid @RequestBody String groupString) {
		Gson gson = new Gson();
		Group group = gson.fromJson(groupString, Group.class);
		return groupAuthService.update(id, group);
	}
}
