package control;

import java.awt.Point;

import gui.BoardDisplay;
import gui.ChatPane;
import gui.Observer;
import gui.RootLayer;
import gui.SaveMenu;

import javax.swing.*;

import convention.Axis;

import area.BattleField;
import area.Area;
import area.Portal;


import scenario.History;
import settings.ActionsMap;
import settings.KeyMap;

import character.Player;
import chat.ChatBox;

import world.*;
import world.Box;

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
	private ChatPane chatpane;
	private MenuController menucontrol;
	private MotionController motioncontrol; 
	
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
		this.chatpane = new ChatPane(layer, Box.chat, new ChatListener(Box.chat));
		this.player = this.worldmodel.getPlayer();
		this.boardmodel = this.worldmodel.getBoard();
		this.motioncontrol = new MotionController(this.boardmodel);
		Box.board.upload(boardmodel);
		new SaveMenu(layer,true, Box.data);
		this.board = new BoardDisplay(Box.board,player);
		set(this.board);
		this.motioncontrol.addObserver(board);
		this.player.addObserver(board);
	}

	private void set(BoardDisplay board) {
		this.layer.replaceLayer(board, layer.MAP);
		board.setFocusable(true);
		board.requestFocusInWindow();
		board.setInputMap(JComponent.WHEN_FOCUSED, new KeyMap());
		board.setActionMap(this.actmap);	
		board.addObserver(this.playcontrol);
	}

	@Override
	public void update() {
		
		
	}

//	public void teleport(PlayBoard destination, Point landing) {
//		this.boardmodel.remove(player);
//		this.boardmodel = destination;
//		this.boardmodel.put(this.player, landing.x, landing.y);
//		this.playcontrol.changeBoard(boardmodel);
//		this.motioncontrol.setBoard(boardmodel);
//		this.board = new BoardDisplay(Box.board, this.player);
//		this.player.setPosition(landing.x/**board.getScale(Axis.X)*/,landing.y/**board.getScale(Axis.Y)*/);
//		this.player.addObserver(board);
//		set(board);		
//	}

	public void fight(BattleField battleField) {
		// do nothing		
	}

	public void displaymenu(boolean skipsave) {
		this.menucontrol.display();
	}

	public void refresh() {
//		this.layer.validate();
	}	

}
