package ru.unn.agile.mortgagecalculator.viewmodel;

import javafx.beans.property.*;
import ru.unn.agile.mortgagecalculator.model.calculator.MortgageCalculator;
import ru.unn.agile.mortgagecalculator.model.calculator.MortgageWithAnnuityPaymentsCalculator;
import ru.unn.agile.mortgagecalculator.model.calculator.MortgageWithDifferentialPaymentsCalculator;
import ru.unn.agile.mortgagecalculator.model.parameters.MortgageParameters;
import ru.unn.agile.mortgagecalculator.model.parameters.PeriodType;
import ru.unn.agile.mortgagecalculator.model.parameters.commission.Commission;
import ru.unn.agile.mortgagecalculator.model.parameters.commission.FixedCommission;
import ru.unn.agile.mortgagecalculator.model.parameters.commission.PercentCommission;
import ru.unn.agile.mortgagecalculator.model.parameters.monthlycommission.FixedMonthlyCommission;
import ru.unn.agile.mortgagecalculator.model.parameters.monthlycommission.MonthlyCommission;
import ru.unn.agile.mortgagecalculator.model.parameters.monthlycommission.PercentAmountMonthlyCommission;
import ru.unn.agile.mortgagecalculator.model.report.MortgageMonthReport;
import ru.unn.agile.mortgagecalculator.model.report.MortgageReport;

import java.util.List;


public class MortgageCalculatorViewModel {
    private StringProperty apartmentPrice = new SimpleStringProperty();
    private StringProperty firstPayment = new SimpleStringProperty();
    private StringProperty loanPeriod = new SimpleStringProperty();
    private StringProperty loanPeriodType = new SimpleStringProperty();
    private StringProperty interestRate = new SimpleStringProperty();
    private StringProperty oneTimeComisions = new SimpleStringProperty();
    private StringProperty oneTimeComisionsType = new SimpleStringProperty();
    private StringProperty monthlyComissions = new SimpleStringProperty();
    private StringProperty monthlyComissionsType = new SimpleStringProperty();
    private StringProperty typeOfPayment = new SimpleStringProperty();
    private StringProperty result = new SimpleStringProperty();

    private MortgageCalculatorILogger logger;

