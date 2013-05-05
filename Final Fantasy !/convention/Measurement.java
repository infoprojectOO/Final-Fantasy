package convention;

import java.awt.Dimension;

public enum Measurement {
	ITEM_S (5,5),
	ITEM_M (6,6),
	ITEM_L (8,8),
	LIVING_S (7,7),
	LIVING_M (10,10),
	LIVING_L (12,12),
	BUILDING_S (15,15),
	BUILDING_M (20,20),
	BUILDING_L (25,25),
	PLAYER (10,10);
	
	private Dimension size;

	private Measurement (int w, int h) {
		this.size = new Dimension(w,h);
	}
	
	public Dimension getSize() {
		return this.size;
	}

}
