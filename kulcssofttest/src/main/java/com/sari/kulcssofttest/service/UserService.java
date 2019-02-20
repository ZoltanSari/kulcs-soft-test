package com.sari.kulcssofttest.service;

import com.sari.kulcssofttest.model.User;
import com.sari.kulcssofttest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(String username, String email) {
        User user = User.builder()
                .name(username)
                .email(email).build();

        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
