package pl.project.investment.investment.service.impl;

import org.junit.Test;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.exception.WrongDataException;
import pl.project.investment.investment.service.ValidationService;

import java.time.LocalDate;

public class ValidationServiceImplTest {

    private ValidationService validationService = new ValidationServiceImpl();


    @Test
    public void isAmountCorrectCorrectValue() {
        validationService.isAmountCorrect(3.4);
    }
    @Test(expected = WrongDataException.class)
    public void isAmountCorrectNegativeValue() {
        validationService.isAmountCorrect(-0.1);
    }
    @Test(expected = WrongDataException.class)
    public void isAmountCorrectZeroValue() {
        validationService.isAmountCorrect(0.0);
    }

    @Test
    public void isInvestmentDateFromToCorrectCorrectValue() {
        Investment investment = new Investment(1, "Lokata", 4.0,
                LocalDate.of(2018, 10, 1),
                LocalDate.of(2018, 10, 30));
        validationService.isInvestmentDateFromToCorrect(investment);


    }

    @Test(expected = WrongDataException.class)
    public void isInvestmentDateFromToCorrectIncorrectDay() {
        Investment investment = new Investment(1, "Lokata", 4.0,
                LocalDate.of(2018, 10, 30),
                LocalDate.of(2018, 10, 1));
        validationService.isInvestmentDateFromToCorrect(investment);
    }

    @Test(expected = WrongDataException.class)
    public void isInvestmentDateFromToCorrectIncorrectYear() {
        Investment investment = new Investment(1, "Lokata", 4.0,
                LocalDate.of(2018, 10, 1),
                LocalDate.of(2017, 10, 30));
        validationService.isInvestmentDateFromToCorrect(investment);
    }

    @Test(expected = WrongDataException.class)
    public void isInvestmentDateFromToCorrectIncorrectZero() {
        Investment investment = new Investment(1, "Lokata", 4.0,
                LocalDate.of(2018, 10, 1),
                LocalDate.of(2018, 10, 1));
        validationService.isInvestmentDateFromToCorrect(investment);
    }
}