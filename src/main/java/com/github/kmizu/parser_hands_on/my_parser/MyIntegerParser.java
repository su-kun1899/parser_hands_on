package com.github.kmizu.parser_hands_on.my_parser;

import com.github.kmizu.parser_hands_on.ParseFailure;
import com.github.kmizu.parser_hands_on.integer.AbstractIntegerParser;

/**
 * @author su-kun1899
 */
public class MyIntegerParser extends AbstractIntegerParser {
    @Override
    public Integer parse(String input) {
        char first = input.charAt(0);
        if (!isDigit(first)) {
            throw new ParseFailure("first character is not digit");
        }
        return null;
    }

    private boolean isDigit(char c) {
        return ('0' <= c) && (c <= '9');
    }
}
