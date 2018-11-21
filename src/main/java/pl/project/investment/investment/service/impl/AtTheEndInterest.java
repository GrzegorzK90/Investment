package pl.project.investment.investment.service.impl;

import pl.project.investment.investment.service.CalculationInterface;
/**
 * Class implementing calculationInterface 
 * used to calculate profit at the end of investition period
 * @author Kuliński 
 *
 */
public class AtTheEndInterest implements CalculationInterface {

	@Override
	public double calculateInterest(int days, double interest, double amount) {
		double result = 0.0;
		double rest;
		result = amount * (((interest / 100)) * ((days / DAYS_IN_MONTH) / 12));

		rest = result % (double) 0.01;
		result -= rest;
		if (rest > 0.0049)
			result += 0.01;

		return result;
	}

}
