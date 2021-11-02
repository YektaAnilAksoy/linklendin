package com.yektaanil.linklendin.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yektaanil.linklendin.dto.user.AuthRequestDTO;
import com.yektaanil.linklendin.dto.user.UserDTO;
import com.yektaanil.linklendin.exception.ExceptionConstants;
import com.yektaanil.linklendin.exception.UserAlreadyTakenException;
import com.yektaanil.linklendin.service.user.UserService;
import com.yektaanil.linklendin.utility.EntityTestUtility;
import com.yektaanil.linklendin.utility.TestUtil;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author : Yekta Anil AKSOY
 * @since : 21.10.2021
 **/

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    private static final String URI = "/api/v1/user";
    private static final String signupURI = URI + "/signup";
    private static final String loginURI = URI + "/login";

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void signup_success() throws Exception {
        //Setup mocked service
        UserDTO mockedUserDTO = EntityTestUtility.getUserDTO();
        when(userService.save(any(UserDTO.class))).thenReturn(mockedUserDTO);

        // Execute the POST request
        mockMvc.perform(post(signupURI)
                        .content(TestUtil.asJsonString(mockedUserDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate headers
                //.andExpect(header().string(HttpHeaders.LOCATION, signupURI))
                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(mockedUserDTO.getId().intValue())))
                .andExpect(jsonPath("$.username", is(mockedUserDTO.getUsername())))
                .andExpect(jsonPath("$.name", is(mockedUserDTO.getName())))
                .andExpect(jsonPath("$.surname", is(mockedUserDTO.getSurname())))
                .andExpect(jsonPath("$.email", is(mockedUserDTO.getEmail())));
    }

    @Test
    void signup_fail_when_username_or_email_in_use() throws Exception {
        //Setup mocked service
        UserDTO mockedUserDTO = EntityTestUtility.getUserDTO();
        when(userService.save(any(UserDTO.class))).thenThrow(
                new UserAlreadyTakenException(ExceptionConstants.USER_ALREADY_TAKEN));

        // Execute the POST request
        mockMvc.perform(post(signupURI)
                        .content(TestUtil.asJsonString(mockedUserDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                // Validate the response code and content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate headers
                //.andExpect(header().string(HttpHeaders.LOCATION, signupURI))
                // Validate the returned fields
                .andExpect(jsonPath("$.message", is(ExceptionConstants.USER_ALREADY_TAKEN)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void login_success() throws Exception {
        //Setup mocked service
        AuthRequestDTO mockedAuthRequestDTO = EntityTestUtility.getAuthRequestDTO();
        when(userService.login(any(AuthRequestDTO.class))).thenReturn(
                EntityTestUtility.getAuthResponseDTO());

        // Execute the POST request
        mockMvc.perform(post(loginURI)
                        .content(TestUtil.asJsonString(mockedAuthRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate headers
                //.andExpect(header().string(HttpHeaders.LOCATION, signupURI))
                // Validate the returned fields
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void login_fail() throws Exception {
        //Setup mocked service
        AuthRequestDTO mockedAuthRequestDTO = EntityTestUtility.getAuthRequestDTO();
        when(userService.login(any(AuthRequestDTO.class))).thenThrow(new EntityNotFoundException(
                ExceptionConstants.USER_NOT_FOUND));

        // Execute the POST request
        mockMvc.perform(post(loginURI)
                        .content(TestUtil.asJsonString(mockedAuthRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                // Validate the response code and content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate headers
                //.andExpect(header().string(HttpHeaders.LOCATION, signupURI))
                // Validate the returned fields
                .andExpect(jsonPath("$.message", is(ExceptionConstants.USER_NOT_FOUND)))
                .andExpect(status().isBadRequest());
    }
}
