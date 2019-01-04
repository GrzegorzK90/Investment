package pl.project.investment.investment.service;

import org.junit.Before;
import org.junit.Test;
import pl.project.investment.investment.JSON.JsonModel;
import pl.project.investment.investment.entity.Calculation;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.service.impl.AtTheEndInterest;
import pl.project.investment.investment.service.impl.DayInterest;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class CalculationFactoryTest {

    private JsonModel jsonModel;
    private Investment investment;
    private CalculationFactory calculationFactory;
    private Calculation calculation;


    @Before
    public void init(){
        investment = new Investment(1, "Lokata", 1.0, LocalDate.of(2018, 10, 1), LocalDate.of(2018, 10, 30));
        calculationFactory = null;

    }

    @Test
    public void generateCalculationTestDay() {
        jsonModel = new JsonModel("EndAlgorithm",1000);
        calculationFactory =new CalculationFactory ( new DayInterest(), jsonModel.getAmount());
        calculation = calculationFactory.generateCalculation(investment);

        assertEquals(calculation.getInvestment(), investment);
    }


    @Test
    public void generateCalculationTestEnd() {
        jsonModel = new JsonModel("DayAlgorithm",1000);
        calculationFactory =new CalculationFactory ( new AtTheEndInterest(), jsonModel.getAmount());
        calculation = calculationFactory.generateCalculation(investment);

        assertEquals(calculation.getInvestment(), investment);
    }
}