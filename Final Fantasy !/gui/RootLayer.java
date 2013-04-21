package gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;

import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;

public class RootLayer extends JLayeredPane {
	public Integer on_top;
	public final static Integer MENU = new Integer(50);
	private Window parent;

	public RootLayer(Window parent) {
		super();
		this.parent = parent;
		this.on_top = 0;
	}
	
	public void addLayer(Component comp, Integer depth) {
		this.add(comp,depth);
		if (depth>=this.on_top) {
			this.on_top = depth +1;
		}
		this.sizeUp();		
	}
	
	public void sizeUp() {
		Component[] comps = this.getComponents();
		int width = 100;
		int height = 100; 
		for (Component c : comps) {
			if(c.getWidth()>width) {
				width = c.getWidth();
			} if (c.getHeight()>height) {
				height = c.getHeight();
			}
		}
		setPreferredSize(new Dimension(width,height));
		this.parent.pack();
	}

	public void removeLayer(int n) {
		this.remove(n);
		this.sizeUp();		
	}

	public void replaceLayer(Component board) {
		this.remove(0);
		this.add(board, new Integer(0));
		this.sizeUp();
	}
}
