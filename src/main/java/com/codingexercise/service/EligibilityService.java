package com.codingexercise.service;

import com.codingexercise.model.request.LoanApplicationRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.codingexercise.errors.ErrorConstants.*;

@Service
public class EligibilityService {

    private final EmiCalculator emiCalculator;

    public EligibilityService(EmiCalculator emiCalculator) {
        this.emiCalculator = emiCalculator;
    }

    public List<String> evaluate(LoanApplicationRequest request,
                                 BigDecimal finalRate) {

        List<String> reasons = new ArrayList<>();

        int creditScore = request.getApplicant().getCreditScore();
        int age = request.getApplicant().getAge();
        int tenureMonths = request.getLoan().getTenureMonths();

        BigDecimal income = request.getApplicant().getMonthlyIncome();
        BigDecimal loanAmount = request.getLoan().getAmount();


        if (creditScore < 600) {
            reasons.add(CREDIT_SCORE_BELOW_600);
        }

        int tenureYears = tenureMonths / 12;
        if (age + tenureYears > 65) {
            reasons.add(AGE_TENURE_LIMIT_EXCEEDED);
        }

        BigDecimal emi = emiCalculator.calculateEmi(loanAmount, finalRate, tenureMonths);
        BigDecimal sixtyPercent = income.multiply(new BigDecimal("0.6"));

        if (emi.compareTo(sixtyPercent) > 0) {
            reasons.add(EMI_EXCEEDS_60_PERCENT);
        }

        return reasons;
    }
}