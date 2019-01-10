package pl.project.investment.investment.service.impl;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;
import pl.project.investment.investment.enums.TypeImplementation;

/**
 * Class implementing calculationInterface 
 * used to calculate profit at the end of investment period
 * @author Kuliński
 */
@Component
public class AtTheEndInterest extends CalculationImpl {

	private final TypeImplementation type = TypeImplementation.EndAlgorithm;

	@Override
	double doCalc(int days, double interest, double amount) {
		double result = amount * (((interest / 100)) * ((days / DAYS_IN_MONTH) / 12));
		return DoubleRounder.round(result, 2);
	}

	@Override
	public TypeImplementation getType() {
		return type;
	}

}
