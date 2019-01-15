package pl.project.investment.investment.service.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import pl.project.investment.investment.exception.WrongDataException;
import pl.project.investment.investment.service.CalculationInterface;

import static org.junit.Assert.assertThat;

public class DayInterestTest {

    private final CalculationInterface calculationInterface = new DayInterest();

    @Test
    public void testCorrectResult() {
        assertThat(calculationInterface.calculateInterest(6, 4.5, 25000), CoreMatchers.is(568.85));
    }

    @Test
    public void testCorrectResultDot() {
        assertThat(calculationInterface.calculateInterest(12, 5.5, 1000.22), CoreMatchers.is(56.64));
    }

    @Test
    public void testCorrectResultMiniAmount() {
        assertThat(calculationInterface.calculateInterest(12, 1.5, 250.0), CoreMatchers.is(3.60));
    }

    @Test
    public void testWrongResult() {
        assertThat(calculationInterface.calculateInterest(12, 4.5, 545456564), CoreMatchers.not(100000));
    }

    @Test(expected = WrongDataException.class)
    public void testNegativeDay() {
        calculationInterface.calculateInterest(-1, 4, 1000);
    }

    @Test(expected = WrongDataException.class)
    public void testExceptionDaysNotInPeriod() {
        calculationInterface.calculateInterest(29, 5, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroInterest() {
        calculationInterface.calculateInterest(1, 0, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAmount() {
        calculationInterface.calculateInterest(3, 4, -1);
    }

}