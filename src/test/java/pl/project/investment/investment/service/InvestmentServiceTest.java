package pl.project.investment.investment.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.project.investment.investment.JSON.InvestmentModel;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.enums.PeriodValue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvestmentServiceTest {


    @MockBean
    private InvestmentDAO investmentDAO;

    @Autowired
    InvestmentService investmentService;

    @Test
    public void save() {
        Investment savedInvestment = new Investment(1,
                "InvestmentTest",
                4.0, PeriodValue.valueOf(3),
                LocalDate.now().minusMonths(1),
                LocalDate.now().plusMonths(1));
        InvestmentModel investment = new InvestmentModel("InvestmentTest",
                4.0, 3,
                LocalDate.now().minusMonths(1),
                LocalDate.now().plusMonths(1));
        Investment investment1 = new Investment("InvestmentTest",
                4.0, PeriodValue.valueOf(3),
                LocalDate.now().minusMonths(1),
                LocalDate.now().plusMonths(1));

        Mockito.when(investmentDAO.save(investment1)).thenReturn(savedInvestment);

        assertEquals(1, investmentService.save(investment));

    }

    @Test
    public void getAllInvestment() {
        List<Investment> investments = Arrays.asList(
                new Investment(1, "InvestmentTest", 4.0, PeriodValue.valueOf(3), LocalDate.now().minusMonths(1),
                        LocalDate.now().plusMonths(1)),
                new Investment(2, "Test", 4.0, PeriodValue.valueOf(3), LocalDate.now().minusYears(1),
                        LocalDate.now().plusYears(1))
        );
        Mockito.when(investmentDAO.findAll()).thenReturn(investments);

        List<Investment> testList = investmentService.getAllInvestment();

        assertEquals(investments.get(0).getName(), testList.get(0).getName());
    }
}