package com.sari.kulcssofttest.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
