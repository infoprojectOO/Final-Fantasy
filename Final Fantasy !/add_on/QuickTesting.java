package add_on;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.GameMenu;
import gui.SaveMenu;

import javax.swing.JFrame;

public class QuickTesting {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new GameMenu(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {}
		})
		);
		frame.pack();
		frame.setVisible(true);

	}

}
