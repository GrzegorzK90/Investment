package pl.project.investment.investment.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.project.investment.investment.dao.CalculationDAO;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Calculation;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.enums.ErrorMessages;
import pl.project.investment.investment.enums.PeriodValue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InvestmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculationDAO calculationDAO;
    @MockBean
    private InvestmentDAO investmentDAO;

    private final LocalDate testDateFrom = LocalDate.now().minusYears(1);
    private final LocalDate testDateTo = LocalDate.now().plusYears(1);


    @Test
    public void testGettingAllInvestment() throws Exception {
        List<Investment> investments = Arrays.asList(
                new Investment(1, "InvestmentTest", 4.0, PeriodValue.valueOf(3), testDateFrom,
                        testDateTo),
                new Investment(2, "Test", 4.0, PeriodValue.valueOf(3), testDateFrom.minusYears(1),
                        testDateTo)
        );
        when(investmentDAO.findAll()).thenReturn(investments);
        this.mockMvc.perform(get("/investments"))
                .andExpect(jsonPath("$[1].name", is("Test")))
                .andExpect(jsonPath("$[0].investmentId", is(1)));
    }

    @Test
    public void testAddingInvestment() throws Exception {
        Investment savedInvestment = new Investment(1,
                "InvestmentTest",
                4.0, PeriodValue.valueOf(3),
                testDateFrom,
                testDateTo);
        Investment investment = new Investment("InvestmentTest",
                4.0, PeriodValue.valueOf(3),
                testDateFrom,
                testDateTo);

        when(investmentDAO.save(investment)).thenReturn(savedInvestment);

        mockMvc.perform(put("/investments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"InvestmentTest\",\"dateFrom\":\"" + testDateFrom +
                        "\",\"depositPeriod\":\"3\",\"dateTo\":\"" + testDateTo + "\",\"interestRate\":\"4.0\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAddingInvestmentWrongPeriod() throws Exception {

        mockMvc.perform(put("/investments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"InvestmentTest\",\"dateFrom\":\"" + testDateFrom +
                        "\",\"depositPeriod\":\"5\",\"dateTo\":\"" + testDateTo + "\",\"interestRate\":\"4.0\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddingWrongDateInvestment() throws Exception {
        mockMvc.perform(put("/investments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"InvestmentTest\",\"depositPeriod\":\"3\",\"dateFrom\":\"" + testDateTo + "\",\"dateTo\":\""
                        + testDateFrom + "\",\"interestRate\":\"4.0\"}"))
                .andExpect(jsonPath("@.message", is(ErrorMessages.WRONG_DATE.getErrorMessage())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddingNullNameInvestment() throws Exception {
        mockMvc.perform(put("/investments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"depositPeriod\":\"3\",\"dateFrom\":\"" + testDateFrom + "\",\"dateTo\":\"" + testDateTo
                        + "\",\"interestRate\":\"4.0\"}"))
                .andExpect(jsonPath("@.message", is("Empty name field")))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testWrongDataPeriodExceptionDay() throws Exception {
        mockMvc.perform(put("/investments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"InvestmentTest\",\"dateFrom\":\"" + testDateTo + "\",\"depositPeriod\":\"3\",\"dateTo\":\""
                        + testDateFrom + "\",\"interestRate\":\"4.0\"}"))
                .andExpect(jsonPath("@.message", is(ErrorMessages.WRONG_DATE.getErrorMessage())))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testWrongInterestRateTypeException() throws Exception {
        mockMvc.perform(put("/investments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"InvestmentTest\",\"dateFrom\":\"" + testDateFrom + "\",\"depositPeriod\":\"3\",\"dateTo\":\""
                        + testDateTo + "\",\"interestRate\":\"Do\"}"))
                .andExpect(jsonPath("@.message", is(ErrorMessages.CONVERSION_TYPE_ERROR.getErrorMessage())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculate() throws Exception {
        Investment inv = new Investment(1, "InvestmentTest", 4.0, PeriodValue.valueOf(3),
                testDateFrom,
                testDateTo);

        Calculation calculation2 = new Calculation(0, 100.00, 90, LocalDate.now(), inv, 1.0);
        Calculation calculation1 = new Calculation(1, 100.00, 90, LocalDate.now(), inv, 1.0);

        when(investmentDAO.findById(1)).thenReturn(Optional.of(inv));
        when(calculationDAO.save(calculation2)).thenReturn(calculation1);

        mockMvc.perform(post("/investments/{id}/calculate", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"EndAlgorithm\", \"amount\": \"100.00\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCalculateWrongAmountLetterException() throws Exception {
        mockMvc.perform(post("/investments/{id}/calculate", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"DayAlgorithm\", \"amount\": \"bcs\"}"))
                .andExpect(jsonPath("@.message", is(ErrorMessages.CONVERSION_TYPE_ERROR.getErrorMessage())))
                .andExpect(status().isBadRequest());
    }


    @Test()
    public void testCalculateWrongAlgorithmException() throws Exception {
        mockMvc.perform(post("/investments/{id}/calculate", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"EveryDayAlgorithm\", \"amount\": \"0.00\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculateZeroAmountException() throws Exception {
        Investment inv = new Investment(1, "InvestmentTest", 4.0, PeriodValue.valueOf(3),
                testDateFrom,
                testDateTo);

        when(investmentDAO.findById(1)).thenReturn(java.util.Optional.of(inv));

        mockMvc.perform(post("/investments/{id}/calculate", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"DayAlgorithm\", \"amount\": \"0.00\"}"))
                .andExpect(jsonPath("@.message", is(ErrorMessages.WRONG_VALUE.getErrorMessage() + "amount = 0.0")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculateNegativeAmountException() throws Exception {
        Investment inv = new Investment(1, "InvestmentTest", 4.0, PeriodValue.valueOf(3),
                testDateFrom,
                testDateTo);

        when(investmentDAO.findById(1)).thenReturn(java.util.Optional.of(inv));

        mockMvc.perform(post("/investments/{id}/calculate", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"DayAlgorithm\", \"amount\": \"-20.00\"}"))
                .andExpect(jsonPath("@.message", is(ErrorMessages.WRONG_VALUE.getErrorMessage() + "amount = -20.0")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void TestGettingCalculationById() throws Exception {
        Investment inv = new Investment(1, "InvestmentTest", 4.0, PeriodValue.valueOf(3),
                testDateFrom,
                testDateTo);

        Calculation cal = new Calculation(1, 1000.0, 90, LocalDate.now(), inv, 3.33);

        when(calculationDAO.findById(1)).thenReturn(Optional.of(cal));

        mockMvc.perform(get("/calculations/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("@.amount", is(1000.0)))
                .andExpect(jsonPath("@.period", is(90)));
    }

    @Test
    public void testNotFoundExceptionCalculations() throws Exception {
        mockMvc.perform(get("/calculations/{id]", 1))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testNotFoundExceptionInvestments() throws Exception {
        mockMvc.perform(post("/investments/{id}/calculate", 1))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddingWithoutRequest() throws Exception {
        mockMvc.perform(put("/investments/add"))
                .andExpect(status().isBadRequest());
    }
}