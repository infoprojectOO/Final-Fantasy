package area;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import battle.BattleBox;

import control.DisplayController;
import convention.Axis;
import convention.Measurement;

import world.PlayBoard;

import add_on.GraphImage;

public class BattleField extends Area {
	private BufferedImage look;
	private Dimension size;
	private BattleBox battlebox;
	
	public BattleField(BattleBox battlebox) {
		this.look = GraphImage.getImage("fire.jpg", this);
		this.size = Measurement.PLAYER.getSize();
		this.battlebox = battlebox;
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

	@Override
	public void stomped() {
		//this.battlebox.upload(this.battle);
	}

}
