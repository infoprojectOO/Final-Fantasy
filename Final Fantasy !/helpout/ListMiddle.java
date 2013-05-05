package helpout;

import java.util.ArrayList;
import java.util.List;

public class ListMiddle {
	private static Integer start,end,breadth;
	private static List<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		start = 0;
		breadth = 9;
		System.out.println(breadth/2.0);
		int mid = (int) (start + (breadth+1)/2-1);
		System.out.println(mid);
		for (int i=0;i<=((breadth+1)/2)-1;i++) {
			list.add(mid-i);
			list.add(mid+i+1);
			System.out.println(list);
//			System.out.println(mid+i+1);
		}
		if (list.get(list.size()-1)>=start+breadth) {
			list.remove(list.size()-1);
			System.out.println("hllo");
		} System.out.println(list);
		System.out.println(list.toArray(new Integer[list.size()]));
		

	}

}
