package pl.project.investment.investment.service.impl;

import org.decimal4j.util.DoubleRounder;
import pl.project.investment.investment.enums.ErrorMessages;
import pl.project.investment.investment.enums.PeriodValue;
import pl.project.investment.investment.service.CalculationInterface;

import static com.google.common.base.Preconditions.checkArgument;

abstract class CalculationImpl implements CalculationInterface {

    public double calculateInterest(int period, double interest, double amount) {
        validate(period, interest, amount);
        return doCalc(period, interest, amount);
    }

    abstract double doCalc(int period, double interest, double amount);

    private void validate(Integer period, double interest, double amount) {

        checkArgument(interest > 0, ErrorMessages.WRONG_VALUE.getErrorMessage() + "interest = " + interest);
        checkArgument(amount > 0, ErrorMessages.WRONG_VALUE.getErrorMessage() + "amount = " + amount);
        checkArgument(PeriodValue.valueOf(period) != null, ErrorMessages.WRONG_VALUE.getErrorMessage() + "day = " + period);
    }

    double multipleCapitalizationCalc(double howManyTimes, double percentage, double amount) {
        for (int i = 0; i < howManyTimes; i++) {
            amount += DoubleRounder.round(amount * percentage, 2);
        }

        return amount;
    }
}
