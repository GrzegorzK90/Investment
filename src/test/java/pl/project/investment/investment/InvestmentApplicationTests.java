package pl.project.investment.investment;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.project.investment.investment.JSON.JsonModel;
import pl.project.investment.investment.dao.CalculationDAO;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Calculation;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.service.CalculationInterface;
import pl.project.investment.investment.service.impl.AtTheEndInterest;
import pl.project.investment.investment.service.impl.DayInterest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InvestmentApplicationTests {

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private CalculationDAO calculationDAO;
	@Autowired
	private InvestmentDAO investmentDAO;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		System.out.println();
		try {
			calculationDAO.deleteAll();
			investmentDAO.deleteAll();
			
			Investment inv = new Investment("Lokata",4.0,LocalDate.of(2018, 10, 1),LocalDate.of(2018, 10, 30));
			investmentDAO.save(inv);
			
			Calculation cal = new Calculation(1000.00,LocalDate.now(),inv,3.33);
			calculationDAO.save(cal);
		}catch(Exception ex) {
			
		}
	}
	@Test
	public void contextLoads() {
	}

	
	@Test
	public void testAddInvestment() throws Exception {
		Investment test = new Investment();
		test.setName("Test");
		test.setDateFrom(LocalDate.of(2018, 1, 1));
		test.setDateTo(LocalDate.of(2018, 3, 01));
		test.setInterestRate(4.5);
		
		String uri = "/investments/add";
		String inputJson = objectMapper.writeValueAsString(test);
		
		mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).
				andExpect(status().is(204));	
	}
	
	@Test
	public void testPostCalculate() throws Exception {
		int id = investmentDAO.findAll().get(0).getinvestmentId();
		String uri = "/investments/"+ id +"/calculate";
		JsonModel jsonModel = new JsonModel("EndAlgorithm", 400.50);
		String inputJson = objectMapper.writeValueAsString(jsonModel);
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
		.andExpect(status().isOk());
	}
	@Test
	public void testGetInvestment() throws Exception {
		mockMvc.perform(get("/investments")).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	public void testGetCalculations() throws Exception {
		int id = calculationDAO.findAll().get(0).getId();
		String uri = "/calculations/"+id;
		mockMvc.perform(get(uri)).andExpect(status().isOk());
	}
	
	@Test
	public void testEndInterest() {
		double delta = 0.001;
		CalculationInterface test = new AtTheEndInterest();
		assertEquals(1000.00, test.calculateInterest(90, 4, 100000),delta);
		assertEquals(7500027.76, test.calculateInterest(90, 5.5, 545456564),delta);
		assertEquals(12.49, test.calculateInterest(180, 2.5, 999.5),delta);
		assertEquals(497.60, test.calculateInterest(360, 1.1, 45236.4),delta);
		assertEquals(2251.77, test.calculateInterest(90, 8, 112588.54),delta);
		assertEquals(117.10, test.calculateInterest(30, 5.5, 25548.1),delta);
		assertEquals(12645.23, test.calculateInterest(360, 23.1, 54741.25),delta);
		assertEquals(1421.37, test.calculateInterest(90, 0.9, 631721.74),delta);
		assertEquals(305513.29, test.calculateInterest(180, 11, 5554787.14),delta);
		assertEquals(9863.84, test.calculateInterest(360, 2.3, 428862.4),delta);
		assertEquals(4.29, test.calculateInterest(30, 4.1, 1256.47),delta);
		assertEquals(1250.68, test.calculateInterest(180, 2.5, 100054),delta);
		assertEquals(2.12, test.calculateInterest(360, 21.2, 10.00),delta);
		assertEquals(44.98, test.calculateInterest(90, 18, 999.47),delta);
		assertEquals(4.66, test.calculateInterest(30, 1.23, 4547.40),delta);
		assertEquals(516.60, test.calculateInterest(360, 2.87, 18000.15),delta);
		assertEquals(1276.68, test.calculateInterest(90, 0.2, 2553369.2),delta);
	}
	
	@Test
	public void atTheDayInterest() {
		double delta = 0.1;
		CalculationInterface test = new DayInterest();
		assertEquals(1004.96, test.calculateInterest(90, 4, 100000),delta);
		assertEquals(7551246.77, test.calculateInterest(90, 5.5, 545456564),delta);
		assertEquals(12.60, test.calculateInterest(180, 2.5, 999.5),delta);
		assertEquals(500.32, test.calculateInterest(360, 1.1, 45236.4),delta);
		assertEquals(2274.19, test.calculateInterest(90, 8, 112588.54),delta);
		assertEquals(117.36, test.calculateInterest(30, 5.5, 25548.1),delta);
		assertEquals(14219.87, test.calculateInterest(360, 23.1, 54741.25),delta);
		assertEquals(1422.97, test.calculateInterest(90, 0.9, 631721.74),delta);
		assertEquals(314021.75, test.calculateInterest(180, 11, 5554787.14),delta);
		assertEquals(9977.83, test.calculateInterest(360, 2.3, 428862.4),delta);
		assertEquals(4.20, test.calculateInterest(30, 4.1, 1256.47),delta);
		assertEquals(1258.46, test.calculateInterest(180, 2.5, 100054),delta);
		assertEquals(3.60, test.calculateInterest(360, 21.2, 10.00),delta);
		assertEquals(45.97, test.calculateInterest(90, 18, 999.47),delta);
		assertEquals(4.80, test.calculateInterest(30, 1.23, 4547.40),delta);
		assertEquals(524.13, test.calculateInterest(360, 2.87, 18000.15),delta);
		assertEquals(1277.10, test.calculateInterest(90, 0.2, 2553369.2),delta);
	}
}
