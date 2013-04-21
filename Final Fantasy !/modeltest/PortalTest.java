package modeltest;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import area.Portal;

import world.PlayBoard;

public class PortalTest {

	private Portal portal;
	private PlayBoard two;
	private PlayBoard one;

	@Before
	public void setUp() throws Exception {
		one = new PlayBoard(10,10,"mithra");
		two = new PlayBoard(5,5,"zaneb");
		this.portal = new Portal();
		portal.setIn(one, new Point(2,6));
		portal.setOut(two, new Point(3,0));
	}

/*	@Test
	public void getDestinationTest() {
		assertEquals(this.portal.getDestination(),two);
		assertEquals(this.portal.reversed().getDestination(),one);
	}
	
	@Test
	public void getPortalTest(){
		one.put(portal, 0, 0);
		assertEquals(this.portal.getDestination(),two);
		two.put(portal, 3, 2);
		assertEquals(((Portal) one.get(0, 0)).getDestination(),two);
	}*/

}
