package modeltest;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import convention.Axis;
import convention.Orientation;

import character.Player;

import area.BattleField;
import area.Portal;

import world.Building;
import world.PlayBoard;

public class PlayBoardTest {
	private PlayBoard play;

	@Before
	public void setUp() throws Exception {
		play = new PlayBoard(70,90,"mithra");
	}
	
	@Test(expected = RuntimeException.class)
	public void constructorTest() {
		new PlayBoard(-4,9,null);
	}
	@Test(expected = RuntimeException.class)
	public void constructorTest2() {
		new PlayBoard(4,-9,null);
	}
	@Test
	public void getSizeTest() {
		assertEquals(play.getWidth(),70);
		assertEquals(play.getHeigth(),90);
	}
	@Test
	public void withinTest() {
		assertFalse(play.within(60,90));
		assertFalse(play.within(73,2));
		assertFalse(play.within(-1, 0));
		assertFalse(play.within(0, -2));		
	}
	@Test
	public void putTest() {
		play.put(new Portal(), 60, 80);
		assertTrue(play.isRoot(60, 80));
		assertFalse(play.isRoot(60, 81));
	}
	@Test
	public void putGetEmptyTest() {
		Building built = new Building();
		assertTrue(play.isEmpty(2,2));
		play.put(built,2,2);
		assertFalse(play.isEmpty(2,2));
		assertEquals(play.get(2, 2),built);	
		assertFalse(play.isEmpty(2,2));
		assertTrue(play.isEmpty(1, 3));
	}
	@Test(expected=RuntimeException.class)
	public void isEmpty() {
		play.isEmpty(90, 20);
	}
	@Test
	public void removeTest() {
		Building b = new Building();
		play.put(b, 3, 4);
		assertFalse(play.isEmpty(4, 5));
		assertFalse(play.isEmpty(3, 4));
		play.remove(b);
		assertTrue(play.isEmpty(3, 4));
		assertTrue(play.isEmpty(7, 8));
	}

	@Test
	public void hasWayTest() {
		Point p = new Point(0,0);
		Point pbuild = new Point(9,6);
		assertFalse(play.hasWay(Orientation.DOWN,p));
		assertFalse(play.hasWay(Orientation.LEFT,p));
		assertTrue(play.hasWay(Orientation.RIGHT,p));
		assertTrue(play.hasWay(Orientation.UP,p));
		play.put(new Building(), 10, 0);
		assertFalse(play.hasWay(Orientation.RIGHT,pbuild));
		assertTrue(play.hasWay(Orientation.UP,pbuild));
		play.put(new BattleField(), 50, 0);
		assertTrue(play.hasWay(Orientation.RIGHT,p));
		play.put(new Building(), 0, 7);
		assertFalse(play.hasWay(Orientation.UP,pbuild));
	}
}
