package com.codingexercise.service;

import com.codingexercise.enums.EmploymentType;
import com.codingexercise.enums.RiskBand;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class InterestRateService {

    private static final BigDecimal BASE_RATE = new BigDecimal("12.0");

    public BigDecimal calculateFinalRate(RiskBand riskBand,
                                         EmploymentType employmentType,
                                         BigDecimal loanAmount) {

        BigDecimal rate = BASE_RATE;

        switch (riskBand) {
            case MEDIUM -> rate = rate.add(new BigDecimal("1.5"));
            case HIGH -> rate = rate.add(new BigDecimal("3.0"));
            case LOW -> rate = rate;
        }

        if (employmentType == EmploymentType.SELF_EMPLOYED) {
            rate = rate.add(new BigDecimal("1.0"));
        }

        if (loanAmount.compareTo(new BigDecimal("1000000")) > 0) {
            rate = rate.add(new BigDecimal("0.5"));
        }

        return rate.setScale(2, RoundingMode.HALF_UP);
    }
}