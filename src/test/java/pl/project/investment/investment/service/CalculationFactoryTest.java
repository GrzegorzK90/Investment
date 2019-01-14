package pl.project.investment.investment.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.project.investment.investment.enums.TypeImplementation;
import pl.project.investment.investment.service.impl.AtTheEndInterest;
import pl.project.investment.investment.service.impl.DayInterest;
import pl.project.investment.investment.service.impl.MonthInterest;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculationFactoryTest {

    @Autowired
    CalculationFactory calculationFactory;

    @Test
    public void getInterfaceDayImpl() {
        assertEquals(calculationFactory.getInterface(TypeImplementation.EndAlgorithm).getClass(), AtTheEndInterest.class);
    }

    @Test
    public void getInterfaceEndImpl() {
        assertEquals(calculationFactory.getInterface(TypeImplementation.DayAlgorithm).getClass(), DayInterest.class);
    }

    @Test
    public void getInterfaceMonthImpl() {
        assertEquals(calculationFactory.getInterface(TypeImplementation.MonthAlgorithm).getClass(), MonthInterest.class);
    }
}