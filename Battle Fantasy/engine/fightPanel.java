package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class fightPanel {
	JFrame fightframe;
	JPanel fightpanel;
	JPanel actionpanel;
	JPanel statepanel;
	JPanel battlepanel;
	JPanel visualpanel;
	JButton attackbutton;
	JButton magicbutton;
	JButton inventorybutton;
	JButton enemy1button;
	JButton enemy2button;
	JButton enemy3button;
	JButton player1button;
	JButton player2button;
	JButton player3button;
	
	public fightPanel(){
		initializeControls();
	}

	private void initializeControls(){
		fightframe = new JFrame ("Battle");
		fightframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fightframe.setSize(1400, 800);
		
		fightpanel = new JPanel(new BorderLayout());
		actionpanel = new JPanel ();
		statepanel = new JPanel ();
		battlepanel = new JPanel (new BorderLayout());
		visualpanel = new BattleScene();
		attackbutton = new JButton ();
		magicbutton = new JButton();
		inventorybutton = new JButton();
		enemy1button =new JButton();
		enemy2button =new JButton();
		enemy3button =new JButton();
		player1button = new JButton();
		player2button = new JButton();
		player3button = new JButton();
		enemy1button.setText("enemy1");
		enemy2button.setText("enemy2");
		enemy3button.setText("enemy3");
		player1button.setText("player1");
		player2button.setText("player2");
		player3button.setText("player3");
		attackbutton.setText("Attack");
		magicbutton.setText("Magic");
		inventorybutton.setText("Inventory");
		
		actionpanel.setBackground(Color.BLUE);
		statepanel.setBackground(Color.BLUE);
		fightframe.getContentPane().add(fightpanel);
		fightpanel.add(battlepanel, BorderLayout.PAGE_END);
		fightpanel.add(visualpanel,BorderLayout.CENTER);
		battlepanel.add(actionpanel,BorderLayout.LINE_START);
		battlepanel.add(statepanel, BorderLayout.CENTER);
		
		visualpanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets (100,0,0,100);
		visualpanel.add(enemy1button,gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		visualpanel.add(enemy2button,gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		visualpanel.add(enemy3button,gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.insets = new Insets (100,100,0,0);
		visualpanel.add(player1button,gbc);
		gbc.gridx = 3;
		gbc.gridy = 2;
		visualpanel.add(player2button,gbc);
		gbc.gridx = 3;
		gbc.gridy = 4;
		visualpanel.add(player3button,gbc);
		
		
		
		
		actionpanel.setLayout(new GridLayout(3,1));
		actionpanel.add(attackbutton);
		actionpanel.add(magicbutton);
		actionpanel.add(inventorybutton);
		
		//attackbutton.addActionListener(Attack());
		//magicbutton.addActionListener(Magic());
		//inventorybutton.addActionListener(Inventory());
		
		
		
		fightframe.setVisible(true);
	}

}
