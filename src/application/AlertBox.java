package application;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox{
	
	/**
	*display method
	*
	*In the menu bar the user can select two options
	*which then the info displayed on a different
	*window
	*
	*@param		title  The title of the new window
	*@param		message  The information to display on the new window.
	*/
	public static void display(String title, String message){
		Stage window = new Stage(); //Create a new stage
		
		window.initModality(Modality.APPLICATION_MODAL);//First this window has to be
		window.setTitle(title); //Settled with before going back to previous window
		window.setMinWidth(350); 
		
		Label info = new Label(); //Creating the label and button
		info.setText(message);
		info.setWrapText(true);
		info.setPrefWidth(250);
		Button exitBut = new Button("Close");
		exitBut.setOnAction(e -> window.close()); //Close the new window
		
		VBox structure = new VBox(15); //Creates the layout for the window
		structure.getChildren().addAll(info, exitBut);
		structure.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(structure); //Every new window needs a scene
		window.setScene(scene);
		window.showAndWait(); //Window closes until it is closed
	}
}