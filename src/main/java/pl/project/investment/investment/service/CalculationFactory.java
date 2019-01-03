package pl.project.investment.investment.service;

import java.time.LocalDate;
import pl.project.investment.investment.entity.Calculation;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.exception.WrongDataException;

public class CalculationFactory {

	private CalculationInterface calculationInterface;
	private double amount;

	public CalculationFactory(CalculationInterface calculationInterface, double amount) {
			this.amount = amount;
			this.calculationInterface = calculationInterface;
	}

	public Calculation generateCalculation(Investment investment) throws WrongDataException {

		int days = investment.getDateTo().getDayOfYear() - investment.getDateFrom().getDayOfYear();

		double interestRate = investment.getInterestRate();
		LocalDate today = LocalDate.now();

		double profit = calculationInterface.calculateInterest(days, interestRate, amount);

		if(amount == 0.0) throw new WrongDataException("Test");
		return new Calculation(days, amount, today, investment, profit);
	}

}
