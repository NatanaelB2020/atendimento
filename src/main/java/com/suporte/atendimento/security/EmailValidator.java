package com.suporte.atendimento.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@hmdbio\\.com\\.br$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    public static boolean validate(String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}

