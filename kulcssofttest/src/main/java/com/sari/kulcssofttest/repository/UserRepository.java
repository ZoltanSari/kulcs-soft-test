package com.sari.kulcssofttest.repository;

import com.sari.kulcssofttest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
