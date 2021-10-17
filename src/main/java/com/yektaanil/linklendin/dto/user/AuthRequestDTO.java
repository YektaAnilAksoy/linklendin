package com.yektaanil.linklendin.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 17.10.2021
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {

    private String username;
    private String password;
}
