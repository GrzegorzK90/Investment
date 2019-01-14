package pl.project.investment.investment.JSON;

import lombok.Data;
import pl.project.investment.investment.enums.TypeImplementation;

import javax.validation.constraints.NotNull;

/**
 * Class used to get parameters from user to generate calculation
 */
@Data
public class JsonModel {
    @NotNull(message = "Empty name ")
    private TypeImplementation name;
    @NotNull(message = "Empty amount")
    private Double amount;

    public JsonModel(TypeImplementation name, double amount) {
        this.name = name;
        this.amount = amount;
    }
}
