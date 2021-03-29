package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.daos.UserRepository;
import com.talentpath.shamazin.showItemPage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/userdata")
public class UserDataController {
    @Autowired
    UserRepository userRepo;

    @GetMapping("/test")
    public String testUserDataController(){
        return "should not see this without role ADMIN!";
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        //add valid if not matching user ID
        return userRepo.getOne(id);
    }

    @PostMapping("/")
    public User addUser(@RequestBody User newUser){
        return userRepo.saveAndFlush(newUser);
    }

    @PutMapping("/")
    public User editUser(@RequestBody User editedUser){
        //saveAndFlush can both save and edit dep. on if finds matching ID
        return userRepo.saveAndFlush(editedUser);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Integer id){
        userRepo.deleteById(id);
        return true;
    }
}
