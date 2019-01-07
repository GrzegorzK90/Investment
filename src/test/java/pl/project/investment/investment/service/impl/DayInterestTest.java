package pl.project.investment.investment.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.project.investment.investment.service.CalculationInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
public class DayInterestTest {

    private CalculationInterface test;
    private double delta;

    @Before
    public void initTest(){
        test = new DayInterest();
        delta = 0.1;
    }

    @Test
    public void testCorrectValue() {

            assertEquals(1422.97, test.calculateInterest(90, 0.9, 631721.74),delta);
        }
    @Test
    public void testCorrectValue2() {

        assertEquals(1004.96, test.calculateInterest(90, 4, 100000),delta);
    }
    @Test
    public void testCorrectValue3() {

        assertEquals(2274.19, test.calculateInterest(90, 8, 112588.54),delta);
    }

    @Test
    public void testWrongValue(){
        assertNotEquals(1004.96, test.calculateInterest(91, 4, 100000),delta);
    }

    @Test
    public void testWrongValue1(){
        assertNotEquals(1004.96, test.calculateInterest(91, 4, 100000),delta);

    }
}