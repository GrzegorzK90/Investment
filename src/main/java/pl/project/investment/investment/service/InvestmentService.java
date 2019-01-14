package pl.project.investment.investment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.investment.investment.JSON.InvestmentModel;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.enums.ErrorMessages;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class InvestmentService {

    private final InvestmentDAO investmentDAO;

    @Autowired
    public InvestmentService(InvestmentDAO investmentDAO) {

        this.investmentDAO = investmentDAO;
    }

    public int save(InvestmentModel investment) {
        checkArgument(investment.getDateTo().isAfter(investment.getDateFrom()), ErrorMessages.NEGATIVE_DAY.getErrorMessage());
        checkArgument(investment.getInterestRate() > 0,
                ErrorMessages.WRONG_VALUE.getErrorMessage() + "interestRate = " + investment.getInterestRate());

        Investment savedInvestment = investmentDAO.save(new Investment(investment.getName(),
                investment.getInterestRate(), investment.getPeriodValue(), investment.getDateFrom(), investment.getDateTo()));

        return savedInvestment.getInvestmentId();
    }

    public List<Investment> getAllInvestment() {
        return investmentDAO.findAll();
    }

}
