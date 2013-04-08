package area;

import java.awt.Image;
import java.awt.image.BufferedImage;

import add_on.GraphImage;

public class BattleField implements IArea {
	private BufferedImage look;
	
	public BattleField() {
		this.look = GraphImage.getImage("fire.jpg", this);
	}

	@Override
	public Image getLook() {
		return this.look;
	}

}
