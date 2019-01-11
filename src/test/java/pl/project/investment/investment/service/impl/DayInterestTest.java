package pl.project.investment.investment.service.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import pl.project.investment.investment.service.CalculationInterface;

import static org.junit.Assert.assertThat;

public class DayInterestTest {

    private final CalculationInterface calculationInterface = new DayInterest();

    @Test
    public void testCorrectResult() {
        assertThat(calculationInterface.calculateInterest(10, 4.5, 545456564), CoreMatchers.is(682204.36));
    }

    @Test
    public void testWrongResult() {
        assertThat(calculationInterface.calculateInterest(1, 4.5, 545456564), CoreMatchers.not(100000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeDay() {
        calculationInterface.calculateInterest(-1, 4, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroInterest() {
        calculationInterface.calculateInterest(100, 0, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAmount() {
        calculationInterface.calculateInterest(21, 4, -1);
    }

}