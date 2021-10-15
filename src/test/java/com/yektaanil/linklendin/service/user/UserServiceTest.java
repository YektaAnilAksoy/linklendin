package com.yektaanil.linklendin.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.yektaanil.linklendin.config.ModelMapperConfig;
import com.yektaanil.linklendin.dto.user.UserDTO;
import com.yektaanil.linklendin.entity.User;
import com.yektaanil.linklendin.repository.UserRepository;
import com.yektaanil.linklendin.utility.EntityTestUtility;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

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

    @InjectMocks
    private UserService userService = new UserServiceImpl(modelMapper, userRepository);

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
    void get_success() {
        final User user = EntityTestUtility.getUser();
        final UserDTO userDTO = EntityTestUtility.getUserDTO();

        when(modelMapper.map(any(User.class), any())).thenReturn(
                userDTO);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        UserDTO serviceRespDTO = userService.get(userDTO);

        assertEquals(userDTO.getId(), serviceRespDTO.getId());
        assertEquals(userDTO.getUsername(), serviceRespDTO.getUsername());
    }
}
