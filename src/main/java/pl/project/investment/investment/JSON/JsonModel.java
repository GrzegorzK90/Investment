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

}
