package com.lory.soufang.security;

import com.lory.soufang.entity.User;
import com.lory.soufang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();
        User user =  userService.loadUserByUsername(userName);
        if (user == null){
            throw new AuthenticationCredentialsNotFoundException("authError");
        }
        if (bCryptPasswordEncoder.matches(inputPassword, user.getPassword())){
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        throw new BadCredentialsException("auth error");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
