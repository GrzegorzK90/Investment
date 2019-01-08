package pl.project.investment.investment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
/**
 * Class with model of Entity Investment
 *
 */
@Entity
@SequenceGenerator(name = "INV_SEQ", allocationSize = 1)
public class Investment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INV_SEQ")
	private int investmentId;

	private String name;
	private double interestRate;
	private LocalDate dateFrom;
	private LocalDate dateTo;

	@OneToMany(mappedBy = "investment")
	@JsonIgnore
	private List<Calculation> calculation;

	public Investment() {
	}

	public Investment(int investmentId, String name, double interestRate, LocalDate dateFrom, LocalDate dateTo) {
		super();
		this.investmentId = investmentId;
		this.name = name;
		this.interestRate = interestRate;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	public Investment( String name, double interestRate, LocalDate dateFrom, LocalDate dateTo) {
		super();
		this.name = name;
		this.interestRate = interestRate;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public int getinvestmentId() {
		return investmentId;
	}

	public void setinvestmentId(int investmentId) {
		this.investmentId = investmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	@Override
	public String toString() {
		return "Investment [id=" + investmentId + ", name=" + name + ", interestRate=" + interestRate + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + ", calculation=" + calculation + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if (!(obj instanceof Investment)){
			return false;
		}

		Investment inv = (Investment) obj;

		return investmentId == inv.investmentId &&
				interestRate == inv.interestRate &&
				Objects.equals(name,inv.name) &&
				Objects.equals(dateFrom,inv.dateFrom) &&
				Objects.equals(dateTo,inv.dateTo);
	}
}
