package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import control.PlayerController;

import scenario.History;

import character.Player;

import world.*;

public class GameDisplay implements Observer {
	private int width,height;
	private World worldmodel;
	private Player player;
	private PlayBoard boardmodel;
	private BoardDisplay board;
	private JFrame gameframe;
	private PlayerController playcontrol;
	
	public GameDisplay(int width, int height, World world, JFrame gameframe) {
		super();
		this.width = width;
		this.height = height;
		this.worldmodel = world;
		this.gameframe = gameframe;
		this.playcontrol = new PlayerController(this.worldmodel);
		BoardDisplay.width = width;
		BoardDisplay.height = height;
		this.player = this.worldmodel.getPlayer();
		this.boardmodel = this.worldmodel.getBoard(History.currentPlace());
		this.board = new BoardDisplay(boardmodel,player);
		this.gameframe.getContentPane().add(this.board);
		set(this.board);
		this.boardmodel.addObserver(board);
		this.gameframe.pack();
	}

	private void set(BoardDisplay board) {
		board.setFocusable(true);
		board.requestFocusInWindow();
		//board.addKeyListener(playcontrol);		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	

	

}
