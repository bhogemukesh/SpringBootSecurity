package com.mmblearer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mukesh Bhoge
 **/
@RestController
@RequestMapping("/public")
public class HomeController {

    @GetMapping(value = {"register"})
    public String register(){
        return "register Methods will be accessible to all users";
    }

    @GetMapping(value = {"home"})
    public String home(){
        return "home Methods will be accessible to all users";
    }
}
