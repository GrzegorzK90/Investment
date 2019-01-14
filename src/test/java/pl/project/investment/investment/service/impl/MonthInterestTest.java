package pl.project.investment.investment.service.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import pl.project.investment.investment.service.CalculationInterface;

import static org.junit.Assert.*;

public class MonthInterestTest {

    private final CalculationInterface calculationInterface = new MonthInterest();

        @Test
        public void testCorrectValue() {
            assertThat(calculationInterface.calculateInterest(360, 3.5, 1000), CoreMatchers.is(35.57));
        }

        @Test
        public void testCorrectResultMiniAmount() {
            assertThat(calculationInterface.calculateInterest(90, 2, 650), CoreMatchers.is(3.26));
        }

        @Test
        public void testWrongValue() {
            assertThat(calculationInterface.calculateInterest(30, 4.5, 545456564), CoreMatchers.not(7500027.76));
        }

        @Test(expected = IllegalArgumentException.class)
        public void testExceptionDaysNotInPeriod() {
            calculationInterface.calculateInterest(160, 2.1, 100);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testNegativeDay() {
            calculationInterface.calculateInterest(-1, 4, 1000);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testZeroInterest() {
            calculationInterface.calculateInterest(90, 0, 1000);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testNegativeAmount() {
            calculationInterface.calculateInterest(30, 4, -1);
        }
}