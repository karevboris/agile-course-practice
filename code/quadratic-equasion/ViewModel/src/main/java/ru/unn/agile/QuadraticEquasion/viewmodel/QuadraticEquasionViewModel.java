package ru.unn.agile.QuadraticEquasion.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class QuadraticEquasionViewModel {
    private BooleanProperty btnCalcDisabled = new SimpleBooleanProperty();
    private StringProperty txtCoeffA = new SimpleStringProperty();
    private StringProperty txtCoeffB = new SimpleStringProperty();
    private StringProperty txtCoeffC = new SimpleStringProperty();

    public QuadraticEquasionViewModel() {
        btnCalcDisabled.setValue(true);
        txtCoeffA.setValue("");
        txtCoeffB.setValue("");
        txtCoeffC.setValue("");
        txtCoeffA.addListener((observable, oldValue, newValue) -> {
            inputValueA(newValue);
        });
        txtCoeffB.addListener((observable, oldValue, newValue) -> {
            inputValueB(newValue);
        });
        txtCoeffC.addListener((observable, oldValue, newValue) -> {
            inputValueC(newValue);
        });
    }

    private void inputValueA(final String newValue) {
        txtCoeffAProperty().setValue(newValue);
        btnCalcDisabled.setValue(!txtFieldsNotIsEmpty());
    }

    private void inputValueB(final String newValue) {
        txtCoeffBProperty().setValue(newValue);
        btnCalcDisabled.setValue(!txtFieldsNotIsEmpty());
    }

    private void inputValueC(final String newValue) {
        txtCoeffCProperty().setValue(newValue);
        btnCalcDisabled.setValue(!txtFieldsNotIsEmpty());
    }

    public BooleanProperty isCalculateButtonDisabled() {
        return btnCalcDisabled;
    }

    public StringProperty txtCoeffAProperty() {
        return txtCoeffA;
    }

    public StringProperty txtCoeffBProperty() {
        return txtCoeffB;
    }

    public StringProperty txtCoeffCProperty() {
        return txtCoeffC;
    }

    private boolean txtFieldsNotIsEmpty() {
        return !txtCoeffAProperty().get().isEmpty()
                && !txtCoeffBProperty().get().isEmpty()
                && !txtCoeffCProperty().get().isEmpty();
    }
}
