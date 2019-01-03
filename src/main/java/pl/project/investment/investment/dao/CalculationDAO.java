package pl.project.investment.investment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.project.investment.investment.entity.Calculation;

@Repository
public interface CalculationDAO extends JpaRepository<Calculation, Integer> {
    Calculation findById(int id);

}
