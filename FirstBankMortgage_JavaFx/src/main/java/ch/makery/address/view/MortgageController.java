package ch.makery.address.view;

import org.apache.poi.ss.formula.functions.FinanceLib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.UUID;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;
import javafx.scene.control.ComboBox;

public class MortgageController {

	// Reference to the main application.
	private MainApp mainApp;

	@FXML
	TextField txtIncome;
	@FXML
	TextField txtExpenses;
	@FXML
	TextField txtCreditScore;
	@FXML
	TextField txtHouseCost;
	@FXML
	ComboBox cmbTerm;
	@FXML
	Label lblMortgagePayment;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public MortgageController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {
		//Set-up the ComboBox
		ObservableList<String> comboBoxItems = FXCollections.observableArrayList("15 Year", "30 Year");
		cmbTerm.setItems(comboBoxItems);
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */

	@FXML
	private void handleCalculate() {
		lblMortgagePayment.setText("");

		boolean approvedLoan = true;
		double income = Double.parseDouble(txtIncome.getText());
		double monthlyIncome = income / 12;
		double monthlyExpenses = Double.parseDouble(txtExpenses.getText());
		double houseCost = Double.parseDouble(txtHouseCost.getText());
		int creditScore = (int) Double.parseDouble(txtCreditScore.getText());

		Rate rate = new Rate(creditScore);
		rate.setPresentValue(houseCost);

		int payments = 0;
		switch ((String) cmbTerm.getValue()) {
		case "15 Year": payments = 180;
		break;
		case "30 Year": payments = 360;
		break;
		default: payments = 180;
		}
		
		//If the credit score was too low, a rate of 0 would have been assigned
		if (rate.getInterestRate() == 0) {
			approvedLoan = false;
			lblMortgagePayment.setText("Credit score too low.");
		}
		
		double payment = 0;
		if (approvedLoan == true) {
			payment = rate.getPayment(payments); 
		}
		
		//First condition of approval
		if (payment > (monthlyIncome * .36) ) {
			approvedLoan = false;
			lblMortgagePayment.setText("House cost too high.");
		}
		//Second condition of approval
		else if (payment > ((monthlyIncome - monthlyExpenses) * .28) ) {
			approvedLoan = false;
			lblMortgagePayment.setText("House cost too high.");
		}

		//Only print the payment if approved
		if (approvedLoan == true) {
			//Truncate the payment to two decimal places
			String strpayment = Double.toString(payment);
			int icounter = 0;
			int decimalindex = 0;
			for (char c : strpayment.toCharArray()) {
				if (c == '.') {
					decimalindex = icounter;
				}
				icounter++;
			}
			strpayment = strpayment.substring(0, decimalindex + 3);

			lblMortgagePayment.setText("$" + strpayment + " / month");
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}


}