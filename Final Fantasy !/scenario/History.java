package scenario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class History {
	public static final List<String> PLACE_SERIES = writeHistory();
	private static int level = 0;
	private static String currentplace = PLACE_SERIES.get(level);
	
	public static void nextPlace() {
		level ++;
	}

	private static List<String> writeHistory() {
		List<String> places = Arrays.asList("Mithra","Towem","Tazul");
		return places;
	}
	
	public static String currentPlace() {
		return currentplace;
	}

}
