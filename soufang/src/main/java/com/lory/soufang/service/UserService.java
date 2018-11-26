package com.lory.soufang.service;


import com.lory.soufang.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    User loadUserByUsername(String userName) throws UsernameNotFoundException;
}
