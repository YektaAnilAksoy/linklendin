package com.yektaanil.linklendin.service.user;

import com.yektaanil.linklendin.dto.user.AuthRequestDTO;
import com.yektaanil.linklendin.dto.user.AuthResponseDTO;
import com.yektaanil.linklendin.dto.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
public interface UserService extends UserDetailsService {

    UserDTO save(UserDTO userDTO);

    AuthResponseDTO login(AuthRequestDTO authRequestDTO);
}
