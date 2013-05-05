package control;

import area.Area;
import convention.Axis;
import convention.Orientation;
import gui.BoardDisplay;
import gui.Observer;

import scenario.History;

import character.Player;

import world.Box;
import world.IMapComponent;
import world.PlayBoard;
import world.World;

public class ActionController implements Observer {
	private World world;
	private Player motioncharacter;
	private PlayBoard playground;
	private BoardDisplay boarddisplay;
	private ContactController contact;
	private DisplayController dispcontrol;
	
	public ActionController(World world, DisplayController dispcontrol) {
		this.world = world;
		this.dispcontrol = dispcontrol;
		this.playground = this.world.getBoard();
		this.motioncharacter = world.getPlayer();
		this.contact = world.getContact();
		Box.board.addObserver(this);
	}

	@Override
	public void update() {
		System.out.println("action");
		this.playground = Box.board.getBoard();
		this.dispcontrol.refresh();		
	}

	public void shift(int dx, int dy, Orientation id) {
		this.motioncharacter.setArrow(id);
		if (this.motioncharacter.requestMove()) {
			this.managePlayer();
		}
	}

	private void managePlayer() {
		/*int xr = this.motioncharacter.getPos(Axis.X)%this.boarddisplay.getScale(Axis.X);
		int yr = this.motioncharacter.getPos(Axis.Y)%this.boarddisplay.getScale(Axis.Y);*/
		int x = this.motioncharacter.getPos(Axis.X);
		int y = this.motioncharacter.getPos(Axis.Y);
/*		if (xr==0) {
			this.motioncharacter.setLeeway(Axis.X, false);
		} else {
			this.motioncharacter.setLeeway(Axis.X, true);
			System.out.println("x leeway");
		} if (yr==0) {			
			this.motioncharacter.setLeeway(Axis.Y, false);
		} else {
			this.motioncharacter.setLeeway(Axis.Y, true);
			System.out.println("y leeway");
		}*/
		if (this.playground.inside(x, y)) {
			IMapComponent object = this.playground.get(x, y);
			if (object instanceof Area) {
				((Area) object).stomped();			
			}
		}
	}

	public void addDisplay(BoardDisplay board) {
//		this.boarddisplay = board;
//		this.contact.addDisplay(board);
	}

	public void undertake(boolean accept) {
		if (accept) {
			IMapComponent target = this.contact.getTarget();
			target.awaken();
		}
	}
	
	public void menu(boolean skipsave) {
		this.dispcontrol.displaymenu(skipsave);
	}

//	public void changeBoard(PlayBoard boardmodel) {
//		this.playground = boardmodel;
//		this.contact.setBoard(boardmodel);
//	}
	
	
	

}
