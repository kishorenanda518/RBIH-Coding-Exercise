package com.codingexercise.loanevaluation.service;

import com.codingexercise.enums.EmploymentType;
import com.codingexercise.enums.LoanPurpose;
import com.codingexercise.model.request.*;
import com.codingexercise.model.response.LoanApplicationResponse;
import com.codingexercise.repository.LoanApplicationRepository;
import com.codingexercise.service.*;
import com.codingexercise.mapper.LoanApplicationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class LoanApplicationServiceImplTest {

    private LoanApplicationRepository repository;
    private LoanApplicationServiceImpl service;

    @BeforeEach
    void setUp() {

        // Mock repository only
        repository = Mockito.mock(LoanApplicationRepository.class);

        // Use real implementations for business logic
        RiskAssessmentService riskService = new RiskAssessmentService();
        InterestRateService rateService = new InterestRateService();
        EmiCalculator emiCalculator = new EmiCalculator();
        EligibilityService eligibilityService = new EligibilityService(emiCalculator);
        LoanApplicationMapper mapper = new LoanApplicationMapper();

        service = new LoanApplicationServiceImpl(
                riskService,
                rateService,
                eligibilityService,
                emiCalculator,
                repository,
                mapper
        );
    }

    @Test
    void shouldRejectIfCreditScoreLow() {

        // Build request inline (Option 2 - Simple)

        ApplicantDTO applicant = new ApplicantDTO();
        applicant.setName("Test User");
        applicant.setAge(30);
        applicant.setMonthlyIncome(new BigDecimal("50000"));
        applicant.setEmploymentType(EmploymentType.SALARIED);
        applicant.setCreditScore(550); // Below 600

        LoanDetailsDTO loan = new LoanDetailsDTO();
        loan.setAmount(new BigDecimal("500000"));
        loan.setTenureMonths(36);
        loan.setPurpose(LoanPurpose.PERSONAL);

        LoanApplicationRequest request = new LoanApplicationRequest();
        request.setApplicant(applicant);
        request.setLoan(loan);

        // Execute
        LoanApplicationResponse response = service.evaluate(request);

        // Verify
        assertThat(response.getStatus().name()).isEqualTo("REJECTED");
        assertThat(response.getRejectionReasons())
                .contains("CREDIT_SCORE_BELOW_600");
    }
}