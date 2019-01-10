package pl.project.investment.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.project.investment.investment.JSON.JsonModel;
import pl.project.investment.investment.JSON.ResultModel;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.response.ResponseHeader;
import pl.project.investment.investment.service.CalculationService;
import pl.project.investment.investment.service.InvestmentService;

import javax.validation.Valid;
import java.util.List;


/**
 * InvestmentResource class with mapping 
 *
 */
@RestController

public class InvestmentController {

	private CalculationService calculationService;
	private InvestmentService investmentService;
	private ResponseHeader responseHeader = new ResponseHeader();

	@Lazy
	@Autowired
	public InvestmentController(
							  CalculationService calculationService,
							  InvestmentService investmentService){
		this.calculationService = calculationService;
		this.investmentService = investmentService;
	}

	/**
	 * 
	 * @return InvestmentRepository List with all investment 
	 */
	@GetMapping("/investments")
	public List<Investment> retrieveAllInvestment() {

		return investmentService.getAllInvestment();
	}
	
	@PutMapping("/investments/add")
	@ResponseBody
	public ResponseEntity<String> addInvestment(@Valid @RequestBody Investment investment) {


		int id = investmentService.save(investment);

		return new ResponseEntity<>("",responseHeader.getHeader("/investments/{id}",id), HttpStatus.CREATED);
	}

	@PostMapping("/investments/{id}/calculate")
	public ResponseEntity<String> calculate(@PathVariable int id, @Valid @RequestBody JsonModel jsonModel) {

		ResultModel rm = calculationService.doCalculation(id ,jsonModel);

		return new ResponseEntity<>(rm.toString(),
				responseHeader.getHeader("/calculations/{id}",rm.getCalculationId()), HttpStatus.OK);
	}
	/**
	 * 
	 * @param id historical calculation
	 * @return ResultModel object with result of old Calculation
	 */
	@GetMapping("/calculations/{id}")
	public ResultModel getCalculationById(@PathVariable int id) {
		return calculationService.getCalculationById(id);
	}

}
