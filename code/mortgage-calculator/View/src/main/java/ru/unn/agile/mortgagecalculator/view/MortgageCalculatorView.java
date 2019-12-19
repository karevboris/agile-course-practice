package ru.unn.agile.mortgagecalculator.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.mortgagecalculator.viewmodel.MortgageCalculatorViewModel;

public class MortgageCalculatorView {

    @FXML
    private MortgageCalculatorViewModel viewModel;
    @FXML
    private TextField apartmentPrice;
    @FXML
    private TextField firstPayment;
    @FXML
    private TextField loanPeriod;
    @FXML
    private TextField interestRate;
    @FXML
    private TextField mothlyComissions;
    @FXML
    private TextField oneTimeComissions;
    @FXML
    private TextArea result;
    @FXML
    private ChoiceBox<String> loanPeriodType;
    @FXML
    private ChoiceBox<String> mothlyComissionType;
    @FXML
    private ChoiceBox<String> oneTimeComissionType;
    @FXML
    private ChoiceBox<String> typeOfPayment;
    @FXML
    private Button calculate;

    @FXML
    void initialize() {
        apartmentPrice.textProperty().bindBidirectional(viewModel.apartmentPriceProperty());
        firstPayment.textProperty().bindBidirectional(viewModel.firstPaymentProperty());
        loanPeriod.textProperty().bindBidirectional(viewModel.loanPeriodProperty());
        interestRate.textProperty().bindBidirectional(viewModel.interestRateProperty());
        mothlyComissions.textProperty().bindBidirectional(viewModel.monthlyComissionsProperty());
        oneTimeComissions.textProperty().bindBidirectional(viewModel.oneTimeComissionsProperty());
        result.textProperty().bindBidirectional(viewModel.resultProperty());
        loanPeriodType.valueProperty().bindBidirectional(viewModel.loanPeriodTypeProperty());
        mothlyComissionType.valueProperty().bindBidirectional(
                viewModel.monthlyComissionsTypeProperty());
        oneTimeComissionType.valueProperty().bindBidirectional(
                viewModel.oneTimeComissionsTypeProperty());
        typeOfPayment.valueProperty().bindBidirectional(viewModel.typeOfPaymentProperty());
        calculate.setOnAction(event -> viewModel.calculate());
    }
}
