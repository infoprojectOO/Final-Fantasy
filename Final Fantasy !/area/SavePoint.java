package area;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import control.DisplayController;
import convention.Axis;
import data.DataBox;

import world.PlayBoard;

import add_on.GraphImage;

public class SavePoint extends Area {
	private BufferedImage look;
	private Dimension size;
	private DataBox databox;
	
	public SavePoint(DataBox databox) {
		this.look = GraphImage.getImage("save_crystal.jpg", this);
		this.size = new Dimension(10,10);
		this.databox = databox;
	}

	@Override
	public Image getLook() {
		return this.look;
	}

	@Override
	public void lead(DisplayController dispcontrol) {
		dispcontrol.displaymenu(true);		
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
	}

	@Override
	public void stomped() {
		this.databox.saveRequest();
	}


}
