package ru.unn.agile.mortgagecalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MortgageCalculatorViewModelTests {
    private MortgageCalculatorViewModel viewModel;

    private void setCorrectInputs() {
        viewModel.loanPeriodProperty().set("12");
        viewModel.loanPeriodTypeProperty().set("Month");
        viewModel.apartmentPriceProperty().set("200");
        viewModel.firstPaymentProperty().set("20");
        viewModel.interestRateProperty().set("3");
        viewModel.monthlyComissionsProperty().set("1");
        viewModel.monthlyComissionsTypeProperty().set("Rubles");
        viewModel.oneTimeComisionsProperty().set("2");
        viewModel.oneTimeComisionsTypeProperty().set("Percent");
        viewModel.typeOfPaymentProperty().set("Annuity");
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new MortgageCalculatorViewModel(new MortgageCalculatorFakeLogger());
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void setEmptyStringInTextFieldsByDefault() {
        assertEquals("", viewModel.getApartmentPrice());
        assertEquals("", viewModel.getFirstPayment());
        assertEquals("", viewModel.getInterestRate());
        assertEquals("", viewModel.getLoanPeriod());
        assertEquals("", viewModel.getLoanPeriodType());
        assertEquals("", viewModel.getMonthlyComissions());
        assertEquals("", viewModel.getMonthlyComissionsType());
        assertEquals("", viewModel.getOneTimeComisions());
        assertEquals("", viewModel.getOneTimeComisionsType());
        assertEquals("", viewModel.getTypeOfPayment());
    }

    @Test
    public void canShowErrorAfterInvalidInput() {
        viewModel.apartmentPriceProperty().set("ee");
        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void canShowErrorAfterInvalidInputPeriod() {
        viewModel.loanPeriodProperty().set("1.2");
        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void canClearErrorAfterValidInput() {
        viewModel.apartmentPriceProperty().set("ee");
        setCorrectInputs();
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void canClearResultByDefault() {
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void canCalculate() {
        setCorrectInputs();

        viewModel.calculate();

        assertEquals("Final amount 198.54; Overpayment "
                + "18.539999999999992; With month payment "
                + "16.24 for 12 months.", viewModel.getResult());
    }

    @Test
    public void canClearOutputAfterChangeInput() {
        setCorrectInputs();

        viewModel.calculate();
        viewModel.apartmentPriceProperty().set("2");

        assertEquals("", viewModel.getResult());
    }

    @Test
    public void canCalculateWithDifferentialPayment() {
        setCorrectInputs();
        viewModel.typeOfPaymentProperty().set("Differential");

        viewModel.calculate();

        assertEquals("Final amount 198.53; Overpayment 18.53; "
                + "With month payment 16.45 for 12 months.", viewModel.getResult());
    }

    @Test
    public void canCalculateWithCommisionInPercent() {
        setCorrectInputs();
        viewModel.monthlyComissionsTypeProperty().set("Percent");

        viewModel.calculate();

        assertEquals("Final amount 208.14; Overpayment 28.139999999999986; "
                + "With month payment 17.04 for 12 months.", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithEmptyLoanPeriodProperty() {
        setCorrectInputs();
        viewModel.loanPeriodProperty().set("");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithEmptyLoanPeriodTypeProperty() {
        setCorrectInputs();
        viewModel.loanPeriodTypeProperty().set("");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithEmptyFirstPaymentProperty() {
        setCorrectInputs();
        viewModel.firstPaymentProperty().set("");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithEmptyInterestRateProperty() {
        setCorrectInputs();
        viewModel.interestRateProperty().set("");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithEmptyMonthlyComissionsProperty() {
        setCorrectInputs();
        viewModel.monthlyComissionsProperty().set("");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithEmptyMonthlyComissionsTypeProperty() {
        setCorrectInputs();
        viewModel.monthlyComissionsTypeProperty().set("");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithEmptyOneTimeComisionsProperty() {
        setCorrectInputs();
        viewModel.oneTimeComisionsProperty().set("");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithEmptyOneTimeComisionsTypeProperty() {
        setCorrectInputs();
        viewModel.oneTimeComisionsTypeProperty().set("");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithEmptyTypeOfPaymentProperty() {
        setCorrectInputs();
        viewModel.typeOfPaymentProperty().set("");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithNegativeValues() {
        setCorrectInputs();
        viewModel.firstPaymentProperty().set("-5");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateFirstPaymentBiggerOrEqualsToPrice() {
        setCorrectInputs();
        viewModel.firstPaymentProperty().set("5");
        viewModel.apartmentPriceProperty().set("5");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithInterestRateBiggerThen60() {
        setCorrectInputs();
        viewModel.interestRateProperty().set("61");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void cantCalculateWithPercentBiggerThen100() {
        setCorrectInputs();
        viewModel.monthlyComissionsProperty().set("101");
        viewModel.monthlyComissionsTypeProperty().set("Percent");

        viewModel.calculate();

        assertEquals("Incorrect input", viewModel.getResult());
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new MortgageCalculatorViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }
}
