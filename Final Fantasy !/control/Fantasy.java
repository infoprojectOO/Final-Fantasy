package control;


import gui.ChatPane;
import gui.RootLayer;

import javax.swing.JFrame;

import world.*;

public class Fantasy {
	private World world;
	private DisplayController gamedisplay;

	public Fantasy(JFrame window, RootLayer layer) {
		this.world = new World();		
		this.gamedisplay = new DisplayController(1000,1000,this.world,window,layer);
		this.world.addObserver(gamedisplay);		
	}

}
