package world;

import java.awt.Dimension;
import java.awt.Image;

import convention.Axis;

public class EmptySlot implements IMapComponent {
	private static Dimension size = new Dimension(1,1);
	
	@Override
	public Image getLook() {
		return null;
	}

	@Override
	public Dimension getSize() {
		return size;
	}

	@Override
	public Integer getBreadth(Axis axis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void awaken() {
		System.out.println("I'm inactive sorry !");
	}

}
