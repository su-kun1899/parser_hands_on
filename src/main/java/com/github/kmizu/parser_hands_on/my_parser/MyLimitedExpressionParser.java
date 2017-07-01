package com.github.kmizu.parser_hands_on.my_parser;

import com.github.kmizu.parser_hands_on.ParseFailure;
import com.github.kmizu.parser_hands_on.limited_expression.AbstractLimitedExpressionParser;
import com.github.kmizu.parser_hands_on.limited_expression.LimitedExpressionNode;

import static com.github.kmizu.parser_hands_on.limited_expression.LimitedExpressionNode.*;

/**
 * @author su-kun1899
 */
public class MyLimitedExpressionParser extends AbstractLimitedExpressionParser {
    @Override
    public LimitedExpressionNode parse(String input) {
        int left = integer(input, 0);

        char operand = 0;
        for (int i = 0; i < input.length(); i++) {
            char target = input.charAt(i);
            if (isOperand(target)) {
                operand = target;
                position = i;
                break;
            }
        }
        if (position != 0) {
            int right = integer(input, position + 1);

            switch (operand) {
                case '+':
                    return add(left, right);
                case '-':
                    return sub(left, right);
                case '*':
                    return mul(left, right);
                case '/':
                    return div(left, right);
                default:
                    throw new ParseFailure("unknown operand. operand: " + operand);
            }
        }

        return v(left);
    }

    private int integer(String input, int position) {
        if (input.startsWith("0")) {
            if (input.length() != 1) {
                throw new ParseFailure("input can't starts with zero. input: " + input);
            }
        }

        int result = 0;
        for (int i = position; i < input.length(); i++) {
            char target = input.charAt(i);

            if (isOperand(target)) {
                return result;
            }

            if (!isInteger(target)) {
                throw new ParseFailure("input contains not limited expression node. input: " + input);
            }

            result = result * 10 + (target - '0');
        }
        return result;
    }

    private boolean isInteger(char c) {
        return ('0' <= c) && (c <= '9');
    }

    private boolean isOperand(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
