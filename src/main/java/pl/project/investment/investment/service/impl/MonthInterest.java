package pl.project.investment.investment.service.impl;

import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;
import pl.project.investment.investment.enums.TypeImplementation;

@Component
public class MonthInterest extends CalculationImpl {

    private final TypeImplementation type = TypeImplementation.MonthAlgorithm;

    @Override
    public TypeImplementation getType() {
        return type;
    }

    @Override
    double doCalc(int days, double interest, double amount) {
        int month = days / (int) DAYS_IN_MONTH;
        double percentagePerMonth = interest / 12 / 100.0;
        double result = forLoopCalculation(month, percentagePerMonth, amount);

        return DoubleRounder.round(result - amount, 2);
    }

}


//
//        for(int i = 0; i < month; i++){
//            temp+= temp * percentagePerMonth;
//            temp = DoubleRounder.round(temp,2);
//        }
