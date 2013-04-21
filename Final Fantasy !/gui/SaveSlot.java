package gui;

import java.io.File;

public class SaveSlot {
	private String gamestate = "Empty Slot";
	private File data;
	
	public SaveSlot() {
		
	}
	
	public String toString() {
		return gamestate;
	}

	public void overwrite() {
		this.gamestate = "Empty Slot";
		
	}

	public boolean isEmpty() {
		return (gamestate=="Empty Slot");
	}
	
	

}
