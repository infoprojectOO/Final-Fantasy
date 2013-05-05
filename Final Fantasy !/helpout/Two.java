package helpout;

import java.util.ArrayList;
import java.util.List;

public class Two {
	public int pui;
	private int pri;
	public List<String> pul = new ArrayList<String>();
	private List<String> prl = new ArrayList<String>();

	public Two() {
		this.pui = 1;
		this.pri = 2;
		pul.add("hello");
		prl.add("hi");
	}

	public int getPri() {
		return pri;
	}

	public List<String> getPrl() {
		return prl;
	}

}
