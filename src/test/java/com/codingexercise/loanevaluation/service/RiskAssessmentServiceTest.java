package com.codingexercise.loanevaluation.service;

import com.codingexercise.enums.RiskBand;
import com.codingexercise.service.RiskAssessmentService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RiskAssessmentServiceTest {

    private final RiskAssessmentService service = new RiskAssessmentService();

    @Test
    void shouldReturnLowRisk() {
        assertThat(service.classify(800)).isEqualTo(RiskBand.LOW);
    }

    @Test
    void shouldReturnMediumRisk() {
        assertThat(service.classify(700)).isEqualTo(RiskBand.MEDIUM);
    }

    @Test
    void shouldReturnHighRisk() {
        assertThat(service.classify(620)).isEqualTo(RiskBand.HIGH);
    }
}