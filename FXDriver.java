package application;


import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FXDriver extends Application {
	   
	/**
	 * The main method for the GUI example program JavaFX version
	 * @param args not used
	 * @throws IOException
	 */
	public static void main(String[] args) {
		launch(args);   
	}
		   
	
	@Override
	public void start(Stage stage) throws IOException {
	    // student Task #1:
	    FXMainPane root = new FXMainPane(); // instantiate the FXMainPane
	    Scene scene = new Scene(root, 500, 250); // set the scene to hold root
	    stage.setScene(scene); // set scene to stage
	    stage.setTitle("Hello World GUI");
	    stage.show();
	}
}
