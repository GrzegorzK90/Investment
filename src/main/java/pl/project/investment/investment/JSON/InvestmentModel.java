package pl.project.investment.investment.JSON;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import pl.project.investment.investment.validators.DataFromToValidationAnnotation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@DataFromToValidationAnnotation
@Getter
@Setter
@NoArgsConstructor
public class InvestmentModel {
    @NotNull
    private String name;
    @NotNull
    @NumberFormat
    @Positive
    private double interestRate;
    @NotNull
    private LocalDate dateFrom;
    @NotNull
    private LocalDate dateTo;

    public InvestmentModel(String name, double interestRate, LocalDate dateFrom, LocalDate dateTo) {
        this.name = name;
        this.interestRate = interestRate;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
