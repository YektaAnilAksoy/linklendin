package com.yektaanil.linklendin.dto.exception;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 17.10.2021
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO {

    private Date timestamp;
    private String message;
    private String details;
}
