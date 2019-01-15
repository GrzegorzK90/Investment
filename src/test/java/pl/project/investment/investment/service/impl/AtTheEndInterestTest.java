package pl.project.investment.investment.service.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import pl.project.investment.investment.exception.WrongDataException;
import pl.project.investment.investment.service.CalculationInterface;

import static org.junit.Assert.assertThat;

public class AtTheEndInterestTest {

    private final CalculationInterface calculationInterface = new AtTheEndInterest();

    @Test
    public void testCorrectValue() {
        assertThat(calculationInterface.calculateInterest(12, 1.1, 45236.4), CoreMatchers.is(497.60));
    }

    @Test
    public void testCorrectResultDot() {
        assertThat(calculationInterface.calculateInterest(12, 5.5, 1000.22), CoreMatchers.is(55.01));
    }

    @Test
    public void testCorrectResultMiniAmount() {
        assertThat(calculationInterface.calculateInterest(12, 1.5, 250.0), CoreMatchers.is(3.75));
    }

    @Test
    public void testWrongValue() {
        assertThat(calculationInterface.calculateInterest(1, 4.5, 545456564), CoreMatchers.not(7500027.76));
    }

    @Test(expected = WrongDataException.class)
    public void testExceptionDaysNotInPeriod() {
        calculationInterface.calculateInterest(7, 2.1, 100);
    }

    @Test(expected = WrongDataException.class)
    public void testNegativeDay() {
        calculationInterface.calculateInterest(-1, 4, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroInterest() {
        calculationInterface.calculateInterest(3, 0, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAmount() {
        calculationInterface.calculateInterest(1, 4, -1);
    }

}