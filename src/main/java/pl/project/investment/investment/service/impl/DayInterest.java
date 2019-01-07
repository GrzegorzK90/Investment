package pl.project.investment.investment.service.impl;

import pl.project.investment.investment.Type;
import pl.project.investment.investment.service.CalculationInterface;

/**
 * Class implementing CalculationInterface
 * used to calculate profit for each day
 */
public class DayInterest implements CalculationInterface {

	public Type name = Type.DayAlgorithm;

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
	public String getType() {
		return name.toString();
	}
}
