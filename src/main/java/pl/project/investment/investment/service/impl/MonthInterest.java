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
    double doCalc(int period, double interest, double amount) {

        double percentagePerMonth = interest / 12 / 100.0;
        double result = multipleCapitalizationCalc(period, percentagePerMonth, amount);

        return DoubleRounder.round(result - amount, 2);
    }
}
