package com.codingexercise.service;

import com.codingexercise.entity.LoanApplication;
import com.codingexercise.enums.RiskBand;
import com.codingexercise.mapper.LoanApplicationMapper;
import com.codingexercise.model.request.LoanApplicationRequest;
import com.codingexercise.model.response.LoanApplicationResponse;
import com.codingexercise.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.codingexercise.errors.ErrorConstants.EMI_EXCEEDS_50_PERCENT;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final RiskAssessmentService riskService;
    private final InterestRateService rateService;
    private final EligibilityService eligibilityService;
    private final EmiCalculator emiCalculator;
    private final LoanApplicationRepository repository;
    private final LoanApplicationMapper mapper;

    public LoanApplicationServiceImpl(
            RiskAssessmentService riskService,
            InterestRateService rateService,
            EligibilityService eligibilityService,
            EmiCalculator emiCalculator,
            LoanApplicationRepository repository,
            LoanApplicationMapper mapper) {

        this.riskService = riskService;
        this.rateService = rateService;
        this.eligibilityService = eligibilityService;
        this.emiCalculator = emiCalculator;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public LoanApplicationResponse evaluate(LoanApplicationRequest request) {

        LoanApplication entity = mapper.toEntity(request);

        RiskBand riskBand = riskService.classify(
                request.getApplicant().getCreditScore()
        );

        BigDecimal finalRate = rateService.calculateFinalRate(
                riskBand,
                request.getApplicant().getEmploymentType(),
                request.getLoan().getAmount()
        );

        List<String> rejectionReasons =
                eligibilityService.evaluate(request, finalRate);

        if (!rejectionReasons.isEmpty()) {
            mapper.markRejected(entity, rejectionReasons);
            repository.save(entity);
            return mapper.toResponse(entity);
        }

        BigDecimal emi = emiCalculator.calculateEmi(
                request.getLoan().getAmount(),
                finalRate,
                request.getLoan().getTenureMonths()
        );

        BigDecimal fiftyPercent = request.getApplicant()
                .getMonthlyIncome()
                .multiply(new BigDecimal("0.5"));

        if (emi.compareTo(fiftyPercent) > 0) {
            mapper.markRejected(entity, List.of(EMI_EXCEEDS_50_PERCENT));
            repository.save(entity);
            return mapper.toResponse(entity);
        }

        BigDecimal totalPayable = emi
                .multiply(BigDecimal.valueOf(request.getLoan().getTenureMonths()))
                .setScale(2, RoundingMode.HALF_UP);

        mapper.markApproved(entity, riskBand, finalRate, emi, totalPayable);

        repository.save(entity);

        return mapper.toResponse(entity);
    }
}