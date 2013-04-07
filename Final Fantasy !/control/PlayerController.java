package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import scenario.History;

import character.Player;

import world.PlayBoard;
import world.World;

public class PlayerController implements KeyListener {
	private World world;
	private Player motioncharacter;
	private PlayBoard playground;
	
	public PlayerController(World world) {
		this.world = world;
		this.playground = this.world.getBoard(History.currentPlace());
		this.motioncharacter = world.getPlayer();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("pre");

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("rel");

	}

	@Override
	public void keyTyped(KeyEvent key) {
		System.out.println("down");
		if (key.getKeyChar()=='r') {
			System.out.print("hello");
			this.world.movePlayer(0,-10);
		}
	}

}
