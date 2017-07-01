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
        int left = left(input);

        return null;
    }

    private int left(String input) {
        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            char target = input.charAt(i);
            if (target == '+' || target == '-' || target == '*' || target == '/') {
                return result;
            }

            result = result * 10 + (target - '0');
        }

        throw new ParseFailure("input is not limited expression node. input:" + input);
    }
}
