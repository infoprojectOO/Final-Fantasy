package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;

import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;

public class RootLayer extends JLayeredPane {
	public final Integer LOW = new Integer(0);
	public final Integer MAP = new Integer(10);
	public final Integer PRIOR = new Integer(50);
	public final Integer MENU = new Integer(25);
	public final Integer MESSAGE = new Integer(20);
	private Window parent;

	public RootLayer(Window parent) {
		super();
		this.parent = parent;
	}
	
	public void addLayer(Component comp, Integer depth) {
		this.add(comp,depth);
		this.sizeUp();
		this.getComponent(0).requestFocusInWindow();
//		this.setPreferredSize(comp.getPreferredSize());
		this.parent.pack();
	}

	private void sizeUp() {
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
//		setSize(this.getPreferredSize());
		this.parent.pack();
	}

	public void removeLayer(Integer depth) {
		for (Component comp : this.getComponentsInLayer(depth)) {
			this.remove(comp);
		}
		this.getComponent(0).setEnabled(true);
		this.getComponent(0).requestFocusInWindow();
		this.repaint();
		this.sizeUp();		
	}

	public void replaceLayer(Component board, Integer depth) {
		for (Component comp : this.getComponentsInLayer(depth)) {
			this.remove(comp);
		}
		this.add(board, new Integer(depth));
		for (Component comp : this.getComponentsInLayer(depth)) {
			comp.setEnabled(true);
			comp.requestFocusInWindow();
		}
		this.repaint();
		this.sizeUp();
	}
}
