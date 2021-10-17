package com.yektaanil.linklendin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Yekta Anil AKSOY
 * @since : 17.10.2021
 **/
@RestController
public class HelloResource {

    @RequestMapping({"/hello"})
    public String hello() {
        return "Hello World";
    }
}
