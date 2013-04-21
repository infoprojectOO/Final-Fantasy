package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import actions.Navigate;

import control.Launcher;

import settings.*;

public class ConfigureMenu extends JPanel implements KeyListener,ActionListener {
	private final List<String> actionnames = Arrays.asList("Move Up","Move Right","Move Down","Move Left","Accept","Cancel","Menu");
	private Map<DoubleTextField,Integer> keyconfig = new HashMap<DoubleTextField,Integer>();

	public ConfigureMenu() {
		super(new BorderLayout());
		JPanel entries = new JPanel(new GridLayout(0,2));
		for (String s : actionnames) {
			String actkey = ActionsMap.name_key_converter.get(s);
			DoubleTextField field = new DoubleTextField(new ConfigFormat(),actkey);
			field.setValue(KeyMap.custom_keys.get(actkey));
			field.addKeyListener(this);
			entries.add(new JLabel(s));
			entries.add(field);
			this.keyconfig.put(field, KeyMap.custom_keys.get(actkey));
		}
		this.add(entries,BorderLayout.NORTH);
		this.add(buttonPane(),BorderLayout.SOUTH);
		this.setSize(this.getPreferredSize());
	}

	private JPanel buttonPane() {
		JPanel bpane = new JPanel();
		JButton ok = new JButton("Accept Changes");
		JButton cancel = new JButton("Cancel");
		JButton reset = new JButton("Reset to Default");
		ok.setActionCommand("accept");
		cancel.setActionCommand("cancel");
		reset.setActionCommand("reset");
		JButton[] it = {ok,cancel,reset};
		for (JButton b : it) {
			b.addActionListener(this);
			bpane.add(b);
		}
		return bpane;
	}
	
	private HashMap<String, Integer> createBindings() throws ClassCastException {
		HashMap<String, Integer> res = new HashMap<String,Integer>();
		for (DoubleTextField dtf : keyconfig.keySet()) {
			res.put(dtf.getActionId(),(Integer) dtf.getValue());
		}
		return res;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		e.consume();		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Object source = e.getSource();
		System.out.println(keyconfig.values());
		if (source instanceof DoubleTextField) {
			int keycode = e.getKeyCode();
			if (!this.keyconfig.containsValue(keycode)) {
				((DoubleTextField) source).setValue(keycode);
				this.keyconfig.put((DoubleTextField) source, keycode);
			} else if (((DoubleTextField) source).getValue()!=null && ((DoubleTextField) source).getValue().equals(keycode)) {
				this.keyconfig.put((DoubleTextField) source, null);
				((DoubleTextField) source).setText("< Vacant >");
				System.out.println("deleted");
			} else if (((DoubleTextField) source).getValue()==null) {
				System.out.println("null value !");
			} 
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		if (cmd == "reset") {
				reset(KeyMap.default_keys);
		} else if (cmd == "cancel") {
			reset(KeyMap.custom_keys);
			new Navigate().actionPerformed(evt);
		} else if (!this.keyconfig.containsValue(null)) {
			try { 
				KeyMap.customize(createBindings());
				new Navigate().actionPerformed(evt);
			} catch (ClassCastException c) {System.err.println("Vacant Key Bindings");}
		} else {
			System.out.println("Vacant Key Bindings");
		}
	}

	private void reset(Map<String, Integer> keymap) {
		for (DoubleTextField dtf : this.keyconfig.keySet()) {
			dtf.setValue(keymap.get(dtf.getActionId()));
			this.keyconfig.put(dtf,keymap.get(dtf.getActionId()));
		}
	}

}
