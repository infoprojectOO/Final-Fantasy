package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.GameMenu;
import gui.RootLayer;
import gui.SaveMenu;

public class MenuController implements ActionListener {
	private RootLayer layer;
	private FileController filecontrol;
	private GameMenu menu;

	public MenuController(RootLayer layer) {
		this.layer = layer;
		this.menu = new GameMenu(this);
	}
	
	public void display(boolean skipsave) {
		if (skipsave) {
			System.out.println("save");
			this.layer.addLayer(new SaveMenu(true), layer.on_top);
		} else {
			System.out.println("menu");
			this.layer.addLayer(this.menu, RootLayer.MENU);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
