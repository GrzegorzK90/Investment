package pl.project.investment.investment.JSON;

import java.time.LocalDate;
import pl.project.investment.investment.entity.Calculation;
/**
 * Class creating result from object Calculation
 *
 */
public final class ResultModel {
	final double amount;
	final double interest;
	final int period;
	final LocalDate date;
	double profit;
	
	public ResultModel(Calculation calc) {
		this.amount = calc.getAmount();
		this.interest = calc.getInvestment().getInterestRate();
		this.period = calc.getInvestment().getDateTo().getDayOfYear()
				- calc.getInvestment().getDateFrom().getDayOfYear();
		this.date = calc.getCalculationDate();
		this.profit = calc.getProfit();
	}

	public ResultModel(double amount, double interest, int period, LocalDate date, double profit) {
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
