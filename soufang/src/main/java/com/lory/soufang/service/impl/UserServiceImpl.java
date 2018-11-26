package com.lory.soufang.service.impl;

import com.lory.soufang.entity.Role;
import com.lory.soufang.entity.User;
import com.lory.soufang.repository.RoleRepository;
import com.lory.soufang.repository.UserRepository;
import com.lory.soufang.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByName(userName);
        if (user == null){
            return  null;
        }
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (CollectionUtils.isEmpty(roles)){
            throw new DisabledException("权限非法");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }
}
