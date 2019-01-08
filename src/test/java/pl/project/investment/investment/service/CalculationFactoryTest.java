package pl.project.investment.investment.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.project.investment.investment.enums.TypeImplementation;
import pl.project.investment.investment.service.impl.AtTheEndInterest;
import pl.project.investment.investment.service.impl.DayInterest;

import java.util.EnumSet;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AtTheEndInterest.class,DayInterest.class,CalculationFactory.class})
//@SpringBootTest(classes = context.class)
public class CalculationFactoryTest {

    @Autowired
    CalculationFactory calculationFactory;
    @Autowired
    DayInterest dayInterest;
    @Autowired
    AtTheEndInterest atTheEndInterest;

    @Test
    public void getInterfaceDayImpl() {
        assertEquals(calculationFactory.getInterface(TypeImplementation.DayAlgorithm)
                        .calculateInterest(1,3,4.0),
                dayInterest.calculateInterest(1,3,4.0),0.001);
    }
    @Test
    public void getInterfaceEndImpl() {
        assertEquals(calculationFactory.getInterface(TypeImplementation.EndAlgorithm)
                        .calculateInterest(1,3,4.0),
                atTheEndInterest.calculateInterest(1,3,4.0),0.001);
    }
    @Test
    public void getAllInterfaceAvailable(){
        EnumSet.allOf(TypeImplementation.class).forEach(d ->  assertEquals(calculationFactory.getInterface(d).getType(),d));
    }

}