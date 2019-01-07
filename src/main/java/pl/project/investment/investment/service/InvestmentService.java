package pl.project.investment.investment.service;

import org.springframework.stereotype.Service;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.service.impl.ValidationServiceImpl;

import java.util.List;


@Service
public class InvestmentService {

    private ValidationService validationService = new ValidationServiceImpl();

    private InvestmentDAO investmentDAO;

    public InvestmentService(InvestmentDAO investmentDAO) {
        this.investmentDAO = investmentDAO;
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
