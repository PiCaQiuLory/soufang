package com.lory.soufang.repository;

import com.lory.soufang.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Integer> {

    User findByName(String userName);
}
