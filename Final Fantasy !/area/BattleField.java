package area;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import control.DisplayController;

import world.PlayBoard;

import add_on.GraphImage;

public class BattleField implements IArea {
	private BufferedImage look;
	//private List<IEnemy> enemies;
	
	public BattleField() {
		this.look = GraphImage.getImage("fire.jpg", this);
	}

	@Override
	public Image getLook() {
		return this.look;
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public void lead(DisplayController dispcontrol) {
		dispcontrol.fight(this);		
	}

}
