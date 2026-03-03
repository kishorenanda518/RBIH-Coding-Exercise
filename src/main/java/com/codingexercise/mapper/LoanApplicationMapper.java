package com.codingexercise.mapper;

import com.codingexercise.entity.LoanApplication;
import com.codingexercise.enums.LoanStatus;
import com.codingexercise.enums.RiskBand;
import com.codingexercise.model.request.LoanApplicationRequest;
import com.codingexercise.model.response.LoanApplicationResponse;
import com.codingexercise.model.response.OfferResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class LoanApplicationMapper {

    public LoanApplication toEntity(LoanApplicationRequest request) {

        return LoanApplication.builder()
                .applicantName(request.getApplicant().getName())
                .age(request.getApplicant().getAge())
                .monthlyIncome(request.getApplicant().getMonthlyIncome())
                .employmentType(request.getApplicant().getEmploymentType())
                .creditScore(request.getApplicant().getCreditScore())

                .loanAmount(request.getLoan().getAmount())
                .tenureMonths(request.getLoan().getTenureMonths())
                .purpose(request.getLoan().getPurpose())
                .build();
    }

    public void markApproved(LoanApplication entity,
                             RiskBand riskBand,
                             BigDecimal interestRate,
                             BigDecimal emi,
                             BigDecimal totalPayable) {

        entity.setStatus(LoanStatus.APPROVED);
        entity.setRiskBand(riskBand);
        entity.setInterestRate(interestRate);
        entity.setEmi(emi);
        entity.setTotalPayable(totalPayable);
        entity.setRejectionReasons(null);
    }
    public void markRejected(LoanApplication entity,
                             List<String> rejectionReasons) {

        entity.setStatus(LoanStatus.REJECTED);
        entity.setRiskBand(null);
        entity.setInterestRate(null);
        entity.setEmi(null);
        entity.setTotalPayable(null);
        entity.setRejectionReasons(rejectionReasons);
    }

    public LoanApplicationResponse toResponse(LoanApplication entity) {

        LoanApplicationResponse.LoanApplicationResponseBuilder builder =
                LoanApplicationResponse.builder()
                        .applicationId(entity.getId())
                        .status(entity.getStatus())
                        .riskBand(entity.getRiskBand());

        if (entity.getStatus() == LoanStatus.APPROVED) {
            builder.offer(
                    OfferResponseDTO.builder()
                            .interestRate(entity.getInterestRate())
                            .tenureMonths(entity.getTenureMonths())
                            .emi(entity.getEmi())
                            .totalPayable(entity.getTotalPayable())
                            .build()
            );
        } else {
            builder.rejectionReasons(entity.getRejectionReasons());
        }

        return builder.build();
    }
}