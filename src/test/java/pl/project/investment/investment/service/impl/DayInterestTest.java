package pl.project.investment.investment.service.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import pl.project.investment.investment.service.CalculationInterface;

import static org.junit.Assert.assertThat;

public class DayInterestTest {

    private final CalculationInterface calculationInterface = new DayInterest();

    @Test
    public void testCorrectResult() {
        assertThat(calculationInterface.calculateInterest(180, 4.5, 25000), CoreMatchers.is(568.84));
    }

    @Test
    public void testWrongResult() {
        assertThat(calculationInterface.calculateInterest(360, 4.5, 545456564), CoreMatchers.not(100000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeDay() {
        calculationInterface.calculateInterest(-1, 4, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionDaysNotInPeriod() {
        calculationInterface.calculateInterest(29, 5, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroInterest() {
        calculationInterface.calculateInterest(30, 0, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAmount() {
        calculationInterface.calculateInterest(90, 4, -1);
    }

}