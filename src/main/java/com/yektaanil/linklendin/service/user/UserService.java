package com.yektaanil.linklendin.service.user;

import com.yektaanil.linklendin.dto.user.UserDTO;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
public interface UserService {

    UserDTO save(UserDTO userDTO);

    UserDTO get(UserDTO userDTO);
}
