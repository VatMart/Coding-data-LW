package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class RootPane1_7 extends FlowPane {
	
	private Neuron1_7 neuron;
	private TableView<NeuronData> tableW;
	private Label llegU;
	private Label lPlaceU;
	private Label lFlyingU;
	private Label lFeathersU;
	private Label lEggU;
	private Label lResultMessage;
	private Label lResult;
	private Spinner<Double> spinnerLeg;
	private Spinner<Double> spinnerPlace;
	private Spinner<Double> spinnerFlying;
	private Spinner<Double> spinnerFeathers;
	private Spinner<Double> spinnerEgg;
	
	public RootPane1_7(Neuron1_7 neuron) {
		this.neuron = neuron;
		createTableW();
		setPrefSize(530, 300);
		setPadding(new Insets(5));
		setHgap(4);
		setVgap(5);
		getChildren().add(tableW);
		llegU = new Label("Сколько ног: ");
		lPlaceU = new Label("Живет в воде?: ");
		lFlyingU = new Label("Умеет летать?: ");
		lFeathersU = new Label("Покрыто перьями?: ");
		lEggU = new Label("Рождается из яиц?: ");
		lResultMessage = new Label("Результат: ");
		SpinnerValueFactory<Double> valueLeg = new SpinnerValueFactory.DoubleSpinnerValueFactory(-100, 100, 0);
		SpinnerValueFactory<Double> valuePlace = new SpinnerValueFactory.DoubleSpinnerValueFactory(-100, 100, 0);
		SpinnerValueFactory<Double> valueFlying = new SpinnerValueFactory.DoubleSpinnerValueFactory(-100, 100, 0);
		SpinnerValueFactory<Double> valueFeathers = new SpinnerValueFactory.DoubleSpinnerValueFactory(-100, 100, 0);
		SpinnerValueFactory<Double> valueEgg = new SpinnerValueFactory.DoubleSpinnerValueFactory(-100, 100, 0);
		lResult = new Label();
		spinnerLeg = new Spinner<>(valueLeg);
		spinnerPlace = new Spinner<>(valuePlace);
		spinnerFlying = new Spinner<>(valueFlying);
		spinnerFeathers = new Spinner<>(valueFeathers);
		spinnerEgg = new Spinner<>(valueEgg);
		spinnerLeg.setEditable(true);
		spinnerPlace.setEditable(true);
		spinnerFlying.setEditable(true);
		spinnerFeathers.setEditable(true);
		spinnerEgg.setEditable(true);
		TextFormatter<String> formatterSpinner1 = new TextFormatter<>(c -> c.getText().matches("[^-.0123456789]") ? null : c);
		TextFormatter<String> formatterSpinner2 = new TextFormatter<>(c -> c.getText().matches("[^-.0123456789]") ? null : c);
		TextFormatter<String> formatterSpinner3 = new TextFormatter<>(c -> c.getText().matches("[^-.0123456789]") ? null : c);
		TextFormatter<String> formatterSpinner4 = new TextFormatter<>(c -> c.getText().matches("[^-.0123456789]") ? null : c);
		TextFormatter<String> formatterSpinner5 = new TextFormatter<>(c -> c.getText().matches("[^-.0123456789]") ? null : c);
		spinnerLeg.getEditor().setTextFormatter(formatterSpinner1);
		spinnerPlace.getEditor().setTextFormatter(formatterSpinner2);
		spinnerFlying.getEditor().setTextFormatter(formatterSpinner3);
		spinnerFeathers.getEditor().setTextFormatter(formatterSpinner4);
		spinnerEgg.getEditor().setTextFormatter(formatterSpinner5);
		getChildren().addAll(llegU, spinnerLeg, lPlaceU, spinnerPlace, lFlyingU, spinnerFlying, lFeathersU, spinnerFeathers, lEggU, spinnerEgg, lResultMessage, lResult);
		createListeners();
	}

	private void createListeners() {
		spinnerLeg.valueProperty().addListener((obs,o,n) -> {
			neuron.setUserInput(new NeuronData(neuron.getUserInput().getAnimalName(), n, neuron.getUserInput().getPlaceLiving(), neuron.getUserInput().getIsFlying(), neuron.getUserInput().getFeathers(), neuron.getUserInput().getFromEgg()));
		});
		spinnerPlace.valueProperty().addListener((obs,o,n) -> {
			neuron.setUserInput(new NeuronData(neuron.getUserInput().getAnimalName(), neuron.getUserInput().getQuantityLegs(), n, neuron.getUserInput().getIsFlying(), neuron.getUserInput().getFeathers(), neuron.getUserInput().getFromEgg()));
		});
		spinnerFlying.valueProperty().addListener((obs,o,n) -> {
			neuron.setUserInput(new NeuronData(neuron.getUserInput().getAnimalName(), neuron.getUserInput().getQuantityLegs(), neuron.getUserInput().getPlaceLiving(), n, neuron.getUserInput().getFeathers(), neuron.getUserInput().getFromEgg()));
		});
		spinnerFeathers.valueProperty().addListener((obs,o,n) -> {
			neuron.setUserInput(new NeuronData(neuron.getUserInput().getAnimalName(), neuron.getUserInput().getQuantityLegs(), neuron.getUserInput().getPlaceLiving(), neuron.getUserInput().getIsFlying(), n, neuron.getUserInput().getFromEgg()));
		});
		spinnerEgg.valueProperty().addListener((obs,o,n) -> {
			neuron.setUserInput(new NeuronData(neuron.getUserInput().getAnimalName(), neuron.getUserInput().getQuantityLegs(), neuron.getUserInput().getPlaceLiving(), neuron.getUserInput().getIsFlying(), neuron.getUserInput().getFeathers(), n));
		});
	}

	private void createTableW() {
		tableW = new TableView<NeuronData>();
		tableW.setEditable(true);
		TableColumn<NeuronData, String> cnameCol = new TableColumn<>("Animal");
		TableColumn<NeuronData, Double> cQuantityLegs = new TableColumn<>("Legs");
		TableColumn<NeuronData, Double> cPlaceLiving = new TableColumn<>("Place");
		TableColumn<NeuronData, Double> cIsFlying = new TableColumn<>("Is flying");
		TableColumn<NeuronData, Double> cFeathers = new TableColumn<>("Have feathers");
		TableColumn<NeuronData, Double> cFromEgg = new TableColumn<>("from Egg");
		cnameCol.setCellValueFactory(new PropertyValueFactory<>("animalName"));
		cQuantityLegs.setCellValueFactory(new PropertyValueFactory<>("quantityLegs"));
		//cQuantityLegs.setCellFactory(TextFieldTableCell.<NeuronData> forTableColumn());
		cPlaceLiving.setCellValueFactory(new PropertyValueFactory<>("placeLiving"));
		cIsFlying.setCellValueFactory(new PropertyValueFactory<>("isFlying"));
		cFeathers.setCellValueFactory(new PropertyValueFactory<>("feathers"));
		cFromEgg.setCellValueFactory(new PropertyValueFactory<>("fromEgg"));
		ObservableList<NeuronData> list = getList();
		tableW.setItems(list);
		tableW.getColumns().addAll(cnameCol, cQuantityLegs, cPlaceLiving, cIsFlying, cFeathers, cFromEgg);
		tableW.setPrefHeight(125);
		
		cQuantityLegs.setOnEditCommit((CellEditEvent<NeuronData, Double> event) -> {
	            TablePosition<NeuronData, Double> pos = event.getTablePosition();
	            Double newFullName = event.getNewValue();
	            int row = pos.getRow();
	            NeuronData person = event.getTableView().getItems().get(row);
	            person.setQuantityLegs(newFullName);
	            neuron.recalculate();
	        });
		cQuantityLegs.setCellFactory(new Callback<TableColumn<NeuronData, Double>, //
			        TableCell<NeuronData, Double>>() {
			
			            @Override
			            public TableCell<NeuronData, Double> call(TableColumn<NeuronData, Double> p) {
			                TextFieldTableCell<NeuronData, Double> cell = new TextFieldTableCell<NeuronData, Double>();
			                cell.setConverter(new StringConverter<Double>() {
								@Override
								public String toString(Double object) {
									return object.toString();
								}
								@Override
								public Double fromString(String string) {
									return Double.valueOf(string);
								}
							});
			                cell.setAlignment(Pos.CENTER);
			                return cell; 
			        }
			});
		
		cPlaceLiving.setOnEditCommit((CellEditEvent<NeuronData, Double> event) -> {
            TablePosition<NeuronData, Double> pos = event.getTablePosition();
            Double newFullName = event.getNewValue();
            int row = pos.getRow();
            NeuronData person = event.getTableView().getItems().get(row);
            person.setQuantityLegs(newFullName);
            neuron.recalculate();
        });
		cPlaceLiving.setCellFactory(new Callback<TableColumn<NeuronData, Double>, //
		        TableCell<NeuronData, Double>>() {
		
		            @Override
		            public TableCell<NeuronData, Double> call(TableColumn<NeuronData, Double> p) {
		                TextFieldTableCell<NeuronData, Double> cell = new TextFieldTableCell<NeuronData, Double>();
		                cell.setConverter(new StringConverter<Double>() {
							@Override
							public String toString(Double object) {
								return object.toString();
							}
							@Override
							public Double fromString(String string) {
								return Double.valueOf(string);
							}
						});
		                cell.setAlignment(Pos.CENTER);
		                return cell; 
		        }
		});
		
		cIsFlying.setOnEditCommit((CellEditEvent<NeuronData, Double> event) -> {
            TablePosition<NeuronData, Double> pos = event.getTablePosition();
            Double newFullName = event.getNewValue();
            int row = pos.getRow();
            NeuronData person = event.getTableView().getItems().get(row);
            person.setQuantityLegs(newFullName);
            neuron.recalculate();
        });
		cIsFlying.setCellFactory(new Callback<TableColumn<NeuronData, Double>, //
		        TableCell<NeuronData, Double>>() {
		
		            @Override
		            public TableCell<NeuronData, Double> call(TableColumn<NeuronData, Double> p) {
		                TextFieldTableCell<NeuronData, Double> cell = new TextFieldTableCell<NeuronData, Double>();
		                cell.setConverter(new StringConverter<Double>() {
							@Override
							public String toString(Double object) {
								return object.toString();
							}
							@Override
							public Double fromString(String string) {
								return Double.valueOf(string);
							}
						});
		                cell.setAlignment(Pos.CENTER);
		                return cell; 
		        }
		});
		
		cFeathers.setOnEditCommit((CellEditEvent<NeuronData, Double> event) -> {
            TablePosition<NeuronData, Double> pos = event.getTablePosition();
            Double newFullName = event.getNewValue();
            int row = pos.getRow();
            NeuronData person = event.getTableView().getItems().get(row);
            person.setQuantityLegs(newFullName);
            neuron.recalculate();
        });
		cFeathers.setCellFactory(new Callback<TableColumn<NeuronData, Double>, //
		        TableCell<NeuronData, Double>>() {
		
		            @Override
		            public TableCell<NeuronData, Double> call(TableColumn<NeuronData, Double> p) {
		                TextFieldTableCell<NeuronData, Double> cell = new TextFieldTableCell<NeuronData, Double>();
		                cell.setConverter(new StringConverter<Double>() {
							@Override
							public String toString(Double object) {
								return object.toString();
							}
							@Override
							public Double fromString(String string) {
								return Double.valueOf(string);
							}
						});
		                cell.setAlignment(Pos.CENTER);
		                return cell; 
		        }
		});
		
		cFromEgg.setOnEditCommit((CellEditEvent<NeuronData, Double> event) -> {
            TablePosition<NeuronData, Double> pos = event.getTablePosition();
            Double newFullName = event.getNewValue();
            int row = pos.getRow();
            NeuronData person = event.getTableView().getItems().get(row);
            person.setQuantityLegs(newFullName);
            neuron.recalculate();
        });
		cFromEgg.setCellFactory(new Callback<TableColumn<NeuronData, Double>, //
		        TableCell<NeuronData, Double>>() {
		
		            @Override
		            public TableCell<NeuronData, Double> call(TableColumn<NeuronData, Double> p) {
		                TextFieldTableCell<NeuronData, Double> cell = new TextFieldTableCell<NeuronData, Double>();
		                cell.setConverter(new StringConverter<Double>() {
							@Override
							public String toString(Double object) {
								return object.toString();
							}
							@Override
							public Double fromString(String string) {
								return Double.valueOf(string);
							}
						});
		                cell.setAlignment(Pos.CENTER);
		                return cell; 
		        }
		});
	}

	
	private ObservableList<NeuronData> getList() {
		NeuronData mammals = neuron.getInitMammal();
		NeuronData bird = neuron.getInitBird();
		NeuronData fish = neuron.getInitFish();
	    ObservableList<NeuronData> list = FXCollections.observableArrayList(mammals, bird, fish);
	    return list;
	}

	public void setlResult(String lResult) {
		this.lResult.setText(lResult);
	}
}
