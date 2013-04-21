package area;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import control.DisplayController;

import world.PlayBoard;

import add_on.GraphImage;

public class SavePoint implements IArea {
	private BufferedImage look;
	
	public SavePoint() {
		this.look = GraphImage.getImage("save_crystal.jpg", this);
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public Image getLook() {
		return this.look;
	}

	@Override
	public void lead(DisplayController dispcontrol) {
		dispcontrol.displaymenu(true);		
	}


}
