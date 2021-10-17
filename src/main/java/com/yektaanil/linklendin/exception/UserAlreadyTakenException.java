package com.yektaanil.linklendin.exception;

/**
 * @author : Yekta Anil AKSOY
 * @since : 18.10.2021
 **/
public class UserAlreadyTakenException extends RuntimeException {

    public UserAlreadyTakenException(final String msg) {
        super(msg);
    }
}
