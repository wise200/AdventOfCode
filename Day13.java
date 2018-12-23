import java.util.*;
import java.io.*;

public class Day13 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);

		long begin = System.nanoTime();
		//part1(scan);
		part2(scan);
		long end = System.nanoTime();
		double time = (end - begin) / Math.pow(10, 9);
		System.out.println("\t\t Program finished after " + time + " seconds");
	}

	public static void part1(Scanner scan) {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		scores.add(3);
		scores.add(7);
		int a = 0, b = 1;
		
		while (scores.size() < 165061 + 10) {
			addNum(scores.get(a) + scores.get(b), scores);
			a = (a + scores.get(a) + 1) % scores.size();
			b = (b + scores.get(b) + 1) % scores.size();
		}
		
		String ans = "";
		for (int i = 165061; i < 165061 + 10; i++)
			ans += scores.get(i);
		System.out.println(ans);
	}
	
	public static void addNum(int num, ArrayList<Integer> list) {
		String str = Integer.toString(num);
		for (char c : str.toCharArray())
			list.add(c - '0');
	}

	public static void part2(Scanner scan) {
		ArrayList<Integer> scores = new ArrayList<Integer>(100000);
		scores.add(3);
		scores.add(7);
		int a = 0, b = 1;
		boolean flag = true;
		
		while (flag) {
			addNum(scores.get(a) + scores.get(b), scores);
			a = (a + scores.get(a) + 1) % scores.size();
			b = (b + scores.get(b) + 1) % scores.size();
			if (scores.size() > 100)
				flag = !check(scores, "165061", scores.size() - 7);
			if (scores.size() % 1000 == 0)
				System.out.println("size: " + scores.size());
		}
		
		System.out.println(scores.size() - 7);

	}
	
	public static boolean check(ArrayList<Integer> list, String str, int index) {
		for (int i = 0; i < str.length(); i++) {
			if (list.get(index+i) != str.charAt(i) - '0')
				return false;
		}
		return true;
	}
}