package pl.project.investment.investment.service.impl;

import org.springframework.stereotype.Component;
import pl.project.investment.investment.service.ValidationService;

@Component
public class ValidationServiceImpl implements ValidationService {

    public boolean isNegative(int value){
        return value < 0;
    }

    public boolean isNegative(double value){
        return value < 0;
    }

    public boolean isZero(int value){
        return value == 0;
    }

    public boolean isZero(double value){
        return value == 0;
    }
}
