package GameApp;

import java.util.Scanner;

public class Playing{
	
	static Details Dts ;
	static GameMap Gm ;
    static  Main main = new Main();
    
    static Scanner scan = new Scanner(System.in);
    
	public Playing(Details Dts, GameMap Gm) {
		Playing.Dts = Dts;
        Playing.Gm = Gm;
	}
	
	public static void blastBomb() {
		char[][] gameMap = Gm.getGameMap();
		
		String bombPos = Dts.getBombPosition();
//		System.out.println(bombPos);
		int[] pos = getIntPosition(bombPos);
		
		int row = pos[0];
		int col = pos[1];
//		System.out.println(row+" "+col);
		
		//Check four sides
		if(gameMap[row-1][col] == 'V' || 
		   gameMap[row-1][col] == 'B' ||
      	   gameMap[row-1][col] == 'P')
		{
			
			if(gameMap[row-1][col] == 'P' ) {
			System.out.println("You dead at blast");
			main.isGameEnd(true);
			}
			gameMap[row-1][col] = ' ' ;
		}
		
		if(gameMap[row+1][col] == 'V' || 
				   gameMap[row+1][col] == 'B' ||
		      	   gameMap[row+1][col] == 'P')
		{
			
			if(gameMap[row+1][col] == 'P' ) {
				System.out.println("You dead at blast");
				main.isGameEnd(true);
				}
			gameMap[row+1][col] = ' ';
		}
		if(gameMap[row][col-1] == 'V' || 
				   gameMap[row][col-1] == 'B' ||
		      	   gameMap[row][col-1] == 'P')
		{
			
			if(gameMap[row][col-1] == 'P' ) {
				System.out.println("You dead at blast");
				main.isGameEnd(true);
				}
			gameMap[row][col-1] = ' ';
		}
		if(gameMap[row][col+1] == 'V' || 
				   gameMap[row][col+1] == 'B' ||
		      	   gameMap[row][col+1] == 'P')
		{
		
			if(gameMap[row][col+1] == 'P' ) {
				System.out.println("You dead at blast");
				main.isGameEnd(true);
				}
			gameMap[row][col+1] = ' ';
		}
		gameMap[row][col] = ' ';
		System.out.println("Bomb blast done");
	    Dts.setBombPosition(null);
		Gm.setGameMap(gameMap);
	
	}
	
	public static void setBomb(int row,int col) {
		char[][] gameMap = Gm.getGameMap();
		
		// set the bomb
		if(Dts.getBombPosition() == null) 
		{
			String alphaPos = getAlphaPosition(row,col);
			Dts.setBombPosition(alphaPos);
			Dts.bombSet = true;
			System.out.println("Bomb position added");
		} 
		// blast the bomb
		else if(Dts.getBombPosition() != null) 
		{
			System.out.println("1.Blast");
			System.out.println("2.Denote");
			int choice = scan.nextInt();
			
			//check if ok means blast
			if(choice == 1) {
				blastBomb();
			}
			
			// not ok means remove the bomb
			else {
				String currBombPos = Dts.getBombPosition();
				int[] pos = getIntPosition(currBombPos);
				int x = pos[0];
				int y = pos[1];
				gameMap[x][y] = ' ';
				Dts.setBombPosition(null);
				Dts.bombSet = false;
				Gm.setGameMap(gameMap);
				System.out.println("Bomb was denoted");
			}
			
		}
	}
	
	public static void playerMove(char dir) 
	{			
		String currPlayerPosition = Dts.getPlayerPosition();
		
		
		//Getting the current position of the player
		int[] currPos = getIntPosition(currPlayerPosition);
		int row = currPos[0];
		int col = currPos[1];
		
		if(dir == 'X') {
			setBomb(row,col);
			return;
		}
	
		//store the current position for make it empty after move
		int currRow = row;
		int currCol = col;
		
		//update the position based on the direction
		if (dir == 'W') {
		    row--;
		} else if (dir == 'S') {
		    row++;
		} else if (dir == 'A') {
		    col--;
		} else if (dir == 'D') {
		    col++;
		} else if (dir == 'E') {
		    row--;
		    col++;
		} else if (dir == 'C') {
		    row++;
		    col++;
		} else if (dir == 'Q') {
		    col--;
		    row--;
		} else if (dir == 'Z') {
		    col--;
		    row++;
		} else {
		    System.out.println("Wrong Input");
		    return;
		}
		
		char[][] gameMap = Gm.getGameMap();
		//check the position is available to make a move
		if ((gameMap[row][col] == ' ') && (row > 1 && col > 1) &&
			(row < gameMap.length - 1 && col < gameMap.length - 1))
		{
			gameMap[currRow][currCol] = ' ';
		    gameMap[row][col] = 'P';
		    Gm.setGameMap(gameMap);
		    String alphaPos = getAlphaPosition(row,col);
		    Dts.setPlayerPosition(alphaPos);
		}
		
		//if the position is already fill check that with K or V or B or *
		//if already villan is there the player is die
		else if(gameMap[row][col] == 'V')
		{
				gameMap[currRow][currCol] = ' '; 
				Gm.setGameMap(gameMap);
				System.out.println("You Get Killed By The Villan");
				main.isGameEnd(true);
		}
		
		//if there is a key on that position the player is won
		else if(gameMap[row][col] == 'K' ) 
		{
				gameMap[currRow][currCol] = ' '; 
				gameMap[row][col] = 'P';
				Gm.setGameMap(gameMap);
				System.out.println("Congratzzz you got the Key");
				System.out.println("        You won           ");
				main.isGameEnd(true);
		}
		
		//if there is no key or no villan it might be brick or wall so can't move
	    else
	    {
		    System.out.println("You can't move there");
		}
		
		
		if(Dts.getBombPosition() != Dts.getPlayerPosition() &&  Dts.bombSet) {
			String bomp = Dts.getBombPosition();
			row = bomp.charAt(0) - 'A' + 1;
			col = bomp.charAt(1) - 'A' + 1;
			gameMap[row][col] = 'X'; 
			Gm.setGameMap(gameMap);
			Dts.bombSet = false;
			
		}
		
		

	}//method end
	
	//Convert current String position into integer position
	public static int[] getIntPosition(String currPlayerPosition){
		int[] pos = new int[2];
		
		pos[0] = currPlayerPosition.charAt(0) - 'A' + 1;
		pos[1] = currPlayerPosition.charAt(1) - 'A' + 1;
		return pos;
	}
	//convert the current position into String value and add into current position
	public static String getAlphaPosition(int row,int col){
		char X = (char) ((char) row + 'A' - 1 );
	    char Y = (char) ((char) col + 'A' - 1 );
	    String alphaPos = X+""+Y;
	    
	    return alphaPos;
	}
}
