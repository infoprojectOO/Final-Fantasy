package character;

import convention.Access;
import convention.Axis;
import convention.Orientation;
import world.IMapComponent;

public interface IMotionCharacter extends IMapComponent {
	void setPosition(int w, int h);
	int getPos(Axis axis);
	void move(int dx,int dy);
	void setArrow(Orientation arrow);
	Orientation getArrow();
	Access getAccessKey();
	boolean requestMove();
	
}
