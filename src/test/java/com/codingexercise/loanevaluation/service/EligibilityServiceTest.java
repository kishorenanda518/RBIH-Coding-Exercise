package com.codingexercise.loanevaluation.service;

import com.codingexercise.enums.EmploymentType;
import com.codingexercise.enums.LoanPurpose;
import com.codingexercise.model.request.ApplicantDTO;
import com.codingexercise.model.request.LoanApplicationRequest;
import com.codingexercise.model.request.LoanDetailsDTO;
import com.codingexercise.service.EligibilityService;
import com.codingexercise.service.EmiCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EligibilityServiceTest {

    private final EmiCalculator emiCalculator = new EmiCalculator();
    private final EligibilityService service =
            new EligibilityService(emiCalculator);

    @Test
    void shouldRejectIfCreditScoreBelow600() {

        LoanApplicationRequest request = buildRequest(580);

        List<String> reasons =
                service.evaluate(request, new BigDecimal("12"));

        assertThat(reasons).contains("CREDIT_SCORE_BELOW_600");
    }

    private LoanApplicationRequest buildRequest(int creditScore) {

        ApplicantDTO applicant = new ApplicantDTO();
        applicant.setName("Test");
        applicant.setAge(30);
        applicant.setMonthlyIncome(new BigDecimal("50000"));
        applicant.setEmploymentType(EmploymentType.SALARIED);
        applicant.setCreditScore(creditScore);

        LoanDetailsDTO loan = new LoanDetailsDTO();
        loan.setAmount(new BigDecimal("500000"));
        loan.setTenureMonths(36);
        loan.setPurpose(LoanPurpose.PERSONAL);

        LoanApplicationRequest request = new LoanApplicationRequest();
        request.setApplicant(applicant);
        request.setLoan(loan);

        return request;
    }
}