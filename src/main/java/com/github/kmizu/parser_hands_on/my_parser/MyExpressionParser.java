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

    private boolean isOperand(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
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
        ExpressionNode result = integer(input);
        while (position < input.length()) {
            char ch = input.charAt(position);
            if (isOperand(ch)) {
                position++;
                switch (ch) {
                    case '+':
                        result = result.add(integer(input));
                        break;
                    case '-':
                        result = result.subtract(integer(input));
                        break;
                    case '*':
                        result = result.multiply(integer(input));
                        break;
                    case '/':
                        result = result.divide(integer(input));
                        break;
                    default:
                        throw new ParseFailure("Unsupported operand: " + ch);
                }
                continue;
            }
            throw new ParseFailure("input can't contain not integer. input: " + input);
        }
        return result;
    }
}
