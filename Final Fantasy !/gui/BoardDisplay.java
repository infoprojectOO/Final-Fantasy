package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import control.ActionController;
import control.Orientation;

import character.Player;

import world.IMapComponent;
import world.PlayBoard;

public class BoardDisplay extends JPanel implements Observer {
	public static int width,height;
	private int ScaleX; //1 block = x coordinates
	private int ScaleY; //1 block = y coordinates
	private Player player;
	private PlayBoard gameboard;
	private Observer controller;

	public BoardDisplay(PlayBoard board, Player player) {
		super();
		this.setSize(width, height);
		this.player = player;
		this.gameboard = board;
		this.ScaleX = BoardDisplay.width/this.gameboard.getWidth();
		this.ScaleY = BoardDisplay.height/this.gameboard.getHeigth();
	}
	
	public int getScale(Orientation axis) {
		if (axis == Orientation.X) {
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

	/*public int getScaleX() {
		return ScaleX;
	}

	public int getScaleY() {
		return ScaleY;
	}*/

	public void paint(Graphics g) {
		g.drawImage(this.gameboard.getBackground(), 0, 0, width, height,null);
		drawComponents(g);
		g.drawImage(this.player.getLook(), player.getPos(Orientation.X), player.getPos(Orientation.Y), ScaleX, ScaleY, null);
		
	}

	private void drawComponents(Graphics g) {
		for (int w=0;w<this.gameboard.getWidth();w++) {
			for (int h=0;h<this.gameboard.getHeigth();h++) {
				if (!this.gameboard.isEmpty(w, h)) {
					IMapComponent mc = this.gameboard.get(w, h);
					g.drawImage(mc.getLook(), w*ScaleX, h*ScaleY, ScaleX, ScaleY, null);
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
