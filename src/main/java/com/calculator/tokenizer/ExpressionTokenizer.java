package com.calculator.tokenizer;

import com.calculator.model.Token;
import com.calculator.model.TokenType;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTokenizer {

    private final String input;
    private int position = 0;

    public ExpressionTokenizer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (!isAtEnd()) {
            char current = peek();

            if (Character.isWhitespace(current)) {
                advance();
                continue;
            }

            if (Character.isDigit(current) || current == '.') {
                tokens.add(readNumber());
                continue;
            }

            if (Character.isLetter(current)) {
                tokens.add(readFunction());
                continue;
            }

            switch (current) {
                case '+' -> tokens.add(new Token(TokenType.PLUS, "+"));
                case '-' -> tokens.add(new Token(TokenType.MINUS, "-"));
                case '*' -> tokens.add(new Token(TokenType.MULTIPLY, "*"));
                case '/' -> tokens.add(new Token(TokenType.DIVIDE, "/"));
                case '^' -> tokens.add(new Token(TokenType.POWER, "^"));
                case '(' -> tokens.add(new Token(TokenType.LPAREN, "("));
                case ')' -> tokens.add(new Token(TokenType.RPAREN, ")"));
                default -> throw new RuntimeException("Caractère invalide: " + current);
            }

            advance();
        }

        return tokens;
    }

    private Token readNumber() {
        int start = position;

        while (!isAtEnd() && (Character.isDigit(peek()) || peek() == '.')) {
            advance();
        }

        String number = input.substring(start, position);
        return new Token(TokenType.NUMBER, number);
    }

    private Token readFunction() {
        int start = position;

        while (!isAtEnd() && Character.isLetter(peek())) {
            advance();
        }

        String name = input.substring(start, position);
        return new Token(TokenType.FUNCTION, name);
    }

    private boolean isAtEnd() {
        return position >= input.length();
    }

    private char peek() {
        return input.charAt(position);
    }

    private void advance() {
        position++;
    }
}