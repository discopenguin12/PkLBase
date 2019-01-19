package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;


public class Main extends Application {
	/*#####Class Variables#####*/
	Button choiceButton;
	ComboBox<String> nameList;
	Label picLabel;
	Label infoLabel;
	Label HPLabel;
	Label ATKLabel;
	Label DFSLabel;
	Label SPATKLabel;
	Label SPDFSLabel;
	Label SDLabel;
	Label currentName;
	MenuBar menuB;
	Menu menu1;
	Menu menu2;
	MenuItem menuItem1;
	MenuItem menuItem2;
	MenuItem menuItem3;
	MenuItem menuItem4;
	ProgressBar HPBr;
	ProgressBar ATKBr;
	ProgressBar DFSBr;
	ProgressBar SPECATKBr;
	ProgressBar SPECDFSBr;
	ProgressBar SPDBr;
	TextArea movesList;
	TextArea TMList;
	TextArea HMList;
	String PokeName;
	
	FileInputStream incoming;
	String[] nameOption;
	DexGenerator[] dataBase;
	
	String directory;
	String pokeList;
	String textFiles;
	int counter;
	int maxNum;
	int dexIndex;
	
	
	/**
	*creatArr method
	*Creates an list of the 151 pokemon names.
	*
	*@param		name A string of the pokemon's name
	*@param 	maxNum The total number of pokemon.
	*@return	String[] returns a list of pokemon names
	*/
	public static String[] createArr(String name, int maxNum){
		String[] pkList = new String[maxNum]; //Creates list of all 151 pokemons
		pkList[0] = "--None--";
		Scanner inputStream = null;
		try{ //Attempts to read file
			inputStream = new Scanner(new File(name));
		}catch(FileNotFoundException e){
			System.exit(0);
		}
		int x = 1;
		while(inputStream.hasNextLine()){ //Writes pokemon names to a string array that is returned.
			pkList[x] = inputStream.nextLine();
			x++;
		}
		inputStream.close();
		return pkList;
	}
	
	
	/**
	*creationList method
	*Creates a list of pokemon objects that has
	*the name, number, info, moves, etc. of a pokemon.
	*
	*@param		list A list of pokemon names is passed
	*@param		directory  The path of the pokemon files is given
	*@param		number  The size of the object array.
	*@return	DexGenerator[]  Returns pokemon object list
	*/
	public static DexGenerator[] creationList(String[] list, String directory, int number){
		DexGenerator[] temp = new DexGenerator[number];
		for(int x = 1; x < number; x++){
			temp[x] = new DexGenerator(list[x], directory);
		}
		return temp;
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("PokeDex: Generation One");
		//Sets up current variables
		directory = "../PokeLBase/";
		pokeList = "../PokeLBase/PokeList.txt";
		textFiles = "../PokeLBase/PKMName";
		counter = 0;
		maxNum = 152;
		//System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
		try{
			
			incoming = new FileInputStream(directory+ "Image//Question.png");
			//directory + "\\Image\\Question.png");
		}catch(FileNotFoundException e){
			System.out.println("Error with image: " + e);
			System.exit(0);
		}
		nameOption = createArr(pokeList, maxNum);
		dataBase = creationList(nameOption, textFiles, maxNum); // Creates the array of objects to hold pokemon information.
		
		//Creates the main Pane that holds other components
		BorderPane mainPane = new BorderPane();
		
		//Creates the second Pane, the grid pane.
		GridPane secondPane = new GridPane();
		secondPane.setPadding(new Insets(14, 12, 16, 12));
		secondPane.setVgap(8);
		secondPane.setHgap(25);
		
		//Creates Grid Pane to hold stats
		GridPane statPane = new GridPane();
		for(int x = 0; x < 2; x++){
			ColumnConstraints column = new ColumnConstraints(100);
			statPane.getColumnConstraints().add(column);
		}
		statPane.setPadding(new Insets(4, 4, 4, 4));
		statPane.setVgap(8);
		statPane.setHgap(6);
		
		//Creates VBox to hold Entering Data
		VBox inPane = new VBox();
		
		//HBox to hold TM/HM
		HBox thPane = new HBox();
		
		//HBox for debug info
		HBox dBugPane = new HBox();
		
		//Creates the Menu Bar
		menuB = new MenuBar();
		//Creates the Menu for the Menu Bar
		menu1 = new Menu("File"); //First Menu drop down
		menu2 = new Menu("Help"); //Second Menu drop down.
		//Add items to menu
		menuItem2 = new MenuItem("Exit");
		//Menu item action
		menuItem2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				Platform.exit();
			}
		});
		menuItem3 = new MenuItem("Program Info");
		//Menu item action
		menuItem3.setOnAction(new EventHandler<ActionEvent>(){
			//Creates a new menu to show info
			@Override
			public void handle(ActionEvent event){
				AlertBox.display("Program Info", "This is a Java program that emulates the Pokedex"
					+" from the video game series, Pokemon. A Pokedex is a database that stores"
					+" information about every Pokemon available in the games. It contains "
					+" information such as the name, entry number, a description, and more. "
					+" This program is based off of the first generation PokeDex which includes"
					+" the first 151 Pokemon available. To store Pokemon information, the program"
					+" would create an array of objects to store the Pokemon's information." 
					+" To get information of each Pokemon, a text file containing the"
					+" information was created for each of the Pokemon's, and then would"
					+" be read and the information would be parsed out to each object. Then"
					+" I used JavaFX to create a GUI that would allow the user to see the information"
					+" that they requested for, and it would be an easy way to go about the database.");
			}
		});
		menuItem4 = new MenuItem("How to");
		//Menu item action
		menuItem4.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				AlertBox.display("How to", "Welcome to the Pokedex: First Generation program!"
					+"In order to use the Pokedex, Please use"
					+"The drop down window to select a Pokemon."
					+"Then press the enter button to"
					+"Confirm your selection."
					+"To quit the program. You can"
					+"Press the exit button, or"
					+"Go to File->Exit"
					+"Enjoy!");
			}
		});
		
		//Add items to Menu
		menu1.getItems().add(menuItem2);
		menu2.getItems().add(menuItem3);
		menu2.getItems().add(menuItem4);
		//Adds item to the menu bar
		menuB.getMenus().addAll(menu1, menu2);
		
		//Adds menu bar to the mainPane (top)
		mainPane.setTop(menuB);
		
		//Creates Labels
		Image pkPic = new Image(incoming);
		ImageView pkView = new ImageView(pkPic);
		pkView.setFitHeight(75);
		pkView.setFitWidth(75);
		picLabel = new Label();
		picLabel.setGraphic(pkView);
		GridPane.setConstraints(picLabel, 0,0);
		
		infoLabel = new Label("Name: ?\nNo: ?\nType: ?\n");
		infoLabel.setWrapText(true);
		infoLabel.setPrefWidth(250);
		GridPane.setConstraints(infoLabel, 1,0);
		
		//Creates enter button, and the action listener event
		choiceButton = new Button("Enter");
		choiceButton.setOnAction(new EventHandler<ActionEvent>(){ //If button is pressed
			@Override
			public void handle(ActionEvent e){
				PokeName = nameList.getValue();
				int findCount = 1;
				//Searches the list to find pokemon name.
				while(findCount < maxNum){
					if(PokeName.equalsIgnoreCase(dataBase[findCount].getName())==true){
						dexIndex = findCount;
					}
					findCount++;
				}
				//Changes Fields to display pokemon data
				infoLabel.setText("Name: " +dataBase[dexIndex].getName()
					+ "\nNo: " + dataBase[dexIndex].getDexKey()
					+ "\nType: " + dataBase[dexIndex].getType()
					+ "\nInfo: " + dataBase[dexIndex].getInfo());
				movesList.setText("Moves:\nLv./Name\n" +dataBase[dexIndex].getMoves());
				TMList.setText("TMs:\nNo.\n" +dataBase[dexIndex].getHM());
				HMList.setText("HMs:\nNo.\n" +dataBase[dexIndex].getTM());
				
				HPLabel.setText("HP: " + dataBase[dexIndex].getHP());
				ATKLabel.setText("Attack: " + dataBase[dexIndex].getAttack());
				DFSLabel.setText("Defense: " + dataBase[dexIndex].getDFS());
				SPATKLabel.setText("SPEC-Attack: " + dataBase[dexIndex].getSPEATK());
				SPDFSLabel.setText("SPEC-Defense: " + dataBase[dexIndex].getSPEDFS());
				SDLabel.setText("Speed: " + dataBase[dexIndex].getSPD());
				
				HPBr.setProgress((dataBase[dexIndex].getHP()/250F));
				ATKBr.setProgress((dataBase[dexIndex].getAttack()/250F));
				DFSBr.setProgress((dataBase[dexIndex].getDFS()/250F));
				SPECATKBr.setProgress((dataBase[dexIndex].getSPEATK()/250F));
				SPECDFSBr.setProgress((dataBase[dexIndex].getSPEDFS()/250F));
				SPDBr.setProgress((dataBase[dexIndex].getSPD()/250F));
				
				currentName.setText("Name: " +dataBase[dexIndex].getName()
					+"\t\tNo: " + dataBase[dexIndex].getDexKey()
					+"\t\tInfo displayed correctly.");
				//Attempt to put pokemon image 
				FileInputStream file = null;
				try{
					file = new FileInputStream(directory + "\\Image\\" + dataBase[dexIndex].getName() + ".png");
				}catch(FileNotFoundException f){
					System.out.println("Error with image: " + f);
					System.exit(0);
				}
				Image pic = new Image(file);
				ImageView viewer = new ImageView(pic);
				picLabel.setGraphic(viewer);
			}
		});
		
		//Creates dropdown
		nameList = new ComboBox<String>();
		while(counter < 152){
			nameList.getItems().add(nameOption[counter]);
			counter++;
		}
		counter = 0;
		nameList.setPromptText("Select Pokemon");
		
		//Add choice stuff
		inPane.getChildren().addAll(nameList, choiceButton);
		GridPane.setConstraints(inPane, 2,0);
		
		//Make labels:
		HPLabel = new Label("HP: ");
		GridPane.setConstraints(HPLabel, 0,0);
		ATKLabel = new Label("Attack: ");
		GridPane.setConstraints(ATKLabel, 0,1);
		DFSLabel = new Label("Defense: ");
		GridPane.setConstraints(DFSLabel, 0,2);
		SPATKLabel = new Label("SPEC-Attack: ");
		GridPane.setConstraints(SPATKLabel, 0,3);
		SPDFSLabel = new Label("SPEC-Defense: ");
		GridPane.setConstraints(SPDFSLabel, 0,4);
		SDLabel = new Label("SPeed: ");
		GridPane.setConstraints(SDLabel, 0,5);
		
		//Add Progress bars and sets a default value
		HPBr = new ProgressBar();
		HPBr.setProgress(0.25F);
		HPBr.setMaxWidth(80);
		GridPane.setConstraints(HPBr, 1,0);
		ATKBr = new ProgressBar();
		ATKBr.setProgress(0.25F);
		ATKBr.setMaxWidth(80);
		GridPane.setConstraints(ATKBr, 1,1);
		DFSBr = new ProgressBar();
		DFSBr.setProgress(0.25F);
		DFSBr.setMaxWidth(80);
		GridPane.setConstraints(DFSBr, 1,2);
		SPECATKBr = new ProgressBar();
		SPECATKBr.setProgress(0.25F);
		SPECATKBr.setMaxWidth(80);
		GridPane.setConstraints(SPECATKBr, 1,3);
		SPECDFSBr = new ProgressBar();
		SPECDFSBr.setProgress(0.25F);
		SPECDFSBr.setMaxWidth(80);
		GridPane.setConstraints(SPECDFSBr, 1,4);
		SPDBr = new ProgressBar();
		SPDBr.setProgress(0.25F);
		SPDBr.setMaxWidth(80);
		GridPane.setConstraints(SPDBr, 1,5);
		
		//Adds progress bars and labels to the statPane to add to the Secondary Pane.
		statPane.getChildren().addAll(HPLabel, ATKLabel, DFSLabel, SPATKLabel, SPDFSLabel, SDLabel);
		statPane.getChildren().addAll(HPBr, ATKBr, DFSBr, SPECATKBr, SPECDFSBr, SPDBr);
		GridPane.setConstraints(statPane, 0, 1);
		
		//Adds list view
		movesList = new TextArea("Moves:\nLv/Name\n");
		movesList.setPrefRowCount(15);
		movesList.setPrefColumnCount(6);
		movesList.setPrefWidth(150);
		movesList.setEditable(false);
		//movesList.setMouseTransparent(true);
		GridPane.setConstraints(movesList, 1,1);
		
		//Adds HBox with double List Views.
		TMList = new TextArea("TMs:\nNo.\n");
		TMList.setPrefRowCount(15);
		TMList.setPrefColumnCount(10);
		TMList.setEditable(false);
		//TMList.setMouseTransparent(true);
		HMList = new TextArea("HMs:\nNo.\n");
		HMList.	setPrefRowCount(15);
		HMList.	setPrefColumnCount(10);
		HMList.setEditable(false);
		//HMList.setMouseTransparent(true);
		thPane.getChildren().addAll(TMList, HMList);
		GridPane.setConstraints(thPane, 2, 1);
		
		//Info section to for program.
		currentName = new Label();
		dBugPane.getChildren().addAll(currentName);
		mainPane.setBottom(dBugPane);
		
		//Add to GridPane
		secondPane.getChildren().addAll(picLabel, infoLabel, inPane, statPane, movesList, thPane);
		mainPane.setCenter(secondPane);
		Scene mainScene = new Scene(mainPane, 675, 450);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
