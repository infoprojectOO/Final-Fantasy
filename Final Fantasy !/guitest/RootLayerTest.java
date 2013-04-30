package guitest;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import gui.BoardDisplay;
import gui.RootLayer;

import org.junit.Before;
import org.junit.Test;

import character.Player;

import world.PlayBoard;

public class RootLayerTest {

	private Player player;
	private RootLayer layer;
	private BoardDisplay map1;
	private BoardDisplay map2;
	private JFrame window;

	@Before
	public void setUp() throws Exception {
		this.window = new JFrame();
		this.layer = new RootLayer(window);
		this.player = new Player();
		this.map1 = new BoardDisplay(new PlayBoard(10,10,"zaneb"), player);
		this.map2 = new BoardDisplay(new PlayBoard(12,6,"mithra"), player);
	}

	@Test
	public void addTest() {
		this.layer.addLayer(map1, new Integer(0));
		assertEquals(this.layer.getComponent(0),map1);
		this.layer.addLayer(map2, new Integer(1));
		assertEquals(this.layer.getComponent(0), map2);
		assertEquals(this.layer.getComponent(1),map1);
		this.layer.remove(1);
		assertEquals(this.layer.getComponent(0),map2);
	}
	
	@Test
	public void replaceTest() {
		this.layer.addLayer(map1, new Integer(0));
		this.layer.replaceLayer(map2);
		assertEquals(this.layer.getComponent(0), map2);
	}

}
