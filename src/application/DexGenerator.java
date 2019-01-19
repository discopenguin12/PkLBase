package application;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
import java.lang.Integer;

public class DexGenerator{
	
	private int dexKey;
	private String name;
	private String info;
	private String moves;
	private String HMs;
	private String TMs;
	private String Type;
	private int ATK;
	private	int DFS;
	private int SPEATK;
	private int SPEDFS;
	private int HP;
	private int SPD;
	
	/**
	* Default Constructor of the DexGenerator class
	* Sets the dexKey, Name, info, and moves of the object.
	*/
	public DexGenerator(){
		this.dexKey = 1;
		this.name = "Bulbasaur";
		this.info = "It can go for days without" 
					+"eating a single morsel.\n"
					+"In the bulb on its back," 
					+"it stores energy.";
		this.moves = "\nLevel 1:\t Growl \n"
					+"Level 1:\t Tackle \n"
					+"Level 7:\t Leech Seed \n"
					+"Level 13:\t Vine Whip \n"
					+"Level 20:\t Poison Powder \n"
					+"Level 27:\t Razor Leaf \n"
					+"Level 34:\t Growth \n"
					+"Level 34:\t Sleep Powder \n"
					+"Level 48:\t Solar Beam \n";
		this.HMs = "1 Cut";
		this.TMs = "3 Swords Dance\n"
					+"6 Toxic\n"
					+"8 Body Slam\n"
					+"9 Take Down\n"
					+"10 Double-Edge\n"
					+"20 Rage\n"
					+"21 Mega Drain\n"
					+"22 Solar Beam\n"
					+"31 Mimic\n"
					+"32 Double Team\n"
					+"33 Reflect\n"
					+"34 Bide\n"
					+"44 Rest\n"
					+"50 Substitute";
		this.Type = "Grass";
		this.HP = 20;
		this.ATK = 30;
		this.DFS = 40;
		this.SPEATK = 50;
		this.SPEDFS = 60;
		this.SPD = 70;
	}
	
