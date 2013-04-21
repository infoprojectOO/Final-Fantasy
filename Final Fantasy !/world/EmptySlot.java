package world;

import java.awt.Image;

public class EmptySlot implements IMapComponent {

	@Override
	public Image getLook() {
		return null;
	}

	@Override
	public boolean isActive() {
		return false;
	}

}
