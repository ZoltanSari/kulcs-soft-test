package com.sari.kulcssofttest.service;

import com.sari.kulcssofttest.dto.AdminDTO;
import com.sari.kulcssofttest.model.Admin;
import com.sari.kulcssofttest.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Admin login(String name, String password) {
        Admin user = this.adminRepository.findByUsername(name);
        if (user != null && user.getPassword().equals(password)) return user;

        return null;
    }

    public boolean registration(AdminDTO adminDTO) {
        if (this.checkIsAdminNameAlreadyExist(adminDTO.getUsername())) return false;

        Admin newAdmin = Admin.builder()
                .username(adminDTO.getUsername())
                .password(this.bCryptPasswordEncoder.encode(adminDTO.getPassword()))
                .build();
        this.adminRepository.save(newAdmin);

        return true;
    }

    private boolean checkIsAdminNameAlreadyExist(String adminName) {
        return this.adminRepository.findByUsername(adminName) != null;
    }
}
