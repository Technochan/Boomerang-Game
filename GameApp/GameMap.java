package GameApp;

import java.util.Scanner;

public class GameMap
{
	private char[][] gameMap;
	private int mapSize;
	
	static Scanner scan = new Scanner(System.in);
	Details Dts = new Details();
	Playing play = new Playing(Dts, this);
	
	
	public char[][] getGameMap() {
		return gameMap;
	}

	public void setGameMap(char[][] gameMap) {
		this.gameMap = gameMap;
	}
	
	//preparing the initial game map
	public void prepareMap(int size) 
	{
		mapSize = size;
		gameMap = new char[mapSize][mapSize];
		char rowAlpha = 'A';
		char colAlpha = 'A';
		
		//make the matrix empty
		for(int row = 0 ; row<mapSize ; row++){
			for(int col = 0 ; col<mapSize ; col++) {
				gameMap[row][col] = ' ';
			}
		}
		
		//Prepare the wall
		for(int row = 0 ; row<mapSize ; row++){
			for(int col = 0 ; col<mapSize ; col++) {
				
				if((row==0 && row<col)) {
					gameMap[row][col] = rowAlpha++;
				}
				if((col==0 && row>col)) {
					gameMap[row][col] = colAlpha++;
				}
				if(((col > 0 && row == 1) || ( row > 0 && col == mapSize - 1 ))||
					(row > 0 && col == 1) || ( col > 0 && row == mapSize - 1 ) ||
					 (row%2 ==1 && col%2 == 1)) {
					gameMap[row][col] = '*';
				}
			}
		}
    }
	
	//adding the Player,Key,Villan,Brick position
	public void addPosition(String pos,int count,char identity,String character)
	{
		int row , col ;
		
		//Only for player and Key
		if(count == 0) {
			row = pos.charAt(0) - 'A' + 1;
			col = pos.charAt(1) - 'A' + 1;
			
			if(gameMap[row][col] == ' ') {
				gameMap[row][col] = identity; 	
				System.out.println(character +" Position Added ");
				if(identity == 'P') {
					Dts.setPlayerPosition(pos);
				}else if(identity == 'K') {
					Dts.setKeyPosition(pos);
				}
			}else {
				System.out.println("Position is already occupaid");
			}
		// for Villan and Bricks	
		}else {
			int i = 1;
			while(i<=count) {
				System.out.println("Enter the "+character+" No :"+i+" Position");
				String Pos = scan.next();
				row = Pos.charAt(0) - 'A' + 1;
				col = Pos.charAt(1) - 'A' + 1;
				gameMap[row][col] = identity;
				System.out.println(character +" Position Added ");
				
				if(identity == 'V') {
					Dts.setVillanPosition(pos);
				}else if(identity == 'B') {
					Dts.setBricksPosition(pos);
				}
				i++;
			}
		}
	}
	
	//for displaying the map
	public void display() {
		for(int row = 0 ; row<mapSize ; row++){
			for(int col = 0 ; col<mapSize ; col++) {
					System.out.print(gameMap[row][col]+" ") ;
			}
			System.out.println();
		}
	}

	
}
