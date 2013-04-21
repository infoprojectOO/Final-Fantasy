package control;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import actions.Navigate;
import sound.Sound;

import gui.ConfigureMenu;
import gui.MainMenu;
import gui.RootLayer;
import gui.SaveMenu;


public class Launcher implements ActionListener {
	private JFrame window;
	private MainMenu menue;
	private RootLayer layer;
	private ConfigureMenu config;
	
	public Launcher() {
		this.window = new JFrame("Endless Fantasy");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.menue = new MainMenu(this);
		this.layer = new RootLayer(this.window);
		Navigate.setNavigator(this.layer);
		this.config = new ConfigureMenu();
		this.layer.addLayer(this.menue, new Integer(0));
		this.window.getContentPane().add(this.layer,BorderLayout.CENTER);
		this.window.pack();
		this.window.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		switch (cmd) {
		case "New Game" : System.out.println("New Game"); launchGame(); break;
		case "Continue" : System.out.println("Continue"); loadGame(); break;
		case "Configure" : System.out.println("Configure"); configure(); break;
		case "Credits" : System.out.println("Credits"); break;
		case "Exit" : System.out.println("Exit"); this.window.dispose(); break;
		}
	}
	
	private void launchGame() {
		try {
			new Sound().playAudio();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Fantasy game = new Fantasy(this.window,this.layer);
	}

	private void loadGame() {
		this.layer.addLayer(new SaveMenu(false), layer.on_top);
		this.menue.setEnabled(false);
	}
	
	private void configure() {
		this.layer.addLayer(config,new Integer(10));
		this.menue.setEnabled(false);
	}

	public static void main(String[] args) {
		Launcher gamelauncher = new Launcher();
	}

}
