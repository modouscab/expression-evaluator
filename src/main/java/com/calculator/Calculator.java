package com.calculator;

import com.calculator.model.Token;
import com.calculator.parser.ExpressionParser;
import com.calculator.tokenizer.ExpressionTokenizer;

import java.util.List;

public class Calculator {

    public static double evaluate(String expression) {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
        List<Token> tokens = tokenizer.tokenize();

        ExpressionParser parser = new ExpressionParser(tokens);
        return parser.parse();
    }
}