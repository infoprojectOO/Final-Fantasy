package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.DataBox;

import gui.MenuDisplay;
import gui.RootLayer;
import gui.SaveMenu;

public class MenuController implements ActionListener {
	private RootLayer layer;
	private DataListener filecontrol;
	private MenuDisplay menu;

	public MenuController(RootLayer layer) {
		this.layer = layer;
		this.menu = new MenuDisplay(this);
	}
	
	public void display() {
		System.out.println("menu");
		this.layer.addLayer(this.menu, this.layer.MENU);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
