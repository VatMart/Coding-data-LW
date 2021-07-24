package application;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.FlowPane;

public class RootPane1_3 extends FlowPane {
	private Neuron1_3 neuron;
	private Label lFragrantW;
	private Label lFragrantU;
	private Label lColorfulW;
	private Label lColorfulU;
	private Label lResultMessage;
	private Label lResultInt;
	private Spinner<Integer> spinnerFragW;
	private Spinner<Integer> spinnerFragU;
	private Spinner<Integer> spinnerColW;
	private Spinner<Integer> spinnerColU;
	
	public RootPane1_3(Neuron1_3 neuron) {
		this.neuron = neuron;
		neuron.rootPane = this;
		lFragrantW = new Label("Fragrant W: ");
		lFragrantU = new Label("Fragrant U: ");
		lColorfulW = new Label("Colorful W: ");
		lColorfulU = new Label("Colorful U: ");
		lResultMessage = new Label("RESULT: ");
		SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-10, 10, 0);
		SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-10, 10, 0);
		SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-10, 10, 0);
		SpinnerValueFactory<Integer> valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-10, 10, 0);
		lResultInt = new Label();
		spinnerFragW = new Spinner<>(valueFactory1);
		spinnerFragU = new Spinner<>(valueFactory2);
		spinnerColW = new Spinner<>(valueFactory3);
		spinnerColU = new Spinner<>(valueFactory4);
		getChildren().addAll(lFragrantW, spinnerFragW, lColorfulW, spinnerColW, lFragrantU, spinnerFragU, lColorfulU, spinnerColU, lResultMessage, lResultInt);
		createListeners();
	}

	private void createListeners() {
		spinnerFragW.valueProperty().addListener((obs,o,n) -> {
			neuron.setW1(n);
		});
		spinnerColW.valueProperty().addListener((obs,o,n) -> {
			neuron.setW2(n);
		});
		spinnerFragU.valueProperty().addListener((obs,o,n) -> {
			neuron.setU1(n);
		});
		spinnerColU.valueProperty().addListener((obs,o,n) -> {
			neuron.setU2(n);
		});
	}

	public void setlResultInt(String lResultInt) {
		this.lResultInt.setText(lResultInt);
	}
	

}
