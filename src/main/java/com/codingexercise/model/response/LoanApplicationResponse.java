package com.codingexercise.model.response;

import com.codingexercise.enums.LoanStatus;
import com.codingexercise.enums.RiskBand;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class LoanApplicationResponse {

    private UUID applicationId;
    private LoanStatus status;
    private RiskBand riskBand;
    private OfferResponseDTO offer;
    private List<String> rejectionReasons;
}