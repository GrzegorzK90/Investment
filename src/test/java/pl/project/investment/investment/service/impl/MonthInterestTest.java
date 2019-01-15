package pl.project.investment.investment.service.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import pl.project.investment.investment.exception.WrongDataException;
import pl.project.investment.investment.service.CalculationInterface;

import static org.junit.Assert.assertThat;

public class MonthInterestTest {

    private final CalculationInterface calculationInterface = new MonthInterest();

        @Test
        public void testCorrectValue() {
            assertThat(calculationInterface.calculateInterest(12, 3.5, 1000), CoreMatchers.is(35.57));
        }

    @Test
    public void testCorrectResultDot() {
        assertThat(calculationInterface.calculateInterest(12, 5.5, 1000.22), CoreMatchers.is(56.43));
    }


    @Test
        public void testCorrectResultMiniAmount() {
        assertThat(calculationInterface.calculateInterest(3, 2, 650), CoreMatchers.is(3.26));
        }

        @Test
        public void testWrongValue() {
            assertThat(calculationInterface.calculateInterest(1, 4.5, 545456564), CoreMatchers.not(7500027.76));
        }

    @Test(expected = WrongDataException.class)
        public void testExceptionDaysNotInPeriod() {
        calculationInterface.calculateInterest(5, 2.1, 100);
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