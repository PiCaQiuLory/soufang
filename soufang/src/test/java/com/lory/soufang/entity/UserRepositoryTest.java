package com.lory.soufang.entity;

import com.lory.soufang.SoufangApplicationTests;
import com.lory.soufang.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

public class UserRepositoryTest extends SoufangApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne(){
        Optional<User> user = userRepository.findById(1);
        Assert.assertEquals("waliwali", user.get().getName());
    }

}
