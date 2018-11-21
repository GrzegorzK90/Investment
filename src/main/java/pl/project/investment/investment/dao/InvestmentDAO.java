package pl.project.investment.investment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.project.investment.investment.entity.Investment;

@Repository
public interface InvestmentDAO extends JpaRepository<Investment, Integer>{
	
}
