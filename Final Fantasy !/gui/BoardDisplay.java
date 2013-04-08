package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import control.ActionController;

import actions.Move;

import settings.ActionsMap;
import settings.KeyMap;

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
		this.player = player;
		this.gameboard = board;
		this.ScaleX = BoardDisplay.width/this.gameboard.getWidth();
		this.ScaleY = BoardDisplay.height/this.gameboard.getHeigth();
	}

	public int getScaleX() {
		return ScaleX;
	}

	public int getScaleY() {
		return ScaleY;
	}

	public void paint(Graphics g) {
		g.drawImage(this.gameboard.getBackground(), 0, 0, width, height,null);
		drawComponents(g);
		g.drawImage(this.player.getLook(), player.getPosX(), player.getPosY() , ScaleX, ScaleY, null);
		
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

	public Dimension getPreferredSize() {
        return new Dimension(BoardDisplay.width,BoardDisplay.height);
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
