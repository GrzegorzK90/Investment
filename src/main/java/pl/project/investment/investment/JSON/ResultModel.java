package pl.project.investment.investment.JSON;

import lombok.Getter;
import lombok.Setter;
import pl.project.investment.investment.entity.Calculation;

import java.time.LocalDate;
import java.util.Objects;
/**
 * Class creating result from object Calculation
 */
@Getter
@Setter
public final class ResultModel {
	private final double amount;
	private final double interest;
	private final int period;
	private final LocalDate date;
	private double profit;
	private int calculationId;

	public ResultModel(Calculation calc) {
		this.amount = calc.getAmount();
		this.interest = calc.getInvestment().getInterestRate();
		this.period = calc.getInvestment().getDateTo().getDayOfYear()
				- calc.getInvestment().getDateFrom().getDayOfYear();
		this.date = calc.getCalculationDate();
		this.profit = calc.getProfit();
		this.calculationId = calc.getId();
	}

	public ResultModel(double amount, double interest, int period, LocalDate date, double profit) {
		super();
		this.amount = amount;
		this.interest = interest;
		this.period = period;
		this.date = date;
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "amount=" + amount + ", interest=" + interest + ", period=" + period + ", date=" + date
				+ ", profit=" + profit;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if (!(obj instanceof ResultModel)){
			return false;
		}

		ResultModel rm = (ResultModel) obj;

		return amount == rm.amount &&
				interest == rm.interest &&
				period == rm.period &&
				profit == rm.profit &&
				Objects.equals(date,rm.date);
	}
}
