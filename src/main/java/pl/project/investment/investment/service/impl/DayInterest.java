package pl.project.investment.investment.service.impl;

import org.springframework.stereotype.Component;
import pl.project.investment.investment.enums.TypeImplementation;
import pl.project.investment.investment.service.CalculationInterface;

/**
 * Class implementing CalculationInterface
 * used to calculate profit for each day
 */
@Component
public class DayInterest implements CalculationInterface {

	//public TypeImplementation name = TypeImplementation.DayAlgorithm;

	private TypeImplementation type = TypeImplementation.EndAlgorithm;

	@Override
	public double calculateInterest(int days, double interest, double amount) {

		double percentagePerDay = interest / DAYS_IN_YEAR / (double) 100;
		double rest;
		double temp = amount;
		double result;
		for (int i = 0; i < days; i++) {
			temp += temp * percentagePerDay;
			rest = temp % 0.01;
			temp -= rest;
			if (rest > 0.0049)
				temp += 0.01;
		}


		result = temp - amount;
		rest = (temp - amount) % 0.01;
		result -= rest;
		if (rest > 0.0049)
			result += 0.01;

		return result;
	}

	@Override
	public TypeImplementation getType() {
		return type;
	}
}
