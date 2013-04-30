package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JPanel;

import add_on.GraphImage;

import control.ActionController;
import convention.Axis;

import character.Player;

import world.IMapComponent;
import world.PlayBoard;

public class BoardDisplay extends JPanel implements Observer {
	public static int width,height;
	private int ScaleX; //1 block = x coordinates
	private int ScaleY; //1 block = y coordinates
	private Player player;
	private JLabel space;
	private PlayBoard gameboard;
	private Observer controller;

	public BoardDisplay(PlayBoard board, Player player) {
		super();
		this.setSize(new Dimension(width, height));
		this.player = player;
		this.gameboard = board;
		this.space = new JLabel(player.getMove());
		this.add(space);
		this.ScaleX = BoardDisplay.width/this.gameboard.getWidth();
		this.ScaleY = BoardDisplay.height/this.gameboard.getHeigth();
	}
	
	public int getScale(Axis axis) {
		if (axis == Axis.X) {
			return this.ScaleX;
		} else {
			return this.ScaleY;
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(this.gameboard.getBackground(), 0, 0, width, height,null);
		drawComponents(g);
		g.drawImage(this.player.getLook(), player.getPos(Axis.X)*ScaleX, player.getPos(Axis.Y)*ScaleY, 
				player.getBreadth(Axis.X)*ScaleX, player.getBreadth(Axis.Y)*ScaleY, null);
//		this.space.setLocation(player.getPos(Axis.X), player.getPos(Axis.Y));
//		this.space.paint(g);
	}

	private void drawComponents(Graphics g) {
		for (int w=0;w<this.gameboard.getWidth();w++) {
			for (int h=0;h<this.gameboard.getHeigth();h++) {
				if (this.gameboard.isRoot(w, h)) {
					IMapComponent mc = this.gameboard.get(w, h);
					int pw = (int) mc.getBreadth(Axis.X);
					int ph = (int) mc.getBreadth(Axis.Y);
					g.drawImage(mc.getLook(), w*ScaleX, h*ScaleY, pw*ScaleX, ph*ScaleY, null);
				}
			}
		}
	}

	@Override
	public void update() {
		this.controller.update();
		repaint();
	}

	public void addObserver(Observer playcontrol) {
		this.controller = playcontrol;
		((ActionController) playcontrol).addDisplay(this);
	}

}
