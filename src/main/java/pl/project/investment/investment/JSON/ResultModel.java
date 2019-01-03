package pl.project.investment.investment.JSON;

import java.time.LocalDate;
import pl.project.investment.investment.entity.Calculation;
/**
 * Class creating result from object Calculation
 *
 */
public final class ResultModel {
	private final double amount;
	private final double interest;
	private final int period;
	private final LocalDate date;
	private double profit;
	
	public ResultModel(Calculation calc) {
		this.amount = calc.getAmount();
		this.interest = calc.getInvestment().getInterestRate();
		this.period = calc.getInvestment().getDateTo().getDayOfYear()
				- calc.getInvestment().getDateFrom().getDayOfYear();
		this.date = calc.getCalculationDate();
		this.profit = calc.getProfit();
	}

	public ResultModel(double amount, double interest, int period, LocalDate date) {
		super();
		this.amount = amount;
		this.interest = interest;
		this.period = period;
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public double getInterest() {
		return interest;
	}

	public int getPeriod() {
		return period;
	}

	public LocalDate getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "ResultModel [amount=" + amount + ", interest=" + interest + ", period=" + period + ", date=" + date
				+ ", profit=" + profit + "]";
	}


}
