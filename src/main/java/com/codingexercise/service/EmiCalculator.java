package com.codingexercise.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
public class EmiCalculator {

    private static final int SCALE = 10;
    private static final MathContext MC = new MathContext(15, RoundingMode.HALF_UP);

    public BigDecimal calculateEmi(BigDecimal principal,
                                   BigDecimal annualInterestRate,
                                   int tenureMonths) {

        if (principal == null || annualInterestRate == null || tenureMonths <= 0) {
            throw new IllegalArgumentException("Invalid EMI input parameters");
        }

        BigDecimal monthlyRate = annualInterestRate
                .divide(BigDecimal.valueOf(12), SCALE, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(100), SCALE, RoundingMode.HALF_UP);


        BigDecimal onePlusR = BigDecimal.ONE.add(monthlyRate);
        BigDecimal onePlusRPowerN = onePlusR.pow(tenureMonths, MC);

        BigDecimal numerator = principal
                .multiply(monthlyRate, MC)
                .multiply(onePlusRPowerN, MC);


        BigDecimal denominator = onePlusRPowerN.subtract(BigDecimal.ONE);

        BigDecimal emi = numerator.divide(denominator, SCALE, RoundingMode.HALF_UP);


        return emi.setScale(2, RoundingMode.HALF_UP);
    }
}