package com.lory.soufang.repository;

import com.lory.soufang.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    List<Role> findRolesByUserId(Integer userId);

}
