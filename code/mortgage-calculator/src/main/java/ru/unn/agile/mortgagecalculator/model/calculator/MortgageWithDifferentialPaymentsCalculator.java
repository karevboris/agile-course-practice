package ru.unn.agile.mortgagecalculator.model.calculator;

import ru.unn.agile.mortgagecalculator.model.parameters.MortgageParameters;
import ru.unn.agile.mortgagecalculator.model.report.MortgageMonthReport;
import ru.unn.agile.mortgagecalculator.model.report.MortgageReport;

public class MortgageWithDifferentialPaymentsCalculator extends MortgageCalculator {

    @Override
    public MortgageReport calculate(MortgageParameters parameters) {
        int months = parameters.getMonthsPeriod();
        double monthPercent = parameters.getMonthPercent();
        double basicPayment = parameters.getAmount() / months;
        double currentAmount = parameters.getAmount();

        MortgageReport report = new MortgageReport(parameters.getAmount());

        double finalAmount = 0;

        while (months > 0) {
            double percentPayment = currentAmount * monthPercent;
            double payment = basicPayment + percentPayment;
            currentAmount -= basicPayment;
            finalAmount += payment;

            MortgageMonthReport monthReport = new MortgageMonthReport(payment, basicPayment, percentPayment, currentAmount);
            report.add(monthReport);

            months--;
        }

        report.setFinalAmount(round(finalAmount));

        return report;
    }

}
