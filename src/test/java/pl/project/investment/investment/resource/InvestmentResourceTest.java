package pl.project.investment.investment.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jndi.toolkit.url.Uri;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.project.investment.investment.JSON.ResultModel;
import pl.project.investment.investment.dao.CalculationDAO;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Calculation;
import pl.project.investment.investment.entity.Investment;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InvestmentResourceTest {

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Mock
    private CalculationDAO calculationDAO;
    @Mock
    private InvestmentDAO investmentDAO;
    @InjectMocks
    private InvestmentResource investmentResource;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(investmentResource)
                .build();
    }


    @Test
    public void retriveAllInvestment() throws Exception {
        List<Investment> investments = Arrays.asList(
                new Investment(1,"Lokata",4.0,LocalDate.of(2018, 10, 1),
                        LocalDate.of(2018, 10, 30)),
                new Investment(2,"Test",4.0,LocalDate.of(2018, 10, 1),
                        LocalDate.of(2018, 10, 30))
        );
        when(investmentResource.retriveAllInvestment()).thenReturn(investments);
        mockMvc.perform(get("/investments"))
                .andExpect(jsonPath("$[1].name", is("Test")))
                .andExpect(jsonPath("$[0].investmentId", is(1)));
    }

    @Test
    public void addInvestment() throws Exception {
        Investment investment = new Investment(1,"Lokata",4.0,LocalDate.of(2018, 10, 1),LocalDate.of(2018, 10, 30));
        //when(investmentDAO.save(investment)).thenReturn(new ResponseEntity("location ", HttpStatus.CREATED));
        //doNothing().when(investmentDAO).save(investment);
        //when(investmentResource.addInvestment(investment)).thenReturn(new ResponseEntity("location ", HttpStatus.CREATED));

        mockMvc.perform(put("/investments/add")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(asJsonString(investment))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void calculate() {


    }

    @Test
    public void getCalculationById() throws Exception {
        Investment inv = new Investment(1,"Lokata",4.0,LocalDate.of(2018, 10, 1),LocalDate.of(2018, 10, 30));
        Calculation cal = new Calculation(1,1000.00,LocalDate.now(),inv,3.33);
        ResultModel rm = new ResultModel(1000.00,1,2,LocalDate.now(),10);



        when(calculationDAO.findById(1)).thenReturn(cal);
        mockMvc.perform(get("/calculations/{id}",1))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}