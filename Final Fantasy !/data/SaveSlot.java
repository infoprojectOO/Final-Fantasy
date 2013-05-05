package data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class SaveSlot {
	private String gamestate;
	private Path datapath;
	
	public SaveSlot(Path path) {
		this.gamestate = getName(path);
		this.datapath = path;		
	}
	
	private String getName(Path path) {
		try {
			InputStream instream = Files.newInputStream(path);
			DataInputStream in = new DataInputStream(instream);
			return in.readUTF();
		} catch (IOException e) {return "Empty Slot";}
	}

	public String toString() {
		return gamestate;
	}

	public void overwrite(Integer index) {
		if (index != null) {
			this.gamestate = "Slot " + index;
		} else {this.gamestate = "Empty Slot";}
		try {
			OutputStream outstream = Files.newOutputStream(this.datapath,StandardOpenOption.CREATE);
			DataOutputStream out = new DataOutputStream(outstream);
			out.writeUTF(this.gamestate);
		} catch (IOException e) {}
	}

	public boolean isEmpty() {
		return (gamestate.equals("Empty Slot"));
	}

	public void save(Object[] tostore) {
		this.gamestate = "Saved Slot";
		try {
			OutputStream outstream = Files.newOutputStream(this.datapath,StandardOpenOption.CREATE);
			ObjectOutputStream out = new ObjectOutputStream(outstream);
			for (Object o : tostore) {
				out.writeObject(o);
			}
		} catch (IOException e) {}
	}
	
	public Object[] load() {
		List<Object> list = new ArrayList<Object>();
		try {
			InputStream instream = Files.newInputStream(this.datapath);
			ObjectInputStream out = new ObjectInputStream(instream);
			while (true) {
				try {
					list.add(out.readObject());
				} catch (ClassNotFoundException e) {
					break;
				} catch (IOException e) {
					break;
				}
			}
		} catch (IOException e1) {}
		return list.toArray();
	
	}
	
	

}
