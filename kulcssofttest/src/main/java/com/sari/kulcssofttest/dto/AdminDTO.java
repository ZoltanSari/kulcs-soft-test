package com.sari.kulcssofttest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDTO {

    private String username;
    private String password;
    private String email;
}
