package pl.project.investment.investment.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.project.investment.investment.JSON.JsonModel;
import pl.project.investment.investment.JSON.ResultModel;
import pl.project.investment.investment.dao.CalculationDAO;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Calculation;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.enums.TypeImplementation;
import pl.project.investment.investment.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculationServiceTest {

    private Investment investment;
    private Calculation calculation;

    @MockBean
    private CalculationDAO calculationDAO;
    @MockBean
    private InvestmentDAO investmentDAO;
    @Autowired
    CalculationService calculationService;

    @Before
    public void init(){
        investment = new Investment(1, "Lokata", 4.0,
                LocalDate.of(2018, 10, 1),
                LocalDate.of(2018, 10, 30));

        calculation = new Calculation(1, 1000.00, LocalDate.now(), investment, 3.33);
    }

    @Test
    public void getCalculationByIdCalculationExist() {
        ResultModel resultModel = new ResultModel(calculation);

        when(calculationDAO.findById(1)).thenReturn(Optional.ofNullable(calculation));
        assertEquals(calculationService.getCalculationById(1),resultModel);
    }

    @Test(expected = NotFoundException.class)
    public void getCalculationByIdCalculationNotExist() {
        when(calculationDAO.findById(1)).thenReturn(null);
        calculationService.getCalculationById(2);
    }

    @Test
    public void doCalculation() {
        ResultModel rm = new ResultModel(100,4.0,29,
                LocalDate.now(),0.32);
        JsonModel jsonModel = new JsonModel(TypeImplementation.EndAlgorithm,100.0);
        when(investmentDAO.findById(1)).thenReturn(Optional.ofNullable(investment));
        ResultModel resultModel = calculationService.doCalculation(1 ,jsonModel);

        assertEquals(rm ,resultModel);
    }
}