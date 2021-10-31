package com.yektaanil.linklendin.utility;

import com.yektaanil.linklendin.dto.link.LinkDTO;
import com.yektaanil.linklendin.dto.user.AuthRequestDTO;
import com.yektaanil.linklendin.dto.user.AuthResponseDTO;
import com.yektaanil.linklendin.dto.user.UserDTO;
import com.yektaanil.linklendin.entity.Link;
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
        user.setPassword("password");
        user.setCreateDate(now);
        user.setStatus(1);
        user.setEmail("yektaanilaksoy@gmail.com");
        return user;
    }

    public static UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(getUser().getUsername());
        userDTO.setName(getUser().getName());
        userDTO.setId(getUser().getId());
        userDTO.setLinks(getUser().getLinks());
        userDTO.setSurname(getUser().getSurname());
        userDTO.setEmail(getUser().getEmail());
        return userDTO;
    }

    public static Link getLink() {
        Link link = new Link();
        link.setId(1L);
        link.setUser(getUser());
        link.setTitle("Github");
        link.setUrl("www.github.com/yektaanilaksoy");
        return link;
    }

    public static LinkDTO getLinkDTO() {
        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setId(getLink().getId());
        linkDTO.setTitle(getLink().getTitle());
        linkDTO.setUrl(getLink().getUrl());
        linkDTO.setUserId(getLink().getUser().getId());
        return linkDTO;
    }

    public static AuthRequestDTO getAuthRequestDTO() {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername(getUser().getUsername());
        authRequestDTO.setPassword(getUser().getPassword());
        return authRequestDTO;
    }

    public static AuthResponseDTO getAuthResponseDTO() {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO("customToken");
        return authResponseDTO;
    }
}
