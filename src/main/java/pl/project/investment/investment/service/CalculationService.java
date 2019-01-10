package pl.project.investment.investment.service;

import com.google.common.annotations.GwtCompatible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.investment.investment.JSON.JsonModel;
import pl.project.investment.investment.JSON.ResultModel;
import pl.project.investment.investment.dao.CalculationDAO;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Calculation;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.enums.ErrorMessages;
import pl.project.investment.investment.exception.NotFoundException;
import pl.project.investment.investment.exception.WrongDataException;
import pl.project.investment.investment.service.impl.ValidationServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@GwtCompatible
public class CalculationService {

    private CalculationDAO calculationDAO;
    private InvestmentDAO investmentDAO;
    private ValidationServiceImpl validationServiceImpl;
    private CalculationFactory calculationFactory;


    @Autowired
    public CalculationService(CalculationDAO calculationDAO, InvestmentDAO investmentDAO,
                              ValidationServiceImpl validationServiceImpl, CalculationFactory calculationFactory){
        this.calculationDAO = calculationDAO;
        this.investmentDAO = investmentDAO;
        this.validationServiceImpl = validationServiceImpl;
        this.calculationFactory = calculationFactory;
    }

    public ResultModel doCalculation(int id, JsonModel jsonModel) {
        checkArgument(jsonModel != null,ErrorMessages.WRONG_REQUEST_BODY);

        Optional<Investment> investmentOptional = investmentDAO.findById(id);
        if (!investmentOptional.isPresent())
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

            validationServiceImpl.isAmountCorrect(jsonModel.getAmount());
            Calculation calculation  = generateCalculation(calculationFactory.
                    getInterface(jsonModel.getName()), investmentOptional.get(),jsonModel.getAmount());
            calculationDAO.save(calculation);

            return new ResultModel(calculation);
    }

        private Calculation generateCalculation(CalculationInterface calculationInterface, Investment investment, double amount)
                throws WrongDataException
        {
            int days = investment.getDateTo().getDayOfYear() - investment.getDateFrom().getDayOfYear();
            double interestRate = investment.getInterestRate();
            LocalDate today = LocalDate.now();
            double profit = calculationInterface.calculateInterest(days, interestRate, amount);

            return new Calculation(days, amount, today, investment, profit);
    }

    public ResultModel getCalculationById(int id){
        Optional<Calculation> calculationOptional = calculationDAO.findById(id);
        if(!calculationOptional.isPresent()){
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " " + id);
        }
        return new ResultModel(calculationOptional.get());
    }
}
