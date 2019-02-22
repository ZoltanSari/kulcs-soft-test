package com.sari.kulcssofttest.service;

import com.sari.kulcssofttest.dto.AdminDTO;
import com.sari.kulcssofttest.model.User;
import com.sari.kulcssofttest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(AdminDTO adminDTO) {
        User user = User.builder()
                .name(adminDTO.getUsername())
                .email(adminDTO.getEmail()).build();

        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Integer id) {
        userRepository.removeUserById(id);
    }
}
