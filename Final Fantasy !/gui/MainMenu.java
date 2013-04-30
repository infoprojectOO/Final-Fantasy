package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import add_on.GraphImage;

import control.Launcher;

public class MainMenu extends JPanel {
	private JButton[] buttons;
	private static final String[] names = {"New Game","Continue","Configure","Credits","Exit"};
	private Image background = GraphImage.getImage("ff7theme.jpg",this);
	
	public MainMenu(Launcher launcher) {
		super();
		this.setOpaque(false);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.buttons = new JButton[names.length];
		for (int i = 0; i<names.length;i++) {
			this.buttons[i] = new JButton(names[i]);
			this.buttons[i].addActionListener(launcher);
			this.buttons[i].setAlignmentX(CENTER_ALIGNMENT);
			this.add(this.buttons[i]);
		}
		this.setSize(new Dimension(this.background.getWidth(null),this.background.getHeight(null)));
		this.setPreferredSize(this.getSize());
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(this.background,0, 0, null);
		super.paint(g);
	}
	
	public Object getButton(int i) {
		return this.buttons[i];
	}
	
	public void setEnabled(boolean state) {
		Component[] comps = this.getComponents();
		for (Component c : comps) {
			c.setEnabled(state);
		}
	}
}
