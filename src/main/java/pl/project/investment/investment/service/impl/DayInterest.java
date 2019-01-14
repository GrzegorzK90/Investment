package pl.project.investment.investment.service.impl;

import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;
import pl.project.investment.investment.enums.TypeImplementation;
/**
 * Class implementing CalculationInterface
 * used to calculate profit for each day
 */
@Component
public class DayInterest extends CalculationImpl {

    private final TypeImplementation type = TypeImplementation.DayAlgorithm;

    @Override
    public TypeImplementation getType() {
        return type;
    }

    @Override
    double doCalc(int days, double interest, double amount) {
        double percentagePerDay = interest / DAYS_IN_YEAR / 100.0;
        double result = forLoopCalculation(days,percentagePerDay,amount);

        return DoubleRounder.round(result - amount, 2);
    }
}



//        double temp = amount;
//
//        for (int i = 0; i < days; i++) {
//            temp += DoubleRounder.round(temp * percentagePerDay, 2);
//        }