package pl.project.investment.investment.service.impl;
import org.springframework.stereotype.Component;
import pl.project.investment.investment.enums.TypeImplementation;
import pl.project.investment.investment.service.CalculationInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class implementing calculationInterface 
 * used to calculate profit at the end of investition period
 * @author Kuliński 
 *
 */
@Component
public class AtTheEndInterest implements CalculationInterface {

	private TypeImplementation type = TypeImplementation.DayAlgorithm;

	@Override
	public double calculateInterest(int days, double interest, double amount) {

		double result;
		result = amount * (((interest / 100)) * ((days / DAYS_IN_MONTH) / 12));

		BigDecimal bd = new BigDecimal(result);

		return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	@Override
	public TypeImplementation getType() {
		return type;
	}
}
