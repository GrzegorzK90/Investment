package pl.project.investment.investment.service.impl;

import org.junit.Before;
import org.junit.Test;
import pl.project.investment.investment.exception.WrongDataException;
import pl.project.investment.investment.service.CalculationInterface;

import static org.junit.Assert.*;

public class AtTheEndInterestTest {

    private CalculationInterface test;
    private double delta;


    @Before
    public void init(){
        test = new AtTheEndInterest();
        delta =  0.001;
    }

    @Test
    public void testCorrectValue() {
        assertEquals(497.60, test.calculateInterest(360, 1.1, 45236.4),delta);
    }
    @Test
    public void testCorrectValue2() {
        assertEquals(12645.23, test.calculateInterest(360, 23.1, 54741.25),delta);
    }
    @Test
    public void testCorrectValue3() {
        assertEquals(1000.00, test.calculateInterest(90, 4, 100000), delta);
    }
    @Test
    public void testWrongValue(){
        assertNotEquals(7500027.76, test.calculateInterest(1, 4.5, 545456564),delta); }
    @Test
    public void testWrongValue1(){
        assertNotEquals(1000.00, test.calculateInterest(91, 4, 100000),delta);
    }
    @Test
    public void testWrongValue2(){
        assertNotEquals(7500027.76, test.calculateInterest(1, 4.5, 545456564),delta);
    }

}