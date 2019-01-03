package pl.project.investment.investment.service;

import java.time.LocalDate;
import pl.project.investment.investment.entity.Calculation;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.exception.WrongDataException;
import pl.project.investment.investment.response.ErrorMessages;
import pl.project.investment.investment.service.impl.ValidationServiceImpl;

public class CalculationFactory {

	private CalculationInterface calculationInterface;
	private double amount;
	private ValidationService validationService= new ValidationServiceImpl();

	public CalculationFactory(CalculationInterface calculationInterface, double amount) {
			this.amount = amount;
			this.calculationInterface = calculationInterface;
	}

	public Calculation generateCalculation(Investment investment) throws WrongDataException {

		int days = investment.getDateTo().getDayOfYear() - investment.getDateFrom().getDayOfYear();
		if(validationService.isNegative(days)) throw new WrongDataException(ErrorMessages.NEGATIVE_VALUE.getErrorMessage());
		double interestRate = investment.getInterestRate();
		LocalDate today = LocalDate.now();

		double profit = calculationInterface.calculateInterest(days, interestRate, amount);

		return new Calculation(days, amount, today, investment, profit);
	}

}
