package com.codingexercise.model.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OfferResponseDTO {

    private BigDecimal interestRate;
    private int tenureMonths;
    private BigDecimal emi;
    private BigDecimal totalPayable;
}