package control;

import java.awt.Point;

import gui.BoardDisplay;
import gui.Observer;
import gui.RootLayer;
import gui.SaveMenu;

import javax.swing.*;

import area.BattleField;
import area.IArea;
import area.Portal;


import scenario.History;
import settings.ActionsMap;
import settings.KeyMap;

import character.Player;

import world.*;

public class DisplayController implements Observer {
	private int width,height;
	private World worldmodel;
	private Player player;
	private PlayBoard boardmodel;
	private BoardDisplay board;
	private JFrame gameframe;
	private ActionController playcontrol;
	private ActionsMap actmap;
	private RootLayer layer;
	private MenuController menucontrol; 
	
	public DisplayController(int width, int height, World world, JFrame gameframe, RootLayer layer) {
		super();
		this.width = width;
		this.height = height;
		this.worldmodel = world;
		this.gameframe = gameframe;
		this.layer = layer;
		this.playcontrol = new ActionController(this.worldmodel,this);
		this.menucontrol = new MenuController(this.layer);
		this.actmap = new ActionsMap(this.playcontrol);
		BoardDisplay.width = width;
		BoardDisplay.height = height;
		this.player = this.worldmodel.getPlayer();
		this.boardmodel = this.worldmodel.getBoard();
		this.board = new BoardDisplay(boardmodel,player);
		set(this.board);
		this.boardmodel.addObserver(board);
		this.player.addObserver(board);
	}

	private void set(BoardDisplay board) {
		this.layer.replaceLayer(board);
		board.setFocusable(true);
		board.requestFocusInWindow();
		board.setInputMap(JComponent.WHEN_FOCUSED, new KeyMap());
		board.setActionMap(this.actmap);	
		board.addObserver(this.playcontrol);
	}

	@Override
	public void update() {
		if (this.player.isCloistered()) {
			int x = this.player.getPos(Orientation.X)/this.board.getScale(Orientation.X);
			int y = this.player.getPos(Orientation.Y)/this.board.getScale(Orientation.Y);
			IMapComponent object = this.boardmodel.get(x, y);
			if (object instanceof IArea) {
				((IArea) object).lead(this);				
			}
		}
		
	}

	public void teleport(PlayBoard destination, Point landing) {
		this.boardmodel = destination;
		this.playcontrol.changeBoard(boardmodel);
		this.board = new BoardDisplay(boardmodel, this.player);
		this.player.setPosition(landing.x*board.getScale(Orientation.X),landing.y*board.getScale(Orientation.Y));
		this.player.addObserver(board);
		set(board);		
	}

	public void fight(BattleField battleField) {
		// do nothing		
	}

	public void displaymenu(boolean skipsave) {
		this.menucontrol.display(skipsave);
	}
	
	

	

}
