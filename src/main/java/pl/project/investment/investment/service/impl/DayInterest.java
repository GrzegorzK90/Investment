package pl.project.investment.investment.service.impl;

import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;
import pl.project.investment.investment.enums.TypeImplementation;
import pl.project.investment.investment.service.CalculationInterface;

/**
 * Class implementing CalculationInterface
 * used to calculate profit for each day
 */
@Component
public class DayInterest implements CalculationInterface {

	private TypeImplementation type = TypeImplementation.DayAlgorithm;

	@Override
	public double calculateInterest(int days, double interest, double amount) {
		testIsLogicValues(days,interest,amount);
		double percentagePerDay = interest / DAYS_IN_YEAR / (double) 100;
		double temp = amount;
		double result;
		for (int i = 0; i < days; i++) {
			temp += temp * percentagePerDay;
		}
		result = temp - amount;

		return DoubleRounder.round(result,2);
	}

	@Override
	public TypeImplementation getType() {
		return type;
	}
}
