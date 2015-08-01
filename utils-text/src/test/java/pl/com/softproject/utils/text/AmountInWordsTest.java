/*
 * Copyright 2012-01-24 the original author or authors.
 */
package pl.com.softproject.utils.text;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class AmountInWordsTest {

    /**
     * Test of toWords method, of class AmountInWords.
     */
    @Test
    public void testToWordsZero() {
        System.out.println("toWordsZero");
        double kwota = 0.0;
        String expResult = "zero zł zero gr";
        String result = AmountInWords.toWords(kwota);
        System.out.println("result: " + result);
        assertEquals(expResult, result);
    }


    @Test
    public void testToWords100() {
        System.out.println("toWords");
        double kwota = 123.50;
        String expResult = "sto dwadzieścia trzy zł pięćdziesiąt gr";
        String result = AmountInWords.toWords(kwota);
        System.out.println("result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testToWords126() {
        System.out.println("toWords");
        double kwota = 126.50;
        String expResult = "sto dwadzieścia sześć zł pięćdziesiąt gr";
        String result = AmountInWords.toWords(kwota);
        System.out.println("result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testToWords101() {
        System.out.println("toWords");
        double kwota = 124.50;
        String expResult = "sto dwadzieścia cztery zł pięćdziesiąt gr";
        String result = AmountInWords.toWords(kwota);
        System.out.println("result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testToWords113() {
        System.out.println("toWords");
        double kwota = 113.20;
        String expResult = "sto trzynaście zł dwadzieścia gr";
        String result = AmountInWords.toWords(kwota);
        System.out.println("result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testToWords1() {
        System.out.println("toWords");
        double kwota = 3;
        String expResult = "trzy zł zero gr";
        String result = AmountInWords.toWords(kwota);
        System.out.println("result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testToWords1000() {
        System.out.println("toWords");
        double kwota = 1237.24;
        String expResult = "tysiąc dwieście trzydzieści siedem zł dwadzieścia cztery gr";
        String result = AmountInWords.toWords(kwota);
        System.out.println("result: " + result);
        assertEquals(expResult, result);
    }

    @Test
    public void testLiczba_groszy() {

        double liczba = 123.6456;
        long result = AmountInWords.liczba_groszy(liczba);
        assertEquals(65L, result);
    }
}
