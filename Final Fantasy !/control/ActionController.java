package control;

import gui.BoardDisplay;
import gui.Observer;

import scenario.History;

import character.Player;

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
		this.contact = new ContactController(world);
	}

	@Override
	public void update() {
		int xr = this.motioncharacter.getPos(Orientation.X)%this.boarddisplay.getScale(Orientation.X);
		int yr = this.motioncharacter.getPos(Orientation.Y)%this.boarddisplay.getScale(Orientation.Y);
		int x = this.motioncharacter.getPos(Orientation.X)/this.boarddisplay.getScale(Orientation.X);
		int y = this.motioncharacter.getPos(Orientation.Y)/this.boarddisplay.getScale(Orientation.Y);
		if (xr==0) {
			this.motioncharacter.setLeeway(Orientation.X, false);
			this.contact.splitSquare(Orientation.Y,x);
		} else {
			this.motioncharacter.setLeeway(Orientation.X, true);
		} if (yr==0) {			
			this.motioncharacter.setLeeway(Orientation.Y, false);
			this.contact.splitSquare(Orientation.X,y);
		} else {
			this.motioncharacter.setLeeway(Orientation.Y, true);
		}
	}

	public PlayBoard getBoard() {
		return this.playground;
	}

	public void shift(int dx, int dy, Orientation id) {
		this.motioncharacter.setArrow(id);
		if (this.contact.noContact()){
			this.motioncharacter.move(dx, dy);
			this.update();
			this.dispcontrol.update();
		}	
	}

	public void addDisplay(BoardDisplay board) {
		this.boarddisplay = board;
		this.contact.addDisplay(board);
	}

	public void undertake(boolean accept) {
		if (accept) {
			IMapComponent target = this.contact.getTarget();
			if (target.isActive()) {
				System.out.println("not yet !");
			}
		}
	}
	
	public void menu(boolean skipsave) {
		this.dispcontrol.displaymenu(skipsave);
	}

	public void changeBoard(PlayBoard boardmodel) {
		this.playground = boardmodel;
		this.contact.changeBoard(boardmodel);
	}
	
	
	

}
