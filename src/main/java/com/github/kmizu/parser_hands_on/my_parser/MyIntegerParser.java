package com.github.kmizu.parser_hands_on.my_parser;

import com.github.kmizu.parser_hands_on.ParseFailure;
import com.github.kmizu.parser_hands_on.integer.AbstractIntegerParser;

import java.util.Arrays;

/**
 * @author su-kun1899
 */
public class MyIntegerParser extends AbstractIntegerParser {
    @Override
    public Integer parse(String input) {
        if (input.startsWith("0")) {
            throw new ParseFailure("input starts with zero digit");
        }

        boolean containsNotDigit = !(input.chars().allMatch(value -> isDigit((char) value)));
        if (containsNotDigit) {
            throw new ParseFailure("input is not digit");
        }
        return null;
    }

    private boolean isDigit(char c) {
        return ('0' <= c) && (c <= '9');
    }
}
