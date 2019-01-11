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
                "Lokata",
                4.0,
                LocalDate.of(2018,
                        10,
                        1),
                LocalDate.of(2018,
                        10,
                        30));
        InvestmentModel investment = new InvestmentModel("Lokata",
                4.0,
                LocalDate.of(2018,
                        10,
                        1),
                LocalDate.of(2018,
                        10,
                        30));
        Investment investment1 = new Investment("Lokata",
                4.0,
                LocalDate.of(2018,
                        10,
                        1),
                LocalDate.of(2018,
                        10,
                        30));

        Mockito.when(investmentDAO.save(investment1)).thenReturn(savedInvestment);

        assertEquals(1, investmentService.save(investment));

    }

    @Test
    public void getAllInvestment() {
        List<Investment> investments = Arrays.asList(
                new Investment(1, "Lokata", 4.0, LocalDate.of(2018, 10, 1),
                        LocalDate.of(2018, 10, 30)),
                new Investment(2, "Test", 4.0, LocalDate.of(2018, 10, 1),
                        LocalDate.of(2018, 10, 30))
        );
        Mockito.when(investmentDAO.findAll()).thenReturn(investments);

        List<Investment> testList = investmentService.getAllInvestment();

        assertEquals(investments.get(0).getName(), testList.get(0).getName());
    }
}