package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Attack implements ActionListener {
	private IPlayer p;
	private ICreature c;
	
	public Attack (IPlayer p,ICreature c){
		this.p = p;
		this.c = c;			
	}
	
	public void actionPerformed(ActionEvent e){
		this.c.receiveDamages(this.p.Damages());
	}
}
