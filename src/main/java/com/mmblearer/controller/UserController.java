package com.mmblearer.controller;

import com.mmblearer.dto.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mukesh Bhoge
 **/

@RestController
@RequestMapping("/users")
public class UserController {

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = {"/all"} , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsers(){

        User user =  new User(1234,"mukesh","mukesh@gmail.com");
        List<User> userList =  new ArrayList<>();
        userList.add(user);
        System.out.println("Fetching All Users");
        ResponseEntity<List<User>> responseEntity = ResponseEntity.ok(userList);
        return  responseEntity;
    }

    @PostMapping(value = {"/create"} , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User user){
        System.out.println("Creating user");
        return ResponseEntity.accepted().body(user);
    }

}
