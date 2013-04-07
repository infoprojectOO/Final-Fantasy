package modeltest;

import static org.junit.Assert.*;

import org.junit.Test;

import world.Building;
import world.PlayBoard;

public class PlayBoardTest {

	@Test(expected = RuntimeException.class)
	public void constructorTest() {
		new PlayBoard(-4,9);
	}
	@Test(expected = RuntimeException.class)
	public void constructorTest2() {
		new PlayBoard(4,-9);
	}
	@Test
	public void getSizeTest() {
		PlayBoard play = new PlayBoard(4,9);
		assertEquals(play.getWidth(),4);
		assertEquals(play.getHeigth(),9);
	}
	@Test
	public void withinTest() {
		PlayBoard play = new PlayBoard(4,9);
		assertFalse(play.within(4,8));
		assertFalse(play.within(3,9));
		assertFalse(play.within(-1, 0));
		assertFalse(play.within(0, -2));		
	}
	@Test
	public void putGetEmptyTest() {
		PlayBoard play = new PlayBoard(4,9);
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
		PlayBoard play = new PlayBoard(4,9);
		play.isEmpty(9, 4);
	}
	@Test
	public void removeTest() {
		PlayBoard play = new PlayBoard(4,9);
		play.put(new Building(), 3, 4);
		assertFalse(play.isEmpty(3, 4));
		play.remove(3,4);
		assertTrue(play.isEmpty(3, 4));
	}

}
