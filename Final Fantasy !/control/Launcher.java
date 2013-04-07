package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import gui.MainMenu;


public class Launcher implements ActionListener {
	private JFrame window;
	private MainMenu menue;
	
	public Launcher() {
		this.window = new JFrame("Endless Fantasy");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.menue = new MainMenu(this);
		this.window.add(this.menue);
		this.window.pack();
		this.window.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		int j=-1;
		for (int i=0;i<=3;i++) {
			if (this.menue.getButton(i) == source) {
				j = i;
			}
		}
		switch (j) {
		case 0 : System.out.println("New Game"); launchGame(); break;
		case 1 : System.out.println("Continue"); loadGame(); break;
		case 2 : System.out.println("Configure"); break;
		case 3 : System.out.println("Exit"); this.window.dispose(); break;
		}
	}
	

	private void launchGame() {
		this.window.remove(this.menue);
		Fantasy game = new Fantasy(this.window);
	}

	private void loadGame() {
		Fantasy game = new Fantasy(this.window);		
	}

	public static void main(String[] args) {
		Launcher gamelauncher = new Launcher();
	}

}
