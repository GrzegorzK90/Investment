package pl.project.investment.investment.service;

import org.springframework.stereotype.Service;
import pl.project.investment.investment.JSON.JsonModel;
import pl.project.investment.investment.JSON.ResultModel;
import pl.project.investment.investment.dao.CalculationDAO;
import pl.project.investment.investment.dao.InvestmentDAO;
import pl.project.investment.investment.entity.Calculation;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.exception.NotFoundException;
import pl.project.investment.investment.exception.WrongDataException;
import pl.project.investment.investment.response.ErrorMessages;
import pl.project.investment.investment.service.impl.AtTheEndInterest;
import pl.project.investment.investment.service.impl.DayInterest;
import pl.project.investment.investment.service.impl.ValidationServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CalculationService {


    private CalculationDAO calculationDAO;

    private InvestmentDAO investmentDAO;

    private ValidationService validationService;

    private double amount;

    CalculationService(CalculationDAO calculationDAO, InvestmentDAO investmentDAO){
        this.calculationDAO = calculationDAO;
        this.investmentDAO = investmentDAO;
        validationService = new ValidationServiceImpl();
    }


    public ResultModel doCalculation(int id, JsonModel jsonModel) {

        Optional<Investment> investmentOptional = investmentDAO.findById(id);
        if (!investmentOptional.isPresent())
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        if (jsonModel != null) {

            validationService.isAmountCorrect(this.amount = jsonModel.getAmount());

            //WOW

            List<CalculationInterface> list = new ArrayList<>();

            list.add(new AtTheEndInterest());
            list.add(new DayInterest());

            Calculation calculation  = generateCalculation(
                    (CalculationInterface) CalculationFactory.getInstance(list).getInterface(jsonModel.getName()),
                    Objects.requireNonNull(investmentOptional.get()));

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
