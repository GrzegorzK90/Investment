package pl.project.investment.investment.service.impl;

import pl.project.investment.investment.enums.ErrorMessages;
import pl.project.investment.investment.service.CalculationInterface;

import static com.google.common.base.Preconditions.checkArgument;

abstract class CalculationImpl implements CalculationInterface {

    public double calculateInterest(int days, double interest, double amount) {
        validate(days, interest, amount);
        return doCalc(days, interest, amount);
    }

    abstract double doCalc(int days, double interest, double amount);

    private void validate(Integer days, double interest, double amount) {
        checkArgument(days.equals(30) || days.equals(90) || days.equals(180) || days.equals(360),
                ErrorMessages.WRONG_VALUE.getErrorMessage() + "day = " + days);
        checkArgument(interest > 0, ErrorMessages.WRONG_VALUE.getErrorMessage() + "interest = " + interest);
        checkArgument(amount > 0, ErrorMessages.WRONG_VALUE.getErrorMessage() + "amount = " + amount);
    }
}
