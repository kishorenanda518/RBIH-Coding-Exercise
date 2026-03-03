package com.codingexercise.entity;

import com.codingexercise.auditing.BaseAuditEntity;
import com.codingexercise.enums.EmploymentType;
import com.codingexercise.enums.LoanPurpose;
import com.codingexercise.enums.LoanStatus;
import com.codingexercise.enums.RiskBand;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "loan_applications")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication extends BaseAuditEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String applicantName;
    private Integer age;
    private BigDecimal monthlyIncome;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    private Integer creditScore;
    private BigDecimal loanAmount;
    private Integer tenureMonths;

    @Enumerated(EnumType.STRING)
    private LoanPurpose purpose;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @Enumerated(EnumType.STRING)
    private RiskBand riskBand;

    private BigDecimal interestRate;
    private BigDecimal emi;
    private BigDecimal totalPayable;

    @ElementCollection
    @CollectionTable(
            name = "loan_rejection_reasons",
            joinColumns = @JoinColumn(name = "application_id")
    )
    @Column(name = "reason")
    private List<String> rejectionReasons;
}