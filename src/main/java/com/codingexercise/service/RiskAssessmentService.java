package com.codingexercise.service;

import com.codingexercise.enums.RiskBand;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentService {

    public RiskBand classify(int creditScore) {

        if (creditScore >= 750) {
            return RiskBand.LOW;
        } else if (creditScore >= 650) {
            return RiskBand.MEDIUM;
        } else {
            return RiskBand.HIGH;
        }
    }
}