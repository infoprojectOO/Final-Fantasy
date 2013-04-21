package modeltest;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import control.Orientation;

import character.Player;

import area.BattleField;

import world.Building;
import world.PlayBoard;

public class PlayBoardTest {
	private PlayBoard play;

	@Before
	public void setUp() throws Exception {
		play = new PlayBoard(4,9,"mithra");
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
		assertEquals(play.getWidth(),4);
		assertEquals(play.getHeigth(),9);
	}
	@Test
	public void withinTest() {
		assertFalse(play.within(4,8));
		assertFalse(play.within(3,9));
		assertFalse(play.within(-1, 0));
		assertFalse(play.within(0, -2));		
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
		play.isEmpty(9, 4);
	}
	@Test
	public void removeTest() {
		play.put(new Building(), 3, 4);
		assertFalse(play.isEmpty(3, 4));
		play.remove(3,4);
		assertTrue(play.isEmpty(3, 4));
	}

	@Test
	public void hasWayTest() {
		Point p = new Point(0,0);
		assertFalse(play.hasWay(Orientation.DOWN,p));
		assertFalse(play.hasWay(Orientation.LEFT,p));
		assertTrue(play.hasWay(Orientation.RIGHT,p));
		assertTrue(play.hasWay(Orientation.UP,p));
		play.put(new Building(), 1, 0);
		assertFalse(play.hasWay(Orientation.RIGHT,p));
		assertTrue(play.hasWay(Orientation.UP,p));
		play.put(new BattleField(), 1, 0);
		assertTrue(play.hasWay(Orientation.RIGHT,p));
		play.put(new Building(), 0, 1);
		assertFalse(play.hasWay(Orientation.UP,p));
	}
}
