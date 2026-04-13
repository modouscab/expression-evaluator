package com.calculator.parser;

import com.calculator.exception.CalculationException;
import com.calculator.model.Token;
import com.calculator.model.TokenType;

import java.util.List;

public class ExpressionParser {

    private final List<Token> tokens;
    private int position = 0;

    public ExpressionParser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public double parse() {
        return expression();
    }

    // + et -
    private double expression() {
        double result = term();

        while (match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = previous();
            double right = term();

            result = switch (operator.type()) {
                case PLUS -> result + right;
                case MINUS -> result - right;
                default -> throw new IllegalStateException();
            };
        }

        return result;
    }

    // * et /
    private double term() {
        double result = power();

        while (match(TokenType.MULTIPLY, TokenType.DIVIDE)) {
            Token operator = previous();
            double right = power();

            result = switch (operator.type()) {
                case MULTIPLY -> result * right;
                case DIVIDE -> {
                    if (right == 0) throw new CalculationException("Division par zéro");
                    yield result / right;
                }
                default -> throw new IllegalStateException();
            };
        }

        return result;
    }

    // puissance ^
    private double power() {
        double result = factor();

        if (match(TokenType.POWER)) {
            double exponent = power();
            result = Math.pow(result, exponent);
        }

        return result;
    }
    private double factor() {

        if (match(TokenType.MINUS)) {
            return -factor(); // récursion
        }

        if (match(TokenType.NUMBER)) {
            return Double.parseDouble(previous().value());
        }

        if (match(TokenType.LPAREN)) {
            double result = expression();
            consume(TokenType.RPAREN);
            return result;
        }

        if (match(TokenType.FUNCTION)) {
            String func = previous().value();

            consume(TokenType.LPAREN);
            double value = expression();
            consume(TokenType.RPAREN);

            return switch (func.toLowerCase()) {
                case "sqrt" -> Math.sqrt(value);
                default -> throw new CalculationException("Fonction inconnue :"  + func);
            };
        }

        throw new CalculationException("Expression invalide");
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return tokens.get(position).type() == type;
    }

    private Token advance() {
        if (!isAtEnd()) position++;
        return previous();
    }

    private boolean isAtEnd() {
        return position >= tokens.size();
    }

    private Token previous() {
        return tokens.get(position - 1);
    }

    private void consume(TokenType type) {
        if (check(type)) {
            advance();
            return;
        }
        throw new CalculationException("Parenthèse manquante ou syntaxe invalide");
    }
}