package ru.unn.agile.QuadraticEquasion.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;

import static org.junit.Assert.*;

public class QuadraticEquasionViewModelTest {
    private QuadraticEquasionViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new QuadraticEquasionViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void whenNoDataInputCalcButtonIsDisable() {
        assertTrue(viewModel.isCalculateButtonDisabled().get());
    }

    @Test
    public void afterInputCalcButtonIsEnabled() {
        viewModel.txtCoeffAProperty().setValue("10");
        viewModel.txtCoeffBProperty().setValue("10");
        viewModel.txtCoeffCProperty().setValue("10");
        assertFalse(viewModel.isCalculateButtonDisabled().get());
    }

    @Test
    public void afterInputNotAllDataCalcButtonIsDisabled() {
        viewModel.txtCoeffAProperty().setValue("10");
        assertTrue(viewModel.isCalculateButtonDisabled().get());
    }

    @Test
    public void afterCleanButtonIsDisabled() {
        viewModel.txtCoeffAProperty().setValue("10");
        viewModel.txtCoeffBProperty().setValue("10");
        viewModel.txtCoeffCProperty().setValue("10");
        viewModel.txtCoeffAProperty().setValue("");
        assertTrue(viewModel.isCalculateButtonDisabled().get());
    }

    @Test
    public void canCalculateByClick() {
        viewModel.txtCoeffAProperty().setValue("2");
        viewModel.txtCoeffBProperty().setValue("2");
        viewModel.txtCoeffCProperty().setValue("-4");

        viewModel.calculate();
        ComplexNumber[] solution = new ComplexNumber[2];
        solution[0] = new ComplexNumber(1, 0);
        solution[1] = new ComplexNumber(-2, 0);
        assertEquals("X1 = " + solution[0] + "; X2 = " + solution[1], viewModel.lblResultProperty().get());
    }
}
