package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import actions.Move;

import settings.ActionsMap;
import settings.KeyMap;

import character.Player;

import world.PlayBoard;

public class BoardDisplay extends JPanel implements Observer {
	public static int width,height;
	private int ScaleX; //1 block = x coordinates
	private int ScaleY; //1 block = y coordinates
	private Player player;
	private PlayBoard gameboard;

	public BoardDisplay(PlayBoard board, Player player) {
		super();
		System.out.println(width);
		System.out.println(height);
		this.player = player;
		this.gameboard = board;
		this.ScaleX = BoardDisplay.width/this.gameboard.getWidth();
		this.ScaleY = BoardDisplay.height/this.gameboard.getHeigth();
		this.setInputMap(JComponent.WHEN_FOCUSED, new KeyMap());
		this.setActionMap(new ActionsMap(this.gameboard));
		
	}

	public void paint(Graphics g) {
		draw(g);
		//g.drawImage(worldmodel.getPlayer().getAppearance(), 50,50,100,100,0,0,1000,1000,null);
	}
	
	private void draw(Graphics g) {
		g.drawImage(this.player.getAppearance(), player.getPosX(), player.getPosY() , ScaleX, ScaleY, null);
		
	}

	public Dimension getPreferredSize() {
        return new Dimension(BoardDisplay.width,BoardDisplay.height);
    }

	@Override
	public void update() {
		repaint();
		
	}

}
