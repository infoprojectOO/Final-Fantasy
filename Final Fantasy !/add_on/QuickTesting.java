package add_on;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import gui.ChatPane;
import gui.GameMenu;
import gui.RootLayer;
import gui.SaveMenu;

import javax.swing.*;

import character.ChatTree;

import control.ChatBox;
import control.ChatListener;

public class QuickTesting {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ChatBox chatbox = new ChatBox();
		RootLayer layer = new RootLayer(frame);
		frame.getContentPane().add(layer);
		layer.add(new JPanel(),layer.LOW);
		System.out.println(layer.highestLayer());
		ChatPane panel = new ChatPane(layer, chatbox, new ChatListener(chatbox));
		chatbox.upload(new ChatTree());
		frame.pack();
		frame.setVisible(true);

	}

}
