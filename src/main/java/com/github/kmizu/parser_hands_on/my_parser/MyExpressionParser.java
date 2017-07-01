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
        position = 0;
        if (input.startsWith("0")) {
            if (input.length() != 1) {
                throw new ParseFailure("input can't starts with zero. input: " + input);
            }
        }

        return expression(input);
    }

    private boolean isInteger(char c) {
        return ('0' <= c) && (c <= '9');
    }

    public ExpressionNode integer(String input) {
        int result = 0;
        while (position < input.length()) {
            char ch = input.charAt(position);
            if (isInteger(ch)) {
                result = result * 10 + (ch - '0');
                position++;
                continue;
            }
            break;
        }
        return ExpressionNode.integer(result);
    }

    private ExpressionNode expression(String input) {
        ExpressionNode result = multitive(input);
        while (position < input.length()) {
            char ch = input.charAt(position);
            switch (ch) {
                case '+':
                    position++;
                    result = result.add(multitive(input));
                    continue;
                case '-':
                    position++;
                    result = result.subtract(multitive(input));
                    continue;
                default:
                    break;
            }
            break;
        }

        return result;
    }

    private ExpressionNode primary(String input) {
        ExpressionNode result;
        if (position < input.length()) {
            char ch = input.charAt(position);
            if (ch == '(') {
                position++;
                result = expression(input);
                if (input.charAt(position) != ')') {
                    throw new ParseFailure("Illegal brackets. input: " + input);
                }
                return result;
            } else {
                return integer(input);
            }
        }
        throw new ParseFailure("input can't contain not integer. input: " + input);
    }

    private ExpressionNode multitive(String input) {
        ExpressionNode result = primary(input);
        while (position < input.length()) {
            char ch = input.charAt(position);
            switch (ch) {
                case '*':
                    position++;
                    result = result.multiply(primary(input));
                    continue;
                case '/':
                    position++;
                    result = result.divide(primary(input));
                    continue;
                default:
                    break;
            }
            break;
        }
        return result;
    }
}
