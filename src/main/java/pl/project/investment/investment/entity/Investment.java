package pl.project.investment.investment.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Class with model of Entity Investment
 *
 */
@Entity
@SequenceGenerator(name = "INV_SEQ", sequenceName = "investment_sequence")
public class Investment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INV_SEQ")
	private int investmentId;

	private String name;
	private double InterestRate;
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
		InterestRate = interestRate;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	public Investment( String name, double interestRate, LocalDate dateFrom, LocalDate dateTo) {
		super();
		this.name = name;
		InterestRate = interestRate;
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
		return InterestRate;
	}

	public void setInterestRate(double interestRate) {
		InterestRate = interestRate;
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
		return "Investment [id=" + investmentId + ", name=" + name + ", InterestRate=" + InterestRate + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + ", calculation=" + calculation + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Investment inv = (Investment) obj;
        return this.InterestRate == inv.getInterestRate()
                && this.name.equals(inv.getName())
                && this.dateTo.equals(inv.dateTo)
                && this.dateFrom.equals(inv.dateFrom);
	}
}
