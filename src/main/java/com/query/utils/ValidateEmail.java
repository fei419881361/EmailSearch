package com.query.utils;

import org.junit.Test;

/**
 * Created by exphuhong on 17-10-17.
 * Start
 */
public class ValidateEmail {
    public static boolean EmailFormat(String email) {
        final String str_pattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (email.matches(str_pattern)) {
            return true;
        }
        return false;
    }
}
