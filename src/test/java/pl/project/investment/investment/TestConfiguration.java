package pl.project.investment.investment;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.project.investment.investment.dao.CalculationDAO;
import pl.project.investment.investment.dao.InvestmentDAO;

@Configuration
public class TestConfiguration {

    @Bean
    public CalculationDAO calculationDAO() {
        return Mockito.mock(CalculationDAO.class);
    }

    @Bean
    public InvestmentDAO investmentDAO() {

        return Mockito.mock(InvestmentDAO.class);
    }

}

