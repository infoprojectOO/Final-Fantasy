package world;

import java.awt.Dimension;
import java.awt.Image;

import convention.Access;
import convention.Axis;

public interface IMapComponent {	
	Image getLook();
	Dimension getSize();
	void awaken();
	Integer getBreadth(Axis axis);
	boolean allowsAccess(Access ackey);
}
