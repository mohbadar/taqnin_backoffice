package com.nsia.officems._identity.authentication.user;

import java.util.List;

import javax.transaction.Transactional;

import com.nsia.officems._identity.authentication.group.Group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//JpaRepository<User, Integer> : User is the entity type and Integer is the primary key.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.avatar = ?2 where u.username = ?1")
    int updateAvatar(String username, String avatar);

    @Query("SELECT new com.nsia.officems._identity.authentication.group.Group(g.id, g.name, g.description, g.active) from User u INNER JOIN u.groups g where u.id = ?1")
    List<Group> findAllGroupsByUser(Long userId, String envSlug);

}
