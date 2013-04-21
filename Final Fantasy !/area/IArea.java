package area;

import control.DisplayController;

import world.IMapComponent;
import world.PlayBoard;

public interface IArea extends IMapComponent {
	void lead(DisplayController dispcontrol);
}
