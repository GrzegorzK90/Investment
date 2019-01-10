package pl.project.investment.investment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;
/**
 * Entity class with object Calculation
 */
@Entity
@SequenceGenerator(name = "CAL_SEQ", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class Calculation {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAL_SEQ")
	private int id;
    @NumberFormat
    @Positive
	private double amount;
	private LocalDate calculationDate;
	@ManyToOne
	private Investment investment;
	private double profit;

	public Calculation(Integer id, Double amount, LocalDate calculationDate, Investment investment, Double profit) {
		super();
		this.id = id;
		this.amount = amount;
		this.calculationDate = calculationDate;
		this.investment = investment;
		this.profit = profit;
	}
	public Calculation(Double amount, LocalDate calculationDate, Investment investment, Double profit) {
		super();
		this.amount = amount;
		this.calculationDate = calculationDate;
		this.investment = investment;
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "Calculation [id=" + id + ", amount=" + amount + ", calculationDate=" + calculationDate + ", investment="
				+ investment + ", calculationInterface=" + ", profit=" + profit + "]";
	}

	@Override
	public boolean equals(Object obj) {

		if(obj == this) return true;
		if (!(obj instanceof Calculation)){
			return false;
		}

		Calculation calc = (Calculation) obj;

		return id == calc.id &&
				amount == calc.amount &&
				profit == calc.profit &&
				Objects.equals(investment,calc.investment) &&
				Objects.equals(calculationDate,calc.calculationDate);
	}
}
