package com.codingexercise.loanevaluation.service;

import com.codingexercise.service.EmiCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class EmiCalculatorTest {

    private final EmiCalculator emiCalculator = new EmiCalculator();

    @Test
    void shouldCalculateEmiCorrectly() {

        BigDecimal emi = emiCalculator.calculateEmi(
                new BigDecimal("500000"),
                new BigDecimal("12"),
                36
        );

        assertThat(emi).isEqualByComparingTo(new BigDecimal("16607.27"));
    }
}