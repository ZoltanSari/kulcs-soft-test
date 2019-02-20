package com.sari.kulcssofttest.repository;

import com.sari.kulcssofttest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    void removeUserById(Integer id);
}
