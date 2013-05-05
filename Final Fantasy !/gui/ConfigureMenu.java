package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import control.Configure;

import actions.Navigate;

import settings.*;

public class ConfigureMenu extends JPanel {
	private final List<String> actionnames = Arrays.asList("Move Up","Move Right","Move Down","Move Left","Accept","Cancel","Menu");
	private static Map<DoubleTextField,Integer> keyconfig = new HashMap<DoubleTextField,Integer>();

	public ConfigureMenu() {
		super(new BorderLayout());
		JPanel entries = new JPanel(new GridLayout(0,2));
		EventListener evtl = new Configure();
		for (String s : actionnames) {
			String actkey = ActionsMap.name_key_converter.get(s);
			DoubleTextField field = new DoubleTextField(new ConfigFormat(),actkey);
			field.setValue(KeyMap.custom_keys.get(actkey));
			field.addKeyListener((KeyListener) evtl);
			entries.add(new JLabel(s));
			entries.add(field);
			ConfigureMenu.keyconfig.put(field, KeyMap.custom_keys.get(actkey));
		}
		this.add(entries,BorderLayout.NORTH);
		this.add(buttonPane(evtl),BorderLayout.SOUTH);
		this.setSize(this.getPreferredSize());
	}

	private JPanel buttonPane(EventListener evtl) {
		JPanel bpane = new JPanel();
		JButton ok = new JButton("Accept Changes");
		JButton cancel = new JButton("Cancel");
		JButton reset = new JButton("Reset to Default");
		ok.setActionCommand("accept");
		cancel.setActionCommand("cancel");
		reset.setActionCommand("reset");
		JButton[] it = {ok,cancel,reset};
		for (JButton b : it) {
			b.addActionListener((ActionListener) evtl);
			bpane.add(b);
		}
		return bpane;
	}
	
//	private HashMap<String, Integer> createBindings() throws ClassCastException {
//		HashMap<String, Integer> res = new HashMap<String,Integer>();
//		for (DoubleTextField dtf : keyconfig.keySet()) {
//			res.put(dtf.getActionId(),(Integer) dtf.getValue());
//		}
//		return res;
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		e.consume();		
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		Object source = e.getSource();
//		System.out.println(keyconfig.values());
//		if (source instanceof DoubleTextField) {
//			int keycode = e.getKeyCode();
//			if (!ConfigureMenu.keyconfig.containsValue(keycode)) {
//				((DoubleTextField) source).setValue(keycode);
//				ConfigureMenu.keyconfig.put((DoubleTextField) source, keycode);
//			} else if (((DoubleTextField) source).getValue()!=null && ((DoubleTextField) source).getValue().equals(keycode)) {
//				ConfigureMenu.keyconfig.put((DoubleTextField) source, null);
//				((DoubleTextField) source).setText("< Vacant >");
//				System.out.println("deleted");
//			} else if (((DoubleTextField) source).getValue()==null) {
//				System.out.println("null value !");
//			} 
//		}
//	}
//
//	@Override
//	public void keyTyped(KeyEvent e) {
//		e.consume();		
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent evt) {
//		String cmd = evt.getActionCommand();
//		if (cmd == "reset") {
//				reset(KeyMap.default_keys);
//		} else if (cmd == "cancel") {
//			reset(KeyMap.custom_keys);
//			new Navigate().actionPerformed(evt);
//		} else if (!ConfigureMenu.keyconfig.containsValue(null)) {
//			try { 
//				KeyMap.customize(createBindings());
//				new Navigate().actionPerformed(evt);
//			} catch (ClassCastException c) {System.err.println("Vacant Key Bindings");}
//		} else {
//			System.out.println("Vacant Key Bindings");
//		}
//	}

	public static void reset(Map<String, Integer> keymap) {
		for (DoubleTextField dtf : keyconfig.keySet()) {
			dtf.setValue(keymap.get(dtf.getActionID()));
			keyconfig.put(dtf,keymap.get(dtf.getActionID()));
		}
	}

	public Map<DoubleTextField, Integer> getKeyConfig() {
		return keyconfig;
	}

}
