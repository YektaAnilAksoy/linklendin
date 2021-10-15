package com.yektaanil.linklendin.utility;

import com.yektaanil.linklendin.dto.user.UserDTO;
import com.yektaanil.linklendin.entity.User;
import java.time.LocalDateTime;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
public final class EntityTestUtility {

    public final static LocalDateTime now = LocalDateTime.now();

    public static User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("yaaksoy");
        user.setName("yekta anil");
        user.setSurname("aksoy");
        user.setCreateDate(now);
        user.setStatus(1);
        return user;
    }

    public static UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(getUser().getUsername());
        userDTO.setName(getUser().getName());
        userDTO.setId(getUser().getId());
        userDTO.setLinks(getUser().getLinks());
        userDTO.setUsername(getUser().getUsername());
        return userDTO;
    }
}
