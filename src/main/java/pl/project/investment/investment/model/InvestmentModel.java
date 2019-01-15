package pl.project.investment.investment.model;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import pl.project.investment.investment.enums.PeriodValue;
import pl.project.investment.investment.validators.DataFromToValidationAnnotation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@DataFromToValidationAnnotation
@Data
public class InvestmentModel {
    @NotNull(message = "Empty name field")
    private String name;
    @NotNull(message = "Empty interestRate field")
    @NumberFormat
    @Positive
    private double interestRate;
    @NotNull
    private PeriodValue periodValue;
    @NotNull(message = "Empty dateFrom field")
    private LocalDate dateFrom;
    @NotNull(message = "Empty dateTo field")
    private LocalDate dateTo;

    public InvestmentModel(String name, double interestRate, Integer depositPeriod, LocalDate dateFrom, LocalDate dateTo) {
        this.periodValue = PeriodValue.valueOf(depositPeriod);
        this.name = name;
        this.interestRate = interestRate;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
