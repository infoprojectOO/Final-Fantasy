package world;

import java.awt.Image;
import java.awt.image.BufferedImage;

import add_on.GraphImage;

public class Building implements IMapComponent {
	private BufferedImage look;
	
	public Building() {
		this.look = GraphImage.getImage("building.jpg", this);
	}

	@Override
	public Image getLook() {
		return this.look;
	}

	@Override
	public boolean isActive() {
		return false;
	}

}
