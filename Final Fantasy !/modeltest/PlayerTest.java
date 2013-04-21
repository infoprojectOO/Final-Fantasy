package modeltest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import control.Orientation;

import character.Player;

public class PlayerTest {

	private Player player;

	@Before
	public void setUp() throws Exception {
		this.player = new Player();
	}

	@Test
	public void leewayTest() {
		assertTrue(player.isCloistered());
		player.setLeeway(Orientation.X,true);
		assertFalse(player.isCloistered());
	}

}
