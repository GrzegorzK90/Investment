package pl.project.investment.investment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.project.investment.investment.enums.TypeImplementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalculationFactory {

    private static final Map<TypeImplementation, CalculationInterface> calculationInterfaceMap = new HashMap<>();

    @Autowired
    public CalculationFactory(List<CalculationInterface> list) {
        for (CalculationInterface calInterface : list) {
            calculationInterfaceMap.put(calInterface.getType(), calInterface);
        }
    }
    
    CalculationInterface getInterface(TypeImplementation name) {
        return calculationInterfaceMap.get(name);
    }
}