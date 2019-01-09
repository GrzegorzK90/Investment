package pl.project.investment.investment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Investment;

import java.util.List;


@Service
public class InvestmentService {

    private ValidationService validationService;
    private InvestmentDAO investmentDAO;

    @Autowired
    public InvestmentService(InvestmentDAO investmentDAO,ValidationService validationService) {
        this.investmentDAO = investmentDAO;
        this.validationService = validationService;
    }

    public int save(Investment investment){
        validationService.isInvestmentDateFromToCorrect(investment);
        Investment savedInvestment = investmentDAO.save(investment);

        return savedInvestment.getinvestmentId();
    }

    public List<Investment> getAllInvestment(){
        return investmentDAO.findAll();
    }
}
