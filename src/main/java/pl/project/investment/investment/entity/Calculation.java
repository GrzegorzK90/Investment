package pl.project.investment.investment.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * Entity class with object Calculation
 */
@Entity
@SequenceGenerator(name = "CAL_SEQ", allocationSize = 1)
@Data
@NoArgsConstructor
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAL_SEQ")
    private int id;
    @NumberFormat
    @Positive
    private Double amount;
    private LocalDate calculationDate;
    private Integer depositPeriod;
    @ManyToOne
    private Investment investment;
    private Double profit;

    public Calculation(Integer id, Double amount, Integer depositPeriod, LocalDate calculationDate, Investment investment, Double profit) {
        super();
        this.id = id;
        this.amount = amount;
        this.depositPeriod = depositPeriod;
        this.calculationDate = calculationDate;
        this.investment = investment;
        this.profit = profit;
    }

    public Calculation(Double amount, Integer depositPeriod, LocalDate date, Investment investment, Double profit) {
        this.amount = amount;
        this.depositPeriod = depositPeriod;
        this.calculationDate = date;
        this.investment = investment;
        this.profit = profit;
    }
}
