package ru.unn.agile.QuadraticEquasion.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
}
