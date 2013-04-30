package area;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import control.DisplayController;
import convention.Axis;

import world.PlayBoard;

import add_on.GraphImage;

public class BattleField implements IArea {
	private BufferedImage look;
	private Dimension size;
	
	public BattleField() {
		this.look = GraphImage.getImage("fire.jpg", this);
		this.size = new Dimension(10,10);
	}

	@Override
	public Image getLook() {
		return this.look;
	}

	@Override
	public void lead(DisplayController dispcontrol) {
		dispcontrol.fight(this);		
	}

	@Override
	public Dimension getSize() {
		return this.size;
	}

	@Override
	public Integer getBreadth(Axis axis) {
		if (axis==Axis.X)
		{
			return (int) this.size.getWidth();
		} else {
			return (int) this.size.getHeight();
		}
	}

	@Override
	public void awaken() {
		//beginBattle();		
	}

}
