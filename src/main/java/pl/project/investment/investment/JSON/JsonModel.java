package pl.project.investment.investment.JSON;
/**
 * Class used to get parameters from user to generate calculation
 */
public  class JsonModel {
	 private String name;
	 private double amount;

	public JsonModel(String name, double amount) {
		this.name = name;
		this.amount = amount;
	}

	public JsonModel(){}

	public String getName() {
		return name;
	}

	public double getAmount() {
		return amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAmount(double amount) {
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
				amount == jsonModel.amount;
	}
}
