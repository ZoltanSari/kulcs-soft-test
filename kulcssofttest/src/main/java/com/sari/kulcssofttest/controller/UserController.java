package com.sari.kulcssofttest.controller;

import com.sari.kulcssofttest.dto.AdminDTO;
import com.sari.kulcssofttest.model.Admin;
import com.sari.kulcssofttest.model.User;
import com.sari.kulcssofttest.service.AdminService;
import com.sari.kulcssofttest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;


    @PostMapping("/registration")
    public void adminRegistration(@RequestBody AdminDTO adminDTO) {
        this.adminService.registration(adminDTO);
    }

    @PostMapping("/login")
    public Admin login(@RequestBody Map<String, String> requestJson) {
        return this.adminService.login(requestJson.get("username"), requestJson.get("password"));
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/users/add-user")
    public void registration(@RequestBody AdminDTO adminDTO) {
        userService.addUser(adminDTO);
    }
}
