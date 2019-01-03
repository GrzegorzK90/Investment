package pl.project.investment.investment.resource;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.project.investment.investment.JSON.JsonModel;
import pl.project.investment.investment.JSON.ResultModel;
import pl.project.investment.investment.dao.CalculationDAO;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Calculation;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.exception.NotFoundException;
import pl.project.investment.investment.exception.WrongDataException;
import pl.project.investment.investment.service.CalculationFactory;
import pl.project.investment.investment.service.impl.AtTheEndInterest;
import pl.project.investment.investment.service.impl.DayInterest;
/**
 * InvestmentResource class with mapping 
 *
 */
@RestController
public class InvestmentResource {

	private InvestmentDAO investmentRepository;
	private CalculationDAO calculationRepository;

	@Autowired
	public InvestmentResource(InvestmentDAO investmentDAO,
							  CalculationDAO calculationDAO){
		this.investmentRepository = investmentDAO;
		this.calculationRepository = calculationDAO;
	}

	/**
	 * 
	 * @return InvestmentRepository List with all investment 
	 */
	@GetMapping("/investments")
	public List<Investment> retriveAllInvestment() {
		return investmentRepository.findAll();
	}

	/*
	 * 
	 * Put method 
	 * Example JSON
	 *  {
     *   "name": "Lokata",
     *   "dateFrom": "2018-10-01",
     *   "dateTo": "2018-11-01",
     *   "interestRate": 4
     *	}
     *
	 * 
	 */
	@PutMapping("/investments/add")
	@ResponseBody
	public ResponseEntity<String> addInvestment(@RequestBody Investment investment) {
		Investment savedInvestment = investmentRepository.save(investment);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/investments/{id}")
				.buildAndExpand(savedInvestment.getinvestmentId()).toUri();

		return new ResponseEntity<>("location" + location, HttpStatus.CREATED);
	}

	/*
	 * 
	 * Post method 
	 * in name insert - "EndAlgorithm" for Calculation at the end of investment period
	 * "DayAlgorithm" for Algorithm calculating interest each day
	 * 
	 * Example JSON insert 
	 *  {
     *   "name": "EndAlgorithm",
     *   "amount": 400.10
     *	}
     *
	 * 
	 */
	@PostMapping("/investments/{id}/calculate")
	public ResponseEntity<String> calculate(@PathVariable int id, @RequestBody JsonModel jsonModel) {
		Optional<Investment> investmentOptional = investmentRepository.findById(id);
		if (!investmentOptional.isPresent()) {
			throw new NotFoundException("id -" + id);
		}

		CalculationFactory calculationFactory = null;
		URI location = null;
	try {
		if (jsonModel != null) {
			if (jsonModel.getName().equals("EndAlgorithm")) {
				calculationFactory = new CalculationFactory(new AtTheEndInterest(), jsonModel.getAmount());
			} else if (jsonModel.getName().equals("DayAlgorithm")) {
				calculationFactory = new CalculationFactory(new DayInterest(), jsonModel.getAmount());
			}


			Calculation calculation = Objects.requireNonNull(calculationFactory).generateCalculation(investmentOptional.get());

			calculationRepository.save(calculation);

			location = ServletUriComponentsBuilder.fromCurrentRequest().path("/jpa/{id}")
				.buildAndExpand(calculation.getId()).toUri();
		}
	}catch (WrongDataException ex){
		throw  new WrongDataException("ex");
	}
		return new ResponseEntity<>("" + location, HttpStatus.OK);

	}
	/**
	 * 
	 * @param id historical calculation
	 * @return ResultModel object with result of old Calculation
	 */
	@GetMapping("/calculations/{id}")
	public ResultModel getCalculationById(@PathVariable int id) {
		Optional<Calculation> calculationOptional = Optional.ofNullable(calculationRepository.findById(id));
		if (!calculationOptional.isPresent()) {
			throw new NotFoundException("id -" + id);
		}

		Calculation calc = calculationOptional.get();
		ResultModel rm = new ResultModel(calc);
		System.out.println(rm.getAmount());
		return rm;
	}

}
