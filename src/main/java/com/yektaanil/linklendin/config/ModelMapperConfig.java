package com.yektaanil.linklendin.config;

import com.yektaanil.linklendin.dto.link.LinkDTO;
import com.yektaanil.linklendin.dto.user.UserDTO;
import com.yektaanil.linklendin.entity.Link;
import com.yektaanil.linklendin.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
@Configuration
public class ModelMapperConfig {

    @Bean
    @Primary
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(UserDTO.class, User.class)
                .addMappings(mapper -> mapper.skip(User::setLinks));
        modelMapper.typeMap(User.class, UserDTO.class)
                .addMappings(mapper -> mapper.skip(UserDTO::setPassword));
        PropertyMap<LinkDTO, Link> linkDTOToLink = new PropertyMap<>() {
            protected void configure() {
                map().getUser().setId(source.getUserId());
            }
        };

        modelMapper.addMappings(linkDTOToLink);
        return modelMapper;
    }
}
