package character;

import convention.Axis;
import convention.Orientation;
import world.IMapComponent;

public interface IMotionCharacter extends IMapComponent {
	void move(int dx,int dy);
	void setArrow(Orientation arrow);
	Orientation getArrow();
	int getPos(Axis axis);
	void requestMove();
}
