package add_on;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import gui.ChatPane;
import gui.MenuDisplay;
import gui.RootLayer;
import gui.SaveMenu;

import javax.swing.*;

import actions.Exit;
import actions.Navigate;

import chat.ChatBox;
import chat.ChatTree;

import control.ChatListener;
import control.MenuController;

public class QuickTesting {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Exit.setWindow(frame);
		RootLayer layer = new RootLayer(frame);
		Navigate.setNavigator(layer);
		MenuDisplay menu = new MenuDisplay(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {}			
		});
		frame.getContentPane().add(layer,BorderLayout.CENTER);
		layer.add(menu,layer.LOW);
		frame.pack();
		frame.setVisible(true);

	}

}
