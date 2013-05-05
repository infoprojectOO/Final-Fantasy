package area;

import control.DisplayController;
import convention.Access;

import world.IMapComponent;
import world.PlayBoard;

public abstract class Area implements IMapComponent {
	public abstract void lead(DisplayController dispcontrol);
	
	public abstract void stomped();
	
	public boolean allowsAccess(Access ackey) {
		return (ackey == Access.AREAKEY);
	}
}
