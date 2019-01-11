package pl.project.investment.investment.JSON;

import lombok.Data;
import pl.project.investment.investment.entity.Calculation;

import java.time.LocalDate;

/**
 * Class creating result from object Calculation
 */

@Data
public final class ResultModel {
    private final Double amount;
    private final Double interest;
    private final Integer period;
    private final LocalDate date;
    private Double profit;
    private Integer calculationId;

    public ResultModel(Calculation calc) {
        this.amount = calc.getAmount();
        this.interest = calc.getInvestment().getInterestRate();
        this.period = calc.getInvestment().getDateTo().getDayOfYear()
                - calc.getInvestment().getDateFrom().getDayOfYear();
        this.date = calc.getCalculationDate();
        this.profit = calc.getProfit();
        this.calculationId = calc.getId();
    }

    public ResultModel(double amount, double interest, int period, LocalDate date, double profit, int calculationId) {
        super();
        this.amount = amount;
        this.interest = interest;
        this.period = period;
        this.date = date;
        this.profit = profit;
        this.calculationId = calculationId;
    }

    @Override
    public String toString() {
        return "amount=" + amount + ", interest=" + interest + ", period="
                + period + ", profit=" + profit + ", date=" + date;
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) return true;
        if (!(object instanceof ResultModel)) {
            return false;
        }
        ResultModel resultModel = (ResultModel) object;
        return amount.equals(resultModel.amount) && interest.equals(resultModel.interest)
                && period.equals(resultModel.period) && date.equals(resultModel.date);

    }
}
