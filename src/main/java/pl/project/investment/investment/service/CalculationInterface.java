package pl.project.investment.investment.service;

public interface CalculationInterface {
	double DAYS_IN_YEAR = 360;
	double DAYS_IN_MONTH = 30;

	double calculateInterest(int days, double interest, double amount);

}
