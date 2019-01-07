package pl.project.investment.investment.service.impl;
import pl.project.investment.investment.Type;
import pl.project.investment.investment.service.CalculationInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class implementing calculationInterface 
 * used to calculate profit at the end of investition period
 * @author Kuliński 
 *
 */
public class AtTheEndInterest implements CalculationInterface {

	public Type name = Type.EndAlgorithm;

	@Override
	public double calculateInterest(int days, double interest, double amount) {

		double result;
		result = amount * (((interest / 100)) * ((days / DAYS_IN_MONTH) / 12));

		BigDecimal bd = new BigDecimal(result);

		return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	@Override
	public String getType() {
		return name.toString();
	}
}