    public void setLogger(final MortgageCalculatorILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    public MortgageCalculatorViewModel() {
        init();
    }

    public MortgageCalculatorViewModel(final MortgageCalculatorILogger logger) {
        setLogger(logger);
        init();
    }

    private void init() {
        apartmentPrice.set("");
        firstPayment.set("");
        loanPeriod.set("");
        loanPeriodType.set("");
        interestRate.set("");
        oneTimeComisions.set("");
        oneTimeComisionsType.set("");
        monthlyComissions.set("");
        monthlyComissionsType.set("");
        typeOfPayment.set("");
        result.set("");

        apartmentPrice.addListener((observable, oldValue, newValue) -> {
            if (checkInput()) {
                result.set("");
            } else {
                result.set("Incorrect input");
            }
        });

        firstPayment.addListener((observable, oldValue, newValue) -> {
            if (checkInput()) {
                result.set("");
            } else {
                result.set("Incorrect input");
            }
        });

        loanPeriod.addListener((observable, oldValue, newValue) -> {
            if (checkInput()) {
                result.set("");
            } else {
                result.set("Incorrect input");
            }
        });

        interestRate.addListener((observable, oldValue, newValue) -> {
            if (checkInput()) {
                result.set("");
            } else {
                result.set("Incorrect input");
            }
        });

        oneTimeComisions.addListener((observable, oldValue, newValue) -> {
            if (checkInput()) {
                result.set("");
            } else {
                result.set("Incorrect input");
            }
        });

        monthlyComissions.addListener((observable, oldValue, newValue) -> {
            if (checkInput()) {
                result.set("");
            } else {
                result.set("Incorrect input");
            }
        });

        loanPeriodType.addListener((observable, oldValue, newValue) -> {
            onTypeChange();
        });

        typeOfPayment.addListener((observable, oldValue, newValue) -> {
            onTypeChange();
        });

        monthlyComissionsType.addListener((observable, oldValue, newValue) -> {
            onTypeChange();
        });

        oneTimeComisionsType.addListener((observable, oldValue, newValue) -> {
            onTypeChange();
        });

    }

    private boolean checkDoubleInput(final String newValue) {
        if (newValue.equals("")) {
            return false;
        }
        try {
            double val = Double.parseDouble(newValue);
            if (val < 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean checkIntInput(final String newValue) {
        if (newValue.equals("")) {
            return false;
        }
        try {
            int val = Integer.parseInt(newValue);
            if (val < 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void onTypeChange() {
        result.set("");
    }

    public String getApartmentPrice() {
        return apartmentPrice.get();
    }

    public StringProperty apartmentPriceProperty() {
        return apartmentPrice;
    }

    public String getFirstPayment() {
        return firstPayment.get();
    }

    public StringProperty firstPaymentProperty() {
        return firstPayment;
    }

    public String getLoanPeriod() {
        return loanPeriod.get();
    }

    public StringProperty loanPeriodProperty() {
        return loanPeriod;
    }

    public String getLoanPeriodType() {
        return loanPeriodType.get();
    }

    public StringProperty loanPeriodTypeProperty() {
        return loanPeriodType;
    }

    public String getInterestRate() {
        return interestRate.get();
    }

    public StringProperty interestRateProperty() {
        return interestRate;
    }

    public String getOneTimeComisions() {
        return oneTimeComisions.get();
    }

    public StringProperty oneTimeComisionsProperty() {
        return oneTimeComisions;
    }

    public String getOneTimeComisionsType() {
        return oneTimeComisionsType.get();
    }

    public StringProperty oneTimeComisionsTypeProperty() {
        return oneTimeComisionsType;
    }

    public String getMonthlyComissions() {
        return monthlyComissions.get();
    }

    public StringProperty monthlyComissionsProperty() {
        return monthlyComissions;
    }

    public String getMonthlyComissionsType() {
        return monthlyComissionsType.get();
    }

    public StringProperty monthlyComissionsTypeProperty() {
        return monthlyComissionsType;
    }

    public String getTypeOfPayment() {
        return typeOfPayment.get();
    }

    public StringProperty typeOfPaymentProperty() {
        return typeOfPayment;
    }

    public String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    private boolean checkInput() {
        if (!checkDoubleInput(apartmentPrice.get())) {
            return false;
        }
        if (!checkDoubleInput(interestRate.get())) {
            return false;
        }
        if (!checkIntInput(loanPeriod.get())) {
            return false;
        }
        if (loanPeriodType.get().equals("")) {
            return false;
        }
        if (!checkDoubleInput(firstPayment.get())) {
            return false;
        }
        if (!checkDoubleInput(oneTimeComisions.get())) {
            return false;
        }
        if (!checkDoubleInput(monthlyComissions.get())) {
            return false;
        }
        if (monthlyComissionsType.get().equals("")) {
            return false;
        }
        if (oneTimeComisionsType.get().equals("")) {
            return false;
        }
        return !typeOfPayment.get().equals("");
    }

    private MortgageParameters createMortgageParametersCalculator() {
        MortgageParameters mortgageParameters = null;

        double amount = Double.parseDouble(apartmentPrice.get());
        double percent = Double.parseDouble(interestRate.get());
        int period = Integer.parseInt(loanPeriod.get());

        PeriodType periodType = loanPeriodType.get().equals("Year")
                ? PeriodType.YEAR : PeriodType.MONTH;

        double initialPayment = Double.parseDouble((firstPayment.get()));
        double fixedComissionAmount = Double.parseDouble((oneTimeComisions.get()));
        double monthlyComissionAmount = Double.parseDouble((monthlyComissions.get()));

        Commission fixedCommission = null;
        MonthlyCommission monthlyCommission = null;

        try {
            fixedCommission = oneTimeComisionsType.get().equals("Percent")
                    ? new PercentCommission(fixedComissionAmount)
                    : new FixedCommission(fixedComissionAmount);
            monthlyCommission = monthlyComissionsType.get().equals("Percent")
                    ? new PercentAmountMonthlyCommission(monthlyComissionAmount)
                    : new FixedMonthlyCommission(monthlyComissionAmount);

            mortgageParameters = new MortgageParameters(amount, percent, periodType, period);
            mortgageParameters.setInitialPayment(initialPayment);
        } catch (Exception e) {
            result.set("Incorrect input");
            return null;
        }
        mortgageParameters.setCommission(fixedCommission);
        mortgageParameters.setMonthlyCommission(monthlyCommission);
        return mortgageParameters;
    }

    public void calculate() {
        if (!checkInput()) {
            result.set("Incorrect input");
            return;
        }

        MortgageParameters mortgageParameters = createMortgageParametersCalculator();
        if (mortgageParameters == null) {
            return;
        }

        MortgageCalculator calculator;
        if (typeOfPayment.get().equals("Annuity")) {
            calculator = new MortgageWithAnnuityPaymentsCalculator();
        } else {
            calculator = new MortgageWithDifferentialPaymentsCalculator();
        }

        MortgageReport report = calculator.calculate(mortgageParameters);
        MortgageMonthReport monthReport = report.getMonthReport(1);
        String resultString = "Final amount " + report.getFinalAmount() + "; "
                + "Overpayment " + report.getOverpayment()
                + "; With month payment " + monthReport.getPayment() + " for "
                + "" + mortgageParameters.getMonthsPeriod() + " months.";
        this.result.set(resultString);
    }

    public List<String> getLog() {
        return logger.getLog();
    }
}
