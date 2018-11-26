package com.lory.soufang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/404")
    public String notFoundPage(){
        return "404";
    }

    @GetMapping("/500")
    public String internalServerError(){
        return "500";
    }

    @GetMapping("/403")
    public String accessError(){
        return "403";
    }

    @GetMapping("/logout/page")
    public String logoutPage(){
        return "logout";
    }

    @GetMapping("/admin/login")
    public String adminLogin(){
        return "admin/login";
    }
}
