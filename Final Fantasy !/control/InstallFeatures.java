package control;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class InstallFeatures {
	private static final Path[] filelocs = new Path[10];
	private static String csd="saves";
	
	public static void createPaths() {
		Path dirpath = Paths.get(csd).toAbsolutePath();
//		System.out.println(dirpath);
		for (int i=0;i<filelocs.length;i++) {
			filelocs[i] = dirpath.resolve("savedata"+i);
			try {
				OutputStream out = Files.newOutputStream(filelocs[i],StandardOpenOption.CREATE);
				out.close();
			} catch (IOException e) {System.out.println("err");}
//			System.out.println(filelocs[i]);
		}
		
	}
	public static void main(String[] args) {
		Path dirpath = Paths.get(csd).toAbsolutePath();
		try {
			Files.createDirectory(Paths.get("saves"));
		} catch (IOException e) {System.err.println("no directory created");}
		System.out.println(dirpath);
		for (int i=0;i<filelocs.length;i++) {
			filelocs[i] = dirpath.resolve("savedata"+i);
			try {
				OutputStream out = Files.newOutputStream(filelocs[i],StandardOpenOption.CREATE);		
			} catch (IOException e) {System.out.println("err");}
			System.out.println(filelocs[i]);
		}
	}

	public static Path[] getSaveDataPath() {
		createPaths();
		return filelocs;
	}

}
