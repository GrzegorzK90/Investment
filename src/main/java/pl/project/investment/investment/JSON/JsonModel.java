package pl.project.investment.investment.JSON;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.project.investment.investment.enums.TypeImplementation;

import javax.validation.constraints.NotNull;


/**
 * Class used to get parameters from user to generate calculation
 */
@Getter
@Setter
@NoArgsConstructor
public class JsonModel {
	@NotNull(message = "TEST VALIDATION !!!!")
	private TypeImplementation name;

	@NotNull(message = "TEST VALIDATION AMOUNT !!!!")
	private Double amount;

	public JsonModel(TypeImplementation name, double amount) {
		this.name = name;
		this.amount = amount;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(!(obj instanceof JsonModel)){
			return false;
		}

		JsonModel jsonModel = (JsonModel) obj;
		return name == jsonModel.name&&
				amount.equals(jsonModel.amount);
	}
}


//	public JsonModel(){}
//
//	public TypeImplementation getName() {
//		return name;
//	}
//
//	public double getAmount() {
//		return amount;
//	}
//
//	public void Type(TypeImplementation name) {
//		this.name = name;
//	}
//
//	public void setAmount(double amount) {
//		this.amount = amount;
//	}