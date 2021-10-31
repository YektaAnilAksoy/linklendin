package com.yektaanil.linklendin.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author : Yekta Anil AKSOY
 * @since : 21.10.2021
 **/
public final class TestUtil {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
