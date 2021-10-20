package com.yektaanil.linklendin.controller;

import com.yektaanil.linklendin.dto.user.AuthRequestDTO;
import com.yektaanil.linklendin.dto.user.AuthResponseDTO;
import com.yektaanil.linklendin.dto.user.UserDTO;
import com.yektaanil.linklendin.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Yekta Anil AKSOY
 * @since : 19.10.2021
 **/
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(
            @RequestBody AuthRequestDTO authRequestDTO) {
        return new ResponseEntity<>(userService.login(authRequestDTO),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> createUser(
            @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.save(userDTO),
                HttpStatus.OK);
    }
}
