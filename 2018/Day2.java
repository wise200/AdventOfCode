import java.util.*;
import java.io.*;

public class Day2 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day2.dat"));

		//part1(scan);
		part2(scan);
	}

	public static void part1(Scanner scan) {
		int doubles = 0, triples = 0;

		while (scan.hasNext()) {
			String str = scan.next();
			ArrayList<Integer> counts = counts(str);
			if (counts.contains(2))
				doubles++;
			if (counts.contains(3))
				triples++;
		}
		System.out.println(doubles * triples);
	}
	
	public static ArrayList<Integer> counts(String str) {
		HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
		for (char c : str.toCharArray()) {
			if (!counts.containsKey(c))
				counts.put(c, 0);
			counts.put(c, counts.get(c) + 1);
		}
		return new ArrayList<Integer>(counts.values());
	}

	public static void part2(Scanner scan) {
		ArrayList<String> list = new ArrayList<String>();
		while (scan.hasNext())
			list.add(scan.next());
		System.out.println(findMatch(list));
	}
	
	public static String findMatch(ArrayList<String> list) {
		for (int i = 0; i < list.size() - 1; i++)
			for (int j = i + 1; j < list.size(); j++)
				if (matches(list.get(i), list.get(j)))
					return list.get(i) + " " + list.get(j);
		return null;
	}
	
	public static boolean matches(String a, String b) {
		boolean flag = false;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				if (flag)
					return false;
				else
					flag = true;
			}
		}
		return true;
	}
}

