package com.sari.kulcssofttest.controller;

import com.sari.kulcssofttest.dto.AdminDTO;
import com.sari.kulcssofttest.dto.ErrorDTO;
import com.sari.kulcssofttest.model.Admin;
import com.sari.kulcssofttest.model.User;
import com.sari.kulcssofttest.service.AdminService;
import com.sari.kulcssofttest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/registration")
    public ResponseEntity adminRegistration(@RequestBody AdminDTO adminDTO) {
        if (this.adminService.registration(adminDTO)) return new ResponseEntity(HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorDTO.builder()
                        .error("invalid credentials")
                        .message("This admin name is already exists!")
                        .build());
    }

    @PostMapping("/login")
    public Admin login(@RequestBody Map<String, String> requestJson) {
        return this.adminService.login(requestJson.get("username"), requestJson.get("password"));
    }

    @GetMapping("/admin/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/admin/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/admin/users/add-user")
    public void registration(@RequestBody AdminDTO adminDTO) {
        userService.addUser(adminDTO);
    }
}
