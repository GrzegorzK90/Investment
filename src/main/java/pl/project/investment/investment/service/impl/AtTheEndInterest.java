package pl.project.investment.investment.service.impl;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;
import pl.project.investment.investment.enums.TypeImplementation;
import pl.project.investment.investment.service.CalculationInterface;
import pl.project.investment.investment.service.ValidationService;

/**
 * Class implementing calculationInterface 
 * used to calculate profit at the end of investment period
 * @author Kuliński
 */
@Component
public class AtTheEndInterest extends ValidationService implements CalculationInterface {

	private TypeImplementation type = TypeImplementation.EndAlgorithm;

	@Override
	public double calculateInterest(int days, double interest, double amount) {
		super.isValueLogic(days,interest,amount);
		double result = amount * (((interest / 100)) * ((days / DAYS_IN_MONTH) / 12));
		return DoubleRounder.round(result, 2);
	}

	@Override
	public TypeImplementation getType() {
		return type;
	}

}
