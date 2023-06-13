package com.inbank.decision;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * this class holds in itself utility functions, possibly used in multiple different functions and classes
 */
@Data
@Accessors(chain = true)
public class LoanUtils {
    private static final double minAmount = 2000D;
    private static final double maxAmount = 10000D;
    private static final int minPeriod = 12;
    private static final int maxPeriod = 60;

    public static double maxPossibleLoanAmount(int creditModifier, int inputPeriod) {
        var maxLoan = creditModifier * inputPeriod;
        if (maxLoan < minAmount) {
            return minAmount;
        } else return Math.min(maxLoan, maxAmount);
    }

    public static int maxPossibleLoanPeriod(int inputPeriod) {
        if (inputPeriod < minPeriod) {
            return minPeriod;
        } else return Math.min(inputPeriod, maxPeriod);
    }

}
