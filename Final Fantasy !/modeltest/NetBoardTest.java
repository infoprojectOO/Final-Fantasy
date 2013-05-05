package modeltest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chat.ChatBox;


import area.Portal;

import world.NetBoard;

public class NetBoardTest {

	private NetBoard netboard;

	@Before
	public void setUp() throws Exception {
		this.netboard = new NetBoard("Mithra", null);
	}

/*	@Test
	public void getPortalTest() {
		Portal port = (Portal) this.netboard.getBoard().get(6, 9);
		assertEquals(port.getDestination(),this.netboard.getBoard("zaneb"));
		assertEquals(port.reversed().getDestination(),this.netboard.getBoard("mithra"));
	}*/

}
