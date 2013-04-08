package control;

import gui.BoardDisplay;
import gui.Observer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import scenario.History;

import character.Player;

import world.PlayBoard;
import world.World;

public class ActionController implements Observer {
	private World world;
	private Player motioncharacter;
	private PlayBoard playground;
	private BoardDisplay boarddisplay;
	
	public ActionController(World world) {
		this.world = world;
		this.playground = this.world.getBoard(History.currentPlace());
		this.motioncharacter = world.getPlayer();
	}

	@Override
	public void update() {
		int xr = this.motioncharacter.getPosX()%this.boarddisplay.getScaleX();
		int yr = this.motioncharacter.getPosY()%this.boarddisplay.getScaleY();
		if (xr==0 && yr==0) {
			int x = this.motioncharacter.getPosX()/this.boarddisplay.getScaleX();
			int y = this.motioncharacter.getPosY()/this.boarddisplay.getScaleY();
			this.playground.trackPlayer(x,y);
		}
		
	}

	public PlayBoard getBoard() {
		return this.playground;
	}

	public void shift(int dx, int dy) {
		this.motioncharacter.move(dx, dy);
		this.update();
	}

	public void addDisplay(BoardDisplay board) {
		this.boarddisplay = board;	
	}
	
	
	

}
