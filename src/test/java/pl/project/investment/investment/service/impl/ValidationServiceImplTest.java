package pl.project.investment.investment.service.impl;

import org.junit.Before;
import org.junit.Test;
import pl.project.investment.investment.service.ValidationService;

import static org.junit.Assert.*;

public class ValidationServiceImplTest {

    private ValidationService validationService;

    @Before
    public void init(){
        validationService = new ValidationServiceImpl();
    }

    @Test
    public void isNegativeIntTrue() {
        assertTrue(validationService.isNegative(-1));
    }

    @Test
    public void isNegativeIntFalseZero() {
        assertFalse(validationService.isNegative(0));
    }
    @Test
    public void isNegativeIntFalseBiger() {
        assertFalse(validationService.isNegative(1));
    }
    @Test
    public void isNegativeDoubleTrue() {
        assertTrue(validationService.isNegative(-0.1));
    }

    @Test
    public void isNegativeDoubleFalseZero() {
        assertFalse(validationService.isNegative(0.00));
    }
    @Test
    public void isNegativeDoubleFalseBiger() {
        assertFalse(validationService.isNegative(0.1));
    }
}