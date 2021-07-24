package application;

import javafx.scene.Scene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main1_7 extends Application {
	private Neuron1_7 neuron;
	
	@Override
	public void start(Stage primaryStage) {
		neuron = new Neuron1_7();
		try {
			RootPane1_7 root = new RootPane1_7(neuron);
			neuron.rootPane = root;
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
