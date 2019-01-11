package pl.project.investment.investment.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import pl.project.investment.investment.JSON.InvestmentModel;
import pl.project.investment.investment.validators.DepositPeriodValidationAnnotation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * Class with model of Entity Investment
 */
@Entity
@SequenceGenerator(name = "INV_SEQ", allocationSize = 1)
@Data
@NoArgsConstructor
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INV_SEQ")
    @NotNull
    private int investmentId;
    @NotNull
    private String name;
    @NumberFormat
    @NotNull
    @Positive
    private Double interestRate;
    @DepositPeriodValidationAnnotation
    private Integer depositPeriod;
    @NotNull
    private LocalDate dateFrom;
    @NotNull
    private LocalDate dateTo;

    public Investment(int investmentId, String name, Double interestRate, Integer depositPeriod, LocalDate dateFrom, LocalDate dateTo) {
        this.investmentId = investmentId;
        this.name = name;
        this.interestRate = interestRate;
        this.depositPeriod = depositPeriod;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Investment(String name, Double interestRate, Integer depositPeriod, LocalDate dateFrom, LocalDate dateTo) {
        this.name = name;
        this.interestRate = interestRate;
        this.depositPeriod = depositPeriod;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Investment(InvestmentModel investmentModel) {
        this.name = investmentModel.getName();
        this.interestRate = investmentModel.getInterestRate();
        this.dateFrom = investmentModel.getDateFrom();
        this.dateTo = investmentModel.getDateTo();
        this.depositPeriod = investmentModel.getDepositPeriod();
    }
}
