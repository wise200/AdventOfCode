import java.util.*;
import java.io.*;


public class Day1 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day1.dat"));
		
		HashSet<Integer> nums = new HashSet<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		int count = 0;
		boolean flag = true;
		while (scan.hasNextInt())
			list.add(scan.nextInt());
		int i = 0;
		while (flag) {
			if (nums.contains(count)) {
				flag = false;
			} else {
				nums.add(count);
				count += list.get(i);
				i++;
				i %= list.size();
			}
		}
		
		System.out.println(count);
		System.out.println(flag);
		
	}
}
