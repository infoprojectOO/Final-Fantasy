package data;

import java.util.List;

import gui.Observer;
import gui.Enquirer;

public class DataBox implements Enquirer {
	private List<SaveSlot> fileslots;
	private Observer observer;
	
	public DataBox() {
		this.fileslots = new DataWell().getSaveList();
	}
	
	public void saveRequest() {
		this.fileslots = new DataWell().getSaveList();
		this.observer.update();
	}
	
	@Override
	public void addObserver(Observer o) {
		this.observer = o;
	}	

	public SaveSlot[] getSlots() {
		return fileslots.toArray(new SaveSlot[this.fileslots.size()]);
	}
}
