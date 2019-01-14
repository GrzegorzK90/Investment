package pl.project.investment.investment.service.impl;

import org.decimal4j.util.DoubleRounder;
import pl.project.investment.investment.enums.ErrorMessages;
import pl.project.investment.investment.enums.PeriodValue;
import pl.project.investment.investment.service.CalculationInterface;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

abstract class CalculationImpl implements CalculationInterface {

    public double calculateInterest(int days, double interest, double amount) {
        validate(days, interest, amount);
        return doCalc(days, interest, amount);
    }

    abstract double doCalc(int days, double interest, double amount);

    private void validate(Integer days, double interest, double amount) {
        checkArgument(interest > 0, ErrorMessages.WRONG_VALUE.getErrorMessage() + "interest = " + interest);
        checkArgument(amount > 0, ErrorMessages.WRONG_VALUE.getErrorMessage() + "amount = " + amount);

//      checkArgument(days.equals(30) || days.equals(90) || days.equals(180) || days.equals(360),
//                     ErrorMessages.WRONG_VALUE.getErrorMessage() + "day = " + days);

        List<Integer> correctDays = new ArrayList<>();
        for (PeriodValue p : PeriodValue.values()) {
            correctDays.add(p.getPeriod() * (int) DAYS_IN_MONTH);
        }

        checkArgument(correctDays.contains(days), ErrorMessages.WRONG_VALUE.getErrorMessage() + "day = " + days);
    }

    double forLoopCalculation(double howManyTimes, double percentage, double amount){
        for (int i = 0; i < howManyTimes; i++) {
            amount += DoubleRounder.round(amount * percentage, 2);
        }

        return amount;
    }
}
