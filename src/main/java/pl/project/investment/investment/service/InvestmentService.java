package pl.project.investment.investment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.service.impl.ValidationServiceImpl;

import java.util.List;


@Service
public class InvestmentService {

    private ValidationServiceImpl validationServiceImpl;
    private InvestmentDAO investmentDAO;

    @Autowired
    public InvestmentService(InvestmentDAO investmentDAO, ValidationServiceImpl validationServiceImpl) {
        this.investmentDAO = investmentDAO;
        this.validationServiceImpl = validationServiceImpl;
    }

    public int save(Investment investment){
        validationServiceImpl.isInvestmentDateFromToCorrect(investment);
        Investment savedInvestment = investmentDAO.save(investment);

        return savedInvestment.getinvestmentId();
    }

    public List<Investment> getAllInvestment(){
        return investmentDAO.findAll();
    }
}
