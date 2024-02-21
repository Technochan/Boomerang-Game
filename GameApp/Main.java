package GameApp;
import java.util.Scanner;

public class Main
{
	static Scanner scan = new Scanner(System.in);
	static boolean gameEnd ;
	
	
	
	public static void main(String[] args) 
	{
		GameMap Gm = new GameMap();
		
		System.out.println("Enter The Game Map Size (Even Value Max 26): ");
		int size =  scan.nextInt();
		Gm.prepareMap(size);
		
		System.out.println("Enter Player Position"); // BC
		String player = scan.next();
		Gm.addPosition(player,0,'P',"Player");
		
		System.out.println("Enter Key Position"); // BC
		String key = scan.next();
		Gm.addPosition(key,0,'K',"Key");
		
		char[][] gameMap = Gm.getGameMap();
		System.out.println("Enter Total villan count");
		int vCount =  scan.nextInt();	
		Gm.setGameMap(gameMap);
		Gm.addPosition("", vCount,'V', "Villan");
		
		System.out.println("Enter Total Brick count");
		int bCount = scan.nextInt();
		Gm.setGameMap(gameMap);
		Gm.addPosition("", bCount,'B', "Brick");
		
		Gm.display();
		
		
		gameEnd = false;

		while(!gameEnd) {
			System.out.println("Direction");
			char dir = scan.next().charAt(0);
			Playing.playerMove(dir);
			Gm.display();
		}
		System.out.println();
		System.out.println("GAME IS END");
	}
	
	//To stop the program
	public void isGameEnd(boolean value) {
		gameEnd = value;
	}
}
