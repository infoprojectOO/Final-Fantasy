package misctest;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Before;
import org.junit.Test;

import world.Building;

import add_on.GraphImage;
import area.BattleField;

public class GraphImageTest {

	@Test
	public void GraphImageTest() {
		GraphImage.getImage("fire.jpg", new BattleField());
		GraphImage.getImage("mithra.jpg", new Building());
		GraphImage.getImage("building.jpg", new Building());
		GraphImage.getImage("zaneb.jpg", new Building());
		GraphImage.getImage("towem.jpg", new Building());
		GraphImage.getImage("tazul.jpg", new Building());
	}

}
