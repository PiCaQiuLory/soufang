package com.lory.soufang.normal;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class NormalTest {


    @Test
    public void testBcrypt(){
        String a = "cuoai1995";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String res = passwordEncoder.encode(a);
        System.out.println(passwordEncoder.matches(a, res));
        System.out.println(res);
    }
}
