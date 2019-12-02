import java.util.*;
import java.io.*;

public class Day12 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day12.dat"));

		long begin = System.nanoTime();
		//part1(scan);
		part2(scan);
		long end = System.nanoTime();
		double time = (end - begin) / Math.pow(10, 9);
		System.out.println("\t\t Program finished after " + time + " seconds");
	}

	public static void part1(Scanner scan) {
		String seed = scan.nextLine();
		Map<String,Character> map = new HashMap<String,Character>();
		while (scan.hasNextLine()) {
			String[] line = scan.nextLine().split(" ");
			map.put(line[0], line[2].charAt(0));
		}
		
		PotList pots = new PotList(seed, map, 10);
		System.out.println("0:\t" + pots.toString() + "\t\t\t" + pots.sum());
		for (int i = 1; i <= 20; i++) {
			pots = pots.reproduce();
			System.out.println(i + ":\t" + pots.toString() + "\t\t\t" + pots.sum());
		}
		
		System.out.println(pots.sum());
		
	}

	public static void part2(Scanner scan) {
		String seed = scan.nextLine();
		Map<String,Character> map = new HashMap<String,Character>();
		while (scan.hasNextLine()) {
			String[] line = scan.nextLine().split(" ");
			map.put(line[0], line[2].charAt(0));
		}
		
		PotList pots = new PotList(seed, map, 10);
		System.out.println("0:\t" + pots.toString() + "\t\t\t" + pots.sum());
		int prev = pots.sum();
		for (long i = 1; i <= 500; i++) {
			pots = pots.reproduce();
			int sum = pots.sum();
			int diff = sum - prev;
			System.out.println(i + ":\t" + sum + "\t" + diff);
			prev = sum;
		}
		
		System.out.println(pots.sum());
	}
}

class PotList {
	ArrayList<Character> list;
	Map<String,Character> map;
	int prefix;
	
	public PotList(String seed, Map<String,Character> map, int prefix) {
		this.map = map;
		this.prefix = prefix;
		list = new ArrayList<Character>();
		for (int i = 0; i < prefix; i++)
			list.add('.');
		for (char c : seed.toCharArray())
			list.add(c);
	}
	
	private PotList(Map<String,Character> map, int prefix) {
		this.map = map;
		this.prefix = prefix;
		list = new ArrayList<Character>();
	}
	
	public char get(int index) {
		if (index < 0 || index >= list.size())
			return '.';
		return list.get(index);
	}
	
	public char next(int index) {
		String str = "";
		for (int i = index - 2; i <= index + 2; i++)
			str += get(i);
		if (map.containsKey(str))
			return map.get(str);
		return '.';
	}
	
	public int sum() {
		int sum = 0;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i) == '#')
				sum += i - prefix;
		return sum;
	}
	
	public String toString() {
		String str = "";
		for (char c : list)
			str += c;
		return str;
	}
	
	public PotList reproduce() {
		PotList child = new PotList(map, prefix);
		
		for (int i = 0; i < list.size(); i++) {
			child.list.add(next(i));
		}
		if (next(list.size()) == '#' || next(list.size() + 1) == '#') {
			child.list.add(next(list.size()));
			child.list.add(next(list.size()+1));
		}
		
		return child;
	}
}

