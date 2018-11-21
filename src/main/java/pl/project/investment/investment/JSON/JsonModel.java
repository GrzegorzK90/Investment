package pl.project.investment.investment.JSON;
/**
 * Class used to get parameters from user to generate calculation
 */
public final class JsonModel {
	final String name;
	final double amount;

	public JsonModel(String name, double amount) {
		super();
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public double getAmount() {
		return amount;
	}

}
