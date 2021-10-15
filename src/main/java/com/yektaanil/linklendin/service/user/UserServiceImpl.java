package com.yektaanil.linklendin.service.user;

import com.yektaanil.linklendin.dto.user.UserDTO;
import com.yektaanil.linklendin.entity.User;
import com.yektaanil.linklendin.repository.UserRepository;
import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return modelMapper.map(userRepository.save(modelMapper.map(userDTO, User.class)),
                UserDTO.class);
    }

    @Override
    public UserDTO get(UserDTO userDTO) {

        return modelMapper.map(userRepository.findById(userDTO.getId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                String.format("userId: %d not found", userDTO.getId()))),
                UserDTO.class);
    }
}
