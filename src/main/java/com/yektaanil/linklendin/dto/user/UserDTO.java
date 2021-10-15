package com.yektaanil.linklendin.dto.user;

import com.yektaanil.linklendin.entity.Link;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private List<Link> links;
    private String password;
}
