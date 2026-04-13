package com.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testSimpleAddition1() {
        assertEquals(2, Calculator.evaluate("1+1"));
    }
    @Test
    void testSimpleAddition() {
        assertEquals(3, Calculator.evaluate("1+2"));
    }

    @Test
    void testSpaces() {
        assertEquals(3, Calculator.evaluate("1 + 2"));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(0, Calculator.evaluate("1 + -1"));
    }

    @Test
    void testMinusNegativesNumbers() {
        assertEquals(0, Calculator.evaluate("-1--1"));
    }

    @Test
    void testParentheses() {
        assertEquals(21, Calculator.evaluate("(2+5)*3"));
    }

    @Test
    void testPriority() {
        assertEquals(17, Calculator.evaluate("2+2*5+5"));
    }

    @Test
    void testPower() {
        assertEquals(256, Calculator.evaluate("2^8"));
    }

    @Test
    void testComplexPower() {
        assertEquals(1279, Calculator.evaluate("2^8*5-1"));
    }

    @Test
    void testSqrt() {
        assertEquals(2, Calculator.evaluate("sqrt(4)"));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(RuntimeException.class,
                () -> Calculator.evaluate("1/0"));
    }
}