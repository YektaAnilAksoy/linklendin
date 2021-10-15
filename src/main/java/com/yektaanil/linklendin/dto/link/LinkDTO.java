package com.yektaanil.linklendin.dto.link;

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
public class LinkDTO {

    private Long id;
    private String url;
    private String title;
    private Long userId;
}
