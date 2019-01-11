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
    @ManyToOne
    private Investment investment;
    private Double profit;

    public Calculation(Integer id, Double amount, LocalDate calculationDate, Investment investment, Double profit) {
        super();
        this.id = id;
        this.amount = amount;
        this.calculationDate = calculationDate;
        this.investment = investment;
        this.profit = profit;
    }
}