	public DexGenerator(String pkName, String path){
		Scanner inputStream = null; //Creates the Scanner to read file
		String currentLine; //Reads each line.
		this.moves = "\n"; //Initializes these values to defaults.
		this.HMs = "\n";
		this.TMs = "\n";
		int specs = 0;

		try{ //Attempts to open the file.
			inputStream = new Scanner(new File(path+"\\" + pkName + ".txt"));
		}catch(FileNotFoundException e){
			System.exit(0);
		}
		while(inputStream.hasNextLine()){
			//Basically this codes parses up the file into the proper data for the object.
			currentLine = inputStream.nextLine(); //Checks to see file descriptor.
			if(currentLine.equalsIgnoreCase("Name:") == true){ //Checks name
				currentLine = inputStream.nextLine();
				this.name = currentLine;
			}else if(currentLine.equalsIgnoreCase("Dex:") == true){ //Checks dex
				currentLine = inputStream.nextLine();
				try{
					this.dexKey = Integer.parseInt(currentLine);
				}catch(NumberFormatException e){
					System.out.println("Number Error.");
				}
			}else if(currentLine.equalsIgnoreCase("Type:") == true){ //Checks type
				currentLine = inputStream.nextLine();
				this.Type = currentLine;
			}else if(currentLine.equalsIgnoreCase("HP:") == true){ //Checks different stats
				currentLine = inputStream.nextLine();
				try{
					specs = Integer.parseInt(currentLine);
					this.HP = specs;
				}catch(NumberFormatException e){
					System.out.println("Error with file: " + e + "\nPkName: " + getName());
					System.exit(0);
				}
			}else if(currentLine.equalsIgnoreCase("Attack:") == true){
				currentLine = inputStream.nextLine();
				try{
					specs = Integer.parseInt(currentLine);
					this.ATK = specs;
				}catch(NumberFormatException e){
					System.out.println("Error with file: " + e);
					System.exit(0);
				}
			}else if(currentLine.equalsIgnoreCase("Defense:") == true){
				currentLine = inputStream.nextLine();
				try{
					specs = Integer.parseInt(currentLine);
					this.DFS = specs;
				}catch(NumberFormatException e){
					System.out.println("Error with file: " + e);
					System.exit(0);
				}	
			}else if(currentLine.equalsIgnoreCase("SPEC-Attack:") == true){
				currentLine = inputStream.nextLine();
				try{
					specs = Integer.parseInt(currentLine);
					this.SPEATK = specs;
				}catch(NumberFormatException e){
					System.out.println("Error with file: " + e);
					System.exit(0);
				}
			}else if(currentLine.equalsIgnoreCase("SPEC-Defense:") == true){
				currentLine = inputStream.nextLine();
				try{
					specs = Integer.parseInt(currentLine);
					this.SPEDFS = specs;
				}catch(NumberFormatException e){
					System.out.println("Error with file: " + e);
					System.exit(0);
				}
			}else if(currentLine.equalsIgnoreCase("Speed:") == true){
				currentLine = inputStream.nextLine();
				try{
					specs = Integer.parseInt(currentLine);
					this.SPD = specs;
				}catch(NumberFormatException e){
					System.out.println("Error with file: " + e);
					System.exit(0);
				}
			}else if(currentLine.equalsIgnoreCase("Description:") == true){ //Checks Description
				currentLine = inputStream.nextLine();
				this.info = currentLine;
			}else if(currentLine.equalsIgnoreCase("Moves:") == true){ //Moves
				currentLine = inputStream.nextLine();
				while(currentLine.equalsIgnoreCase("#") == false){
					this.moves += currentLine + "\n";
					currentLine = inputStream.nextLine();
				}
			}else if(currentLine.equalsIgnoreCase("HMS:") == true){ //HMS
				currentLine = inputStream.nextLine();
				while(currentLine.equalsIgnoreCase("#") == false){
					this.HMs += currentLine + "\n";
					currentLine = inputStream.nextLine();
				}
			}else if(currentLine.equalsIgnoreCase("TMS:") == true){ //TMS
				currentLine = inputStream.nextLine();
				while(currentLine.equalsIgnoreCase("#") == false){
					this.TMs += currentLine + "\n";
					currentLine = inputStream.nextLine();
				}
			}
		}
		
		inputStream.close();
	}
	
	public void printData(){
		System.out.println("Name: " +this.name + "\n");
		System.out.println("No: " +this.dexKey);
		System.out.println("Type: " +this.Type + "\n");
		System.out.println("HP: " +this.HP + "\n");
		System.out.println("Attack: " +this.ATK + "\n");
		System.out.println("Defense: " +this.DFS + "\n");
		System.out.println("Special Attack: " +this.SPEATK + "\n");
		System.out.println("Special Defense: " +this.SPEDFS + "\n");
		System.out.println("Speed: " +this.SPD + "\n");
		System.out.println("Entry: " +this.info + "\n");
		System.out.println("Moveset: " +this.moves);
		System.out.println("HMs: " +this.HMs);
		System.out.println("TMs: " +this.TMs);
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getDexKey(){
		return this.dexKey;
	}
	
	public String getInfo(){
		return this.info;
	}
	
	public String getMoves(){
		return this.moves;
	}
	
	public String getHM(){
		return this.HMs;
	}
	
	public String getTM(){
		return this.TMs;
	}
	
	public String getType(){
		return this.Type;
	}
	
	public int getHP(){
		return this.HP;
	}
	
	public int getAttack(){
		return this.ATK;
	}
	
	public int getDFS(){
		return this.DFS;
	}
	
	public int getSPEATK(){
		return this.SPEATK;
	}
	
	public int getSPEDFS(){
		return this.SPEDFS;
	}
	
	public int getSPD(){
		return this.SPD;
	}
}	