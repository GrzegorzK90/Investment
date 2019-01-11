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
import pl.project.investment.investment.enums.TypeImplementation;
import pl.project.investment.investment.exception.NotFoundException;
import pl.project.investment.investment.exception.WrongDataException;

import java.time.LocalDate;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class CalculationService {

    private final CalculationDAO calculationDAO;
    private final InvestmentDAO investmentDAO;
    private final CalculationFactory calculationFactory;


    @Autowired
    public CalculationService(CalculationDAO calculationDAO, InvestmentDAO investmentDAO,
                              CalculationFactory calculationFactory) {
        this.calculationDAO = calculationDAO;
        this.investmentDAO = investmentDAO;
        this.calculationFactory = calculationFactory;

    }

    public ResultModel doCalculation(Integer id, JsonModel jsonModel) {
        checkArgument(jsonModel != null, ErrorMessages.WRONG_REQUEST_BODY);

        Investment investment = getInvestment(id);
        Calculation calculation = generateCalculation(jsonModel, investment);

        return new ResultModel(calculationDAO.save(calculation));
    }

    public ResultModel getCalculationById(Integer id) {
        Optional<Calculation> calculationOptional = calculationDAO.findById(id);
        if (!calculationOptional.isPresent()) {
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " " + id);
        }
        return new ResultModel(calculationOptional.get());
    }

    private Investment getInvestment(Integer id) {
        Optional<Investment> investmentOptional = investmentDAO.findById(id);

        if (!investmentOptional.isPresent())
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return investmentOptional.get();

    }

    private CalculationInterface getInterface(TypeImplementation implementationName) {
        return calculationFactory.getInterface(implementationName);
    }

    private Calculation generateCalculation(JsonModel jsonModel, Investment investment) throws WrongDataException {
        CalculationInterface calculationImpl = getInterface(jsonModel.getName());
        Double amount = jsonModel.getAmount();

        Integer days = investment.getDateTo().getDayOfYear() - investment.getDateFrom().getDayOfYear();
        Double interestRate = investment.getInterestRate();
        LocalDate today = LocalDate.now();
        Double profit = calculationImpl.calculateInterest(days, interestRate, amount);

        return new Calculation(days, amount, today, investment, profit);
    }
}
