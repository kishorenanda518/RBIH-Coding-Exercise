package com.codingexercise.loanevaluation.service;

import com.codingexercise.enums.EmploymentType;
import com.codingexercise.enums.RiskBand;
import com.codingexercise.service.InterestRateService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class InterestRateServiceTest {

    private final InterestRateService service = new InterestRateService();

    @Test
    void shouldCalculateCorrectInterestRate() {

        BigDecimal rate = service.calculateFinalRate(
                RiskBand.MEDIUM,
                EmploymentType.SELF_EMPLOYED,
                new BigDecimal("1500000")
        );

        // 12 + 1.5 + 1 + 0.5 = 15.0
        assertThat(rate).isEqualByComparingTo(new BigDecimal("15.00"));
    }
}