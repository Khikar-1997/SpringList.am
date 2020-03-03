package com.example.demo.controller;

import com.example.demo.user.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public void create(@RequestBody User user){
        userService.create(user);
    }

    @GetMapping(value = "/user")
    public void selectAllUsers(){
        for (User user : userService.findAllUsers()) {
            System.out.println("name :" + user.getName() + " ," +
                    "surname :" + user.getSurname() + " ," +
                    "age :" + user.getAge() + " ," + "username :"
                    + user.getUsername() + " ," + "password :" +
                    user.getPassword());
        }
    }

    @GetMapping(value = "/user/{id}")
    public void findUserById(@PathVariable int id){
        System.out.println(userService.findUserById(id));
    }

    @PutMapping(value = "user/{id}")
    public @ResponseBody void update(@PathVariable int id, @RequestBody User user){
        userService.update(id,user);
    }

    @DeleteMapping(value = "/user/{id}")
    public void delete(@PathVariable int id){
        userService.delete(id);
    }

    @GetMapping(value = "/user/login/{username}/{password}")
    public void login(@PathVariable String username, @PathVariable String password){
        System.out.println(userService.login(username, password));
    }
}
