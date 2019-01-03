package pl.project.investment.investment.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Entity class with object Calculation
 *
 *
 */
@Entity
@SequenceGenerator(name = "CAL_SEQ", sequenceName = "calculation_sequence")
public class Calculation {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAL_SEQ")
	private int id;

	private double amount;
	private LocalDate calculationDate;
	@ManyToOne
	private Investment investment;
	private double profit;

	public Calculation() {
	}

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
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getCalculationDate() {
		return calculationDate;
	}

	public void setCalculationDate(LocalDate calculationDate) {
		this.calculationDate = calculationDate;
	}

	public Investment getInvestment() {
		return investment;
	}

	public void setInvestment(Investment investment) {
		this.investment = investment;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "Calculation [id=" + id + ", amount=" + amount + ", calculationDate=" + calculationDate + ", investment="
				+ investment + ", calculationInterface=" + ", profit=" + profit + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Calculation calc = (Calculation) obj;
		if (this.amount == calc.getAmount()
			&& this.investment.equals(calc.getInvestment())
			&& this.profit == calc.getProfit()) {
		}
		return true;
	}
}
