package engine;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import add_on.GraphImage;

public class BattleScene extends JPanel {
	private BufferedImage background = GraphImage.getImage("battle.jpg",this);

	public BattleScene() {
		super();
	}
	
	public void paint(Graphics g) {
		g.drawImage(this.background, 0, 0, null);		
	}


}
