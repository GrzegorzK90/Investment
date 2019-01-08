package pl.project.investment.investment.service;

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

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CalculationService {

    private CalculationDAO calculationDAO;
    private InvestmentDAO investmentDAO;
    private ValidationService validationService;
    private CalculationFactory calculationFactory;


    private double amount;

    @Autowired
    CalculationService(CalculationDAO calculationDAO, InvestmentDAO investmentDAO, ValidationService validationService,CalculationFactory calculationFactory){
        this.calculationDAO = calculationDAO;
        this.investmentDAO = investmentDAO;
        this.validationService = validationService;
        this.calculationFactory = calculationFactory;
    }

    public ResultModel doCalculation(int id, JsonModel jsonModel) {

        Optional<Investment> investmentOptional = investmentDAO.findById(id);
        if (!investmentOptional.isPresent())
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        if (jsonModel != null) {
            validationService.isAmountCorrect(this.amount = jsonModel.getAmount());
            Calculation calculation  = generateCalculation(calculationFactory.
                    getInterface(jsonModel.getName()), investmentOptional.get());
            calculationDAO.save(calculation);

            return new ResultModel(calculation);
        }

        return null;
    }


        private Calculation generateCalculation(CalculationInterface calculationInterface, Investment investment)
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
