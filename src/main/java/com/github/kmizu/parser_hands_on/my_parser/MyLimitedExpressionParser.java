package com.github.kmizu.parser_hands_on.my_parser;

import com.github.kmizu.parser_hands_on.ParseFailure;
import com.github.kmizu.parser_hands_on.limited_expression.AbstractLimitedExpressionParser;
import com.github.kmizu.parser_hands_on.limited_expression.LimitedExpressionNode;

/**
 * @author su-kun1899
 */
public class MyLimitedExpressionParser extends AbstractLimitedExpressionParser {
    @Override
    public LimitedExpressionNode parse(String input) {
        int left = integer(input);

        return new LimitedExpressionNode.ValueNode(left);
    }

    private int integer(String input) {
        if (input.startsWith("0")) {
            if (input.length() != 1) {
                throw new ParseFailure("input can't starts with zero. input: " + input);
            }
        }

        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            char target = input.charAt(i);

            if (!isInteger(target)) {
                throw new ParseFailure("input contains not limited expression node. input: " + input);
            }

            if (target == '+' || target == '-' || target == '*' || target == '/') {
                return result;
            }

            result = result * 10 + (target - '0');
        }
        return result;
    }

    private boolean isInteger(char c) {
        return ('0' <= c) && (c <= '9');
    }
}
