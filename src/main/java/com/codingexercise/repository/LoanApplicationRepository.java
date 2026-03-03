package com.codingexercise.repository;

import com.codingexercise.entity.LoanApplication;
import com.codingexercise.enums.LoanStatus;
import com.codingexercise.enums.RiskBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanApplicationRepository
        extends JpaRepository<LoanApplication, UUID> {
 boolean existsByApplicantName(String applicantName);

    List<LoanApplication> findByStatus(LoanStatus status);


    List<LoanApplication> findByRiskBand(RiskBand riskBand);


    List<LoanApplication> findByApplicantNameIgnoreCase(String applicantName);


    List<LoanApplication> findByLoanAmountGreaterThanEqual(java.math.BigDecimal amount);
}
