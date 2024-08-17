package br.rnp.redesegura.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private PasswordUtils(){}

    public static String encode(String password){
        return encoder.encode(password);
    }

    public static boolean matches(String rawPassowrd, String encodedPassword){
        return encoder.matches(rawPassowrd, encodedPassword);
    }

}
