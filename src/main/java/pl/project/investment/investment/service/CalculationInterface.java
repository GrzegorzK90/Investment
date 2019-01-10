package pl.project.investment.investment.service;

import pl.project.investment.investment.enums.ErrorMessages;
import pl.project.investment.investment.enums.TypeImplementation;

import static com.google.common.base.Preconditions.checkArgument;

public interface CalculationInterface {
	double DAYS_IN_YEAR = 360;
	double DAYS_IN_MONTH = 30;

	double calculateInterest(int days, double interest, double amount);
	TypeImplementation getType();

	default void testIsLogicValues(int days, double interest, double amount){
		checkArgument((days > 0) && (interest > 0) && (amount > 0),
				ErrorMessages.NO_LOGIC.getErrorMessage() + "Days: "+ days + " interest: " + interest + " amount: " + amount);
	}

}
