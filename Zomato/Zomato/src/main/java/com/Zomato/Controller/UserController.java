package com.Zomato.Controller;

import com.Zomato.Entity.User;
import com.Zomato.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);

    }
    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @GetMapping("/{id}")
    public User getByIdUser(@PathVariable Long id){
        return userService.getByIdUser(id);
    }
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user){
      return userService.update(id,user);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        userService.delete(id);
        return "User delete successfully...!";

    }
}
