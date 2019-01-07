package pl.project.investment.investment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculationFactory {

	private static Map<String,Object> calculationInterfaceMap = new HashMap<>();

	private static CalculationFactory instance = null;

	public static CalculationFactory getInstance(List<CalculationInterface> list){
		if(instance==null)
			instance = new CalculationFactory(list);
		return instance;
	}

	private CalculationFactory(List<CalculationInterface> list){
		for (CalculationInterface calInterface: list  ) {
			calculationInterfaceMap.put(calInterface.getType(),calInterface);
		}
	}

	public Object getInterface(String name){
		return calculationInterfaceMap.get(name);
	}

}
