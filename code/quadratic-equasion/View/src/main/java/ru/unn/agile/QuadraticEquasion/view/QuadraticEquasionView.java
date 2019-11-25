package ru.unn.agile.QuadraticEquasion.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.unn.agile.QuadraticEquasion.viewmodel.QuadraticEquasionViewModel;

public class QuadraticEquasionView {
    @FXML
    private QuadraticEquasionViewModel viewModel;
    @FXML
    private Button btnCalc;
    @FXML
    private TextField txtCoeffA;
    @FXML
    private TextField txtCoeffB;
    @FXML
    private TextField txtCoeffC;
    @FXML
    private Label lblResult;

    @FXML
    void initialize() {
        viewModel = new QuadraticEquasionViewModel();
        btnCalc.disableProperty().bindBidirectional(viewModel.isCalculateButtonDisabled());
        txtCoeffA.textProperty().bindBidirectional(viewModel.txtCoeffAProperty());
        txtCoeffB.textProperty().bindBidirectional(viewModel.txtCoeffBProperty());
        txtCoeffC.textProperty().bindBidirectional(viewModel.txtCoeffCProperty());
        lblResult.textProperty().bindBidirectional(viewModel.lblResultProperty());
        btnCalc.setOnAction(event -> {
            viewModel.calculate();
        });
    }
}
