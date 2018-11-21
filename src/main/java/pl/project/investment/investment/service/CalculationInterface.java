package pl.project.investment.investment.service;

public interface CalculationInterface {
	static final double DAYS_IN_YEAR = 360;
	static final double DAYS_IN_MONTH = 30;

	public double calculateInterest(int days, double interest, double amount);
}
