package com.codingexercise.errors;

public final class ErrorConstants {

    private ErrorConstants() {
    }

    public static final String CREDIT_SCORE_BELOW_600 =
            "CREDIT_SCORE_BELOW_600";

    public static final String AGE_TENURE_LIMIT_EXCEEDED =
            "AGE_TENURE_LIMIT_EXCEEDED";

    public static final String EMI_EXCEEDS_60_PERCENT =
            "EMI_EXCEEDS_60_PERCENT";

    public static final String EMI_EXCEEDS_50_PERCENT =
            "EMI_EXCEEDS_50_PERCENT";

    public static final String VALIDATION_FAILED =
            "Validation failed";

    public static final String CONSTRAINT_VIOLATION =
            "Constraint violation";

    public static final String INTERNAL_SERVER_ERROR =
            "Unexpected error occurred";
}