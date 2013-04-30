package world;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import convention.Axis;

import add_on.GraphImage;

public class Building implements IMapComponent {
	private BufferedImage look;
	private Dimension size;
	
	public Building() {
		this.look = GraphImage.getImage("building.jpg", this);
		this.size = new Dimension(20,20);
	}

	@Override
	public Image getLook() {
		return this.look;
	}

	@Override
	public Dimension getSize() {
		return this.size;
	}

	@Override
	public Integer getBreadth(Axis axis) {
		if (axis==Axis.X) {
			return (int) size.getWidth();
		} else {
			return (int) size.getHeight();
		}
	}
	
	@Override
	public void awaken() {
		System.out.println("I'm inactive sorry !");
	}

}
