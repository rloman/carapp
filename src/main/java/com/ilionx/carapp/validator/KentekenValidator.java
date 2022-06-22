package com.ilionx.carapp.validator;

import org.springframework.stereotype.Component;

@Component
public class KentekenValidator {

    public static final String REGEXP_KENTEKEN_FORMAT;

    // so NOT the characters mentioned below (^ = NOT in regexp in a range)
    private static final String VALID_LETTERS = "[^AaEeIiOoCcQqUu0-9]";

    // A valid licensePlate in the NL matches the following regular expression
    static {
        REGEXP_KENTEKEN_FORMAT = String.format("\\d-%1$s{2}-\\d{3}|\\d-%1$s{3}-\\d{2}|"
                + "\\d{2}-\\d{2}-%1$s{2}|\\d{2}-%1$s{2}-\\d{2}|\\d{2}-%1$s{2}-%1$s{2}|\\d{2}-%1$s{3}-\\d|"
                + "\\d{3}-%1$s{2}-\\d|%1$s-\\d{2}-%1$s{3}|%1$s-\\d{3}-%1$s{2}|%1$s{2}-\\d{2}-\\d{2}|%1$s{2}-\\d{2}-%1$s{2}|"
                + "%1$s{2}-\\d{3}-%1$s|%1$s{2}-%1$s{2}-\\d{2}|%1$s{3}-\\d{2}-%1$s", VALID_LETTERS);
    }
}
