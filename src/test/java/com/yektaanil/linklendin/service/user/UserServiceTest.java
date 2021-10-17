package com.yektaanil.linklendin.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.yektaanil.linklendin.config.ModelMapperConfig;
import com.yektaanil.linklendin.dto.user.AuthRequestDTO;
import com.yektaanil.linklendin.dto.user.AuthResponseDTO;
import com.yektaanil.linklendin.dto.user.UserDTO;
import com.yektaanil.linklendin.entity.User;
import com.yektaanil.linklendin.exception.UserAlreadyTakenException;
import com.yektaanil.linklendin.repository.UserRepository;
import com.yektaanil.linklendin.util.JwtUtil;
import com.yektaanil.linklendin.utility.EntityTestUtility;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = ModelMapperConfig.class
)
class UserServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @SpyBean//Mock
    private JwtUtil jwtTokenUtil;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserService userService = new UserServiceImpl(modelMapper, userRepository,
            bCryptPasswordEncoder, authenticationManager, jwtTokenUtil);

    @Test
    void save_success() {
        final User user = EntityTestUtility.getUser();
        final UserDTO userDTO = EntityTestUtility.getUserDTO();

        when(modelMapper.map(any(User.class), any())).thenReturn(
                userDTO);
        when(modelMapper.map(any(UserDTO.class), any())).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDTO serviceRespDTO = userService.save(userDTO);

        assertEquals(userDTO.getId(), serviceRespDTO.getId());
        assertEquals(userDTO.getUsername(), serviceRespDTO.getUsername());
    }

    @Test
    void save_fail_when_user_already_taken() {
        final UserDTO userDTO = EntityTestUtility.getUserDTO();
        when(userRepository.existsByUsernameOrEmail(anyString(), anyString())).thenReturn(true);
        assertThrows(UserAlreadyTakenException.class, () -> userService.save(userDTO));
    }

    @Test
    void login_success() {
        final User user = EntityTestUtility.getUser();
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.of(user));
        final AuthResponseDTO authResponseDTO = userService.login(
                new AuthRequestDTO(user.getUsername(), user.getPassword()));

        assertNotNull(authResponseDTO.getToken(), authResponseDTO.getToken() + "is null");
    }

    @Test
    void login_fail_when_there_is_no_user() {
        final User user = EntityTestUtility.getUser();
        when(userRepository.findUserByUsername(anyString())).thenThrow(
                EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> userService.login(
                new AuthRequestDTO(user.getUsername(), user.getPassword())));
    }

    @Test
    void login_fail_when_credentials_incorrect() {
        final User user = EntityTestUtility.getUser();
        when(authenticationManager.authenticate(any())).thenThrow(BadCredentialsException.class);

        assertThrows(BadCredentialsException.class, () -> userService.login(
                new AuthRequestDTO("userX", "passX")));
    }
}
