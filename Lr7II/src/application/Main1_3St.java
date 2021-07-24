package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
/**
 * 
 * @author Vataev Marat
 * @date 08.01.2021
 *
 */


public class Main1_3St extends Application {
	
	Neuron1_3 neuron;
	@Override
	public void start(Stage primaryStage) {
		neuron = new Neuron1_3();
		try {
			RootPane1_3 root = new RootPane1_3(neuron);
			Scene scene = new Scene(root, 180, 200);
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
