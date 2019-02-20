package com.sari.kulcssofttest.controller;

import com.sari.kulcssofttest.dto.UserDTO;
import com.sari.kulcssofttest.model.User;
import com.sari.kulcssofttest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/registration")
    public void registration(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }
}
