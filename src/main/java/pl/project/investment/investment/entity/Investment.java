package pl.project.investment.investment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import pl.project.investment.investment.validators.DataFromToValidationAnnotation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
/**
 * Class with model of Entity Investment
 */
@Entity
@SequenceGenerator(name = "INV_SEQ", allocationSize = 1)
@DataFromToValidationAnnotation
@Getter
@Setter
@NoArgsConstructor
public class Investment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INV_SEQ")
	private int investmentId;
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

	@OneToMany(mappedBy = "investment")
	@JsonIgnore
	private List<Calculation> calculation;


	public Investment(int investmentId, String name, double interestRate, LocalDate dateFrom, LocalDate dateTo) {
		this.investmentId = investmentId;
		this.name = name;
		this.interestRate = interestRate;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	public Investment( String name, double interestRate, LocalDate dateFrom, LocalDate dateTo) {
		this.name = name;
		this.interestRate = interestRate;
		this.dateFrom = dateFrom;
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
