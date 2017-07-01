package com.github.kmizu.parser_hands_on.my_parser;

import com.github.kmizu.parser_hands_on.ParseFailure;
import com.github.kmizu.parser_hands_on.expression.AbstractExpressionParser;
import com.github.kmizu.parser_hands_on.expression.ExpressionNode;

/**
 * @author su-kun1899
 */
public class MyExpressionParser extends AbstractExpressionParser {

    @Override
    public ExpressionNode parse(String input) {
        if (input.startsWith("0")) {
            if (input.length() != 1) {
                throw new ParseFailure("input can't starts with zero. input: " + input);
            }
        }

        return new ExpressionNode.ValueNode(integer(input));
    }

    private boolean isInteger(char c) {
        return ('0' <= c) && (c <= '9');
    }

    public int integer(String input) {
        int result = 0;
        while (position < input.length()) {
            char ch = input.charAt(position);
            if (isInteger(ch)) {
                result = result * 10 + (ch - '0');
                position++;
                continue;
            }
            throw new ParseFailure("input can't contain not integer. input: " + input);
        }
        return result;
    }
}
