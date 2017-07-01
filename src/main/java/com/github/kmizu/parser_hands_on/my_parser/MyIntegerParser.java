package com.github.kmizu.parser_hands_on.my_parser;

import com.github.kmizu.parser_hands_on.ParseFailure;
import com.github.kmizu.parser_hands_on.integer.AbstractIntegerParser;

/**
 * @author su-kun1899
 */
public class MyIntegerParser extends AbstractIntegerParser {
    @Override
    public Integer parse(String input) {
        if (input.startsWith("0")) {
            if (input.length() != 1) {
                throw new ParseFailure("input starts with zero digit");
            }
        }

        if (input.chars().anyMatch(value -> !isDigit((char) value))) {
            throw new ParseFailure("input is not integer. input: " + input);
        }

        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            result = result * 10 + (input.charAt(i) - '0');
        }

        return result;
    }

    private boolean isDigit(char c) {
        return ('0' <= c) && (c <= '9');
    }
}
