package control;

import gui.GameDisplay;

import javax.swing.JFrame;

import world.*;

public class Fantasy {
	private World world;
	private GameDisplay gamedisplay;

	public Fantasy(JFrame window) {
		this.world = new World();		
		this.gamedisplay = new GameDisplay(1000,1000,this.world,window);
		this.world.addObserver(gamedisplay);		
	}

}
