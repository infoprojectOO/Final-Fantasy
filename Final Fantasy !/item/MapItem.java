package item;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import character.Player;

import add_on.GraphImage;

import convention.Access;
import convention.Axis;
import convention.Measurement;

import world.IMapComponent;

public abstract class MapItem implements IMapComponent {
	private BufferedImage look;
	private Dimension size;
	private Player owner;

	public MapItem(Measurement mes,String name, Player pseudowner) {
		this.owner = pseudowner;
		this.size = mes.getSize();
		this.look = GraphImage.getImage(name, this);
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
	public void awaken() {
		this.owner.take(this);
	}

	@Override
	public Integer getBreadth(Axis axis) {
		if (axis==Axis.X) {
			return (int) this.size.getWidth();
		} else {
			return (int) this.size.getHeight();
		}
	}

	@Override
	public boolean allowsAccess(Access ackey) {
		return false;
	}
	
}
