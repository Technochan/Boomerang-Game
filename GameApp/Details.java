package GameApp;
import java.util.*;

public class Details {
	
	private String playerPosition ;
	private String keyPosition ;
	private String bombPosition ;
	
	boolean bombSet;
	int bombLimit;
	
	List<String> villanPosition = new ArrayList<String>();
	List<String> brickPosition = new ArrayList<String>();
	
	public void setBombLimit(int bombLimit) {
		this.bombLimit = bombLimit;
	}
	public int getBombLimit() {
		return bombLimit;
	}
	
	
	public void setPlayerPosition(String playerPosition) {
		this.playerPosition = playerPosition;
	}
	
	public String getPlayerPosition(){
		return playerPosition;
	}
	
	public void setKeyPosition(String keyPosition) {
		this.keyPosition = keyPosition;
	}
	
	public String getKeyPosition() {
		return keyPosition;
	}
	
	public void setBombPosition(String bombPosition) {
		this.bombPosition = bombPosition;
	}
	
	public String getBombPosition(){
		return bombPosition;
	}
	
	public void setVillanPosition(String villanPosition) {
		this.villanPosition.add(villanPosition);
	}
	
	public void setBricksPosition(String brickPosition) {
		this.brickPosition.add(brickPosition);
	}
	
}
