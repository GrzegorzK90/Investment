package pl.project.investment.investment.service.impl;

import org.junit.Test;
import pl.project.investment.investment.service.CalculationInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class DayInterestTest {

    private CalculationInterface calculationInterface = new DayInterest();
    private double delta;


    @Test
    public void testCorrectResult() {

            assertEquals(1422.97, calculationInterface.calculateInterest(90, 0.9, 631721.74),delta);
        }


    @Test
    public void testWrongResult(){
        assertNotEquals(1004.96, calculationInterface.calculateInterest(91, 4, 100000),delta);
    }

    @Test
    public void testNegativeDay(){
        calculationInterface.calculateInterest(-1,4,1000);
    }

}