package com.yektaanil.linklendin.service.user;

import com.yektaanil.linklendin.dto.user.AuthRequestDTO;
import com.yektaanil.linklendin.dto.user.AuthResponseDTO;
import com.yektaanil.linklendin.dto.user.UserDTO;
import com.yektaanil.linklendin.entity.User;
import com.yektaanil.linklendin.exception.ExceptionConstants;
import com.yektaanil.linklendin.exception.UserAlreadyTakenException;
import com.yektaanil.linklendin.repository.UserRepository;
import com.yektaanil.linklendin.util.JwtUtil;
import java.util.ArrayList;
import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtTokenUtil;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            AuthenticationManager authenticationManager,
            JwtUtil jwtTokenUtil) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        if (userRepository.existsByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail())) {
            throw new UserAlreadyTakenException(ExceptionConstants.USER_ALREADY_TAKEN);
        }
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        return modelMapper.map(userRepository.save(modelMapper.map(userDTO, User.class)),
                UserDTO.class);
    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(),
                        authRequestDTO.getPassword()));

        final UserDetails userDetails = loadUserByUsername(authRequestDTO.getUsername());
        return new AuthResponseDTO(jwtTokenUtil.generateToken(userDetails));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionConstants.USER_NOT_FOUND));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }


}
