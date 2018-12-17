import java.util.*;
import java.io.*;

public class Day5 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day5.dat"));

		//part1(scan);
		part2(scan);
	}

	public static void part1(Scanner scan) {
		char[] arr = scan.next().toCharArray();
		LinkedList<Character> polymer = new LinkedList<Character>();
		
		for (char c : arr)
			polymer.add(c);
		
		boolean flag = true;
		while (flag) {
			flag = false;
			ListIterator<Character> iter = polymer.listIterator();
			char prev = iter.next();
			while (iter.hasNext()) {
				char next = iter.next();
				if (react(prev, next)) {
					flag = true;
					iter.remove();
					iter.previous();
					iter.remove();
					if (iter.hasNext())
						prev = iter.next();
				} else {
					prev = next;
				}
			}
		}
		
		System.out.println(polymer.size());
	}
	
	public static boolean react(char a, char b) {
		char bigA = Character.toUpperCase(a);
		char bigB = Character.toUpperCase(b);
		return bigA == bigB && Character.isUpperCase(a) != Character.isUpperCase(b);
	}

	public static void part2(Scanner scan) {
		char[] arr = scan.next().toCharArray();
		HashSet<Character> charSet = new HashSet<Character>();
		for (char c : arr)
			charSet.add(Character.toLowerCase(c));
		
		int min = Integer.MAX_VALUE;
		for (char exception : charSet) {
			LinkedList<Character> polymer = new LinkedList<Character>();
			for (char c : arr)
				if (Character.toLowerCase(c) != exception)
					polymer.add(c);
			int reactionLen = reactPolymer(polymer);
			if (reactionLen < min) 
				min = reactionLen;
		}
		
		System.out.println(min);
		
		
		
	}
	
	public static int reactPolymer(LinkedList<Character> polymer) {
		boolean flag = true;
		while (flag) {
			flag = false;
			ListIterator<Character> iter = polymer.listIterator();
			char prev = iter.next();
			while (iter.hasNext()) {
				char next = iter.next();
				if (react(prev, next)) {
					flag = true;
					iter.remove();
					iter.previous();
					iter.remove();
					if (iter.hasNext())
						prev = iter.next();
				} else {
					prev = next;
				}
			}
		}
		
		return polymer.size();
	}
}