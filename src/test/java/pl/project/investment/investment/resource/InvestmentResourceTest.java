package pl.project.investment.investment.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.project.investment.investment.dao.CalculationDAO;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Calculation;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.exception.AppExceptionsHandler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InvestmentResourceTest {

    private MockMvc mockMvc;

    @Mock
    private CalculationDAO calculationDAO;
    @Mock
    private InvestmentDAO investmentDAO;
    @InjectMocks
    private InvestmentResource investmentResource;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(investmentResource)
                .setControllerAdvice(new AppExceptionsHandler())
                .build();
    }

    @Test
    public void testGettingAllInvestment() throws Exception {
        List<Investment> investments = Arrays.asList(
                new Investment(1, "Lokata", 4.0, LocalDate.of(2018, 10, 1),
                        LocalDate.of(2018, 10, 30)),
                new Investment(2, "Test", 4.0, LocalDate.of(2018, 10, 1),
                        LocalDate.of(2018, 10, 30))
        );
        when(investmentDAO.findAll()).thenReturn(investments);
        mockMvc.perform(get("/investments"))
                .andExpect(jsonPath("$[1].name", is("Test")))
                .andExpect(jsonPath("$[0].investmentId", is(1)));
    }


    @Test
    public void testAddingInvestment() throws Exception {
        Investment savedInvestment = new Investment(1,
                "Lokata",
                4.0,
                LocalDate.of(2018,
                        10,
                        1),
                LocalDate.of(2018,
                        10,
                        30));
        Investment investment = new Investment("Lokata",
                4.0,
                LocalDate.of(2018,
                        10,
                        1),
                LocalDate.of(2018,
                        10,
                        30));


        when(investmentDAO.save(investment)).thenReturn(savedInvestment);

        mockMvc.perform(put("/investments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Lokata\",\"dateFrom\":\"2018-10-01\",\"dateTo\":\"2018-10-30\",\"interestRate\":\"4.0\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCalculateEndAlgorithm() throws Exception {
        Investment inv = new Investment(1, "Lokata", 4.0, LocalDate.of(2018, 10, 1), LocalDate.of(2018, 10, 30));
        Calculation cal = new Calculation(1000.00, LocalDate.now(), inv, 4.0);

        when(investmentDAO.findById(1)).thenReturn(java.util.Optional.of(inv));
        when(calculationDAO.save(cal)).thenReturn(cal);

        mockMvc.perform(post("/investments/{id}/calculate", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"EndAlgorithm\", \"amount\": \"1000.00\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCalculateDayAlgorithm() throws Exception {
        Investment inv = new Investment(1, "Lokata", 4.0, LocalDate.of(2018, 10, 1), LocalDate.of(2018, 10, 30));
        Calculation cal = new Calculation(1000.00, LocalDate.now(), inv, 4.0);

        when(investmentDAO.findById(1)).thenReturn(java.util.Optional.of(inv));
        when(calculationDAO.save(cal)).thenReturn(cal);

        mockMvc.perform(post("/investments/{id}/calculate", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"DayAlgorithm\", \"amount\": \"1000.00\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCalculateWrongAmountLetter() throws Exception {
        mockMvc.perform(post("/investments/{id}/calculate", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"DayAlgorithm\", \"amount\": \"bcs\"}"))
                .andExpect(status().isBadRequest());
    }


    @Test()
    public void testCalculateWrongAlgorithm() throws Exception {
        mockMvc.perform(post("/investments/{id}/calculate", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"EveryDayAlgorithm\", \"amount\": \"0.00\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculateZeroAmount() throws Exception {
        Investment inv = new Investment(1, "Lokata", 4.0, LocalDate.of(2018, 10, 1), LocalDate.of(2018, 10, 30));

        when(investmentDAO.findById(1)).thenReturn(java.util.Optional.of(inv));

        mockMvc.perform(post("/investments/{id}/calculate", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"DayAlgorithm\", \"amount\": \"0.00\"}"))
                .andExpect(jsonPath("@.message", is("Found 0.00 in amount field")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculateNegativeAmount() throws Exception {
        Investment inv = new Investment(1, "Lokata", 4.0, LocalDate.of(2018, 10, 1), LocalDate.of(2018, 10, 30));

        when(investmentDAO.findById(1)).thenReturn(java.util.Optional.of(inv));

        mockMvc.perform(post("/investments/{id}/calculate", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"DayAlgorithm\", \"amount\": \"-20.00\"}"))
                .andExpect(jsonPath("@.message", is("Value is negative")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void TestGettingCalculationById() throws Exception {
        Investment inv = new Investment(1, "Lokata", 4.0,
                LocalDate.of(2018, 10, 1),
                LocalDate.of(2018, 10, 30));

        Calculation cal = new Calculation(1, 1000.00, LocalDate.now(), inv, 3.33);

        when(calculationDAO.findById(1)).thenReturn(cal);
        mockMvc.perform(get("/calculations/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("@.amount", is(1000.0)))
                .andExpect(jsonPath("@.period", is(29)));
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