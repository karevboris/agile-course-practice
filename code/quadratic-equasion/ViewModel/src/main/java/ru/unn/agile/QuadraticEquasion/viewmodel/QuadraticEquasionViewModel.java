package ru.unn.agile.QuadraticEquasion.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;
import ru.unn.agile.QuadraticEquasion.model.QuadraticEquasion;

public class QuadraticEquasionViewModel {
    private BooleanProperty btnCalcDisabledProp = new SimpleBooleanProperty();
    private StringProperty txtCoeffAProp = new SimpleStringProperty();
    private StringProperty txtCoeffBProp = new SimpleStringProperty();
    private StringProperty txtCoeffCProp = new SimpleStringProperty();
    private StringProperty txtResultProp = new SimpleStringProperty();
    private StringProperty getTxtErrorProp = new SimpleStringProperty();

    public QuadraticEquasionViewModel() {
        btnCalcDisabledProp.setValue(true);
        txtCoeffAProp.setValue("");
        txtCoeffBProp.setValue("");
        txtCoeffCProp.setValue("");
        txtResultProp.setValue("");
        getTxtErrorProp.setValue("");

        txtCoeffAProp.addListener((observable, oldValue, newValue) -> {
            inputValue(txtCoeffAProp, newValue);
        });
        txtCoeffBProp.addListener((observable, oldValue, newValue) -> {
            inputValue(txtCoeffBProp, newValue);
        });
        txtCoeffCProp.addListener((observable, oldValue, newValue) -> {
            inputValue(txtCoeffCProp, newValue);
        });
    }

    private void inputValue(final StringProperty property, final String newValue) {
        property.setValue(newValue);
        btnCalcDisabledProp.setValue(!txtFieldsNotIsEmpty() || !canConvertCoeffsToDouble());
    }

    public BooleanProperty isCalculateButtonDisabled() {
        return btnCalcDisabledProp;
    }

    public StringProperty getTxtCoeffAProperty() {
        return txtCoeffAProp;
    }

    public StringProperty getTxtCoeffBProperty() {
        return txtCoeffBProp;
    }

    public StringProperty getTxtCoeffCProperty() {
        return txtCoeffCProp;
    }

    public StringProperty getTxtResultProperty() {
        return txtResultProp;
    }

    private boolean txtFieldsNotIsEmpty() {
        return !getTxtCoeffAProperty().get().isEmpty()
                && !getTxtCoeffBProperty().get().isEmpty()
                && !getTxtCoeffCProperty().get().isEmpty();
    }

    public void calculate() {
        QuadraticEquasion qe;
        getTxtErrorProp.setValue("");
        try {
            qe = new QuadraticEquasion(Double.parseDouble(txtCoeffAProp.get()),
                    Double.parseDouble(txtCoeffBProp.get()),
                    Double.parseDouble(txtCoeffCProp.get()));
        } catch (IllegalArgumentException e) {
            getTxtErrorProp.setValue("Incorrect Input Data");
            return;
        }
        setTxtResultProp(qe.solve());
    }

    private void setTxtResultProp(final ComplexNumber[] result) {
        StringBuilder res = new StringBuilder();
        res.append("X1 = ").append(result[0]);
        if (result.length > 1) {
            res.append("; X2 = ").append(result[1]);
        }
        txtResultProp.setValue(res.toString());
    }

    private boolean canConvertToDouble(final String value) {
        return value.matches("[+-]?([0-9]*[.])?[0-9]+");
    }

    private boolean canConvertCoeffsToDouble() {
        return canConvertToDouble(txtCoeffAProp.get())
                && canConvertToDouble(txtCoeffBProp.get())
                && canConvertToDouble(txtCoeffCProp.get());
    }

    public StringProperty getTxtErrorProperty() {
        return getTxtErrorProp;
    }
}
