import java.util.*;
import java.io.*;

public class Day7 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day7.dat"));

		//part1(scan);
		part2(scan);
	}

	public static void part1(Scanner scan) {
		Map<Character, String> finishedBefore = new TreeMap<Character,String>();
		Map<Character, String> finishedAfter = new TreeMap<Character,String>();
				
		String start = "bcvz";
		
		while (scan.hasNextLine()) {
			String[] line = scan.nextLine().split(" ");
			char before = line[1].charAt(0);
			char after = line[7].charAt(0);
			
			if (!finishedBefore.containsKey(after))
				finishedBefore.put(after, "");
			finishedBefore.put(after, finishedBefore.get(after) + before);
			
			if (!finishedAfter.containsKey(before))
				finishedAfter.put(before,  "");
			finishedAfter.put(before,  finishedAfter.get(before) + after);
		}
		
		System.out.println(finishedBefore);
		System.out.println(finishedAfter);
		
		
		Queue<Character> pq = new PriorityQueue<Character>();
		pq.add('B');
		pq.add('C');
		pq.add('V');
		pq.add('Z');
		
		String ans = "";
		
		while (!pq.isEmpty()) {
			char next = pq.remove();
			ans += next;
			for (char c : get(next, finishedAfter).toCharArray())
				if (ans.indexOf(c) == -1 && containsAll(ans, finishedBefore.get(c)))
					pq.add(c);
		}
		
		System.out.println(ans);
	}
	
	public static String get(char c, Map<Character,String> map) {
		if (!map.containsKey(c))
			return "";
		return map.get(c);
	}
	
	public static boolean containsAll(String str, String chars) {
		for (char c : chars.toCharArray())
			if (str.indexOf(c) == -1)
				return false;
		return true;
	}

	public static void part2(Scanner scan) {
		Map<Character, String> finishedBefore = new TreeMap<Character,String>();
		Map<Character, String> finishedAfter = new TreeMap<Character,String>();
				
		String start = "bcvz";
		
		while (scan.hasNextLine()) {
			String[] line = scan.nextLine().split(" ");
			char before = line[1].charAt(0);
			char after = line[7].charAt(0);
			
			if (!finishedBefore.containsKey(after))
				finishedBefore.put(after, "");
			finishedBefore.put(after, finishedBefore.get(after) + before);
			
			if (!finishedAfter.containsKey(before))
				finishedAfter.put(before,  "");
			finishedAfter.put(before,  finishedAfter.get(before) + after);
		}
		
		System.out.println(finishedBefore);
		System.out.println(finishedAfter);
		
		
		Queue<Character> pq = new PriorityQueue<Character>();
		pq.add('B');
		pq.add('C');
		pq.add('V');
		pq.add('Z');
		
		Set<Worker> workers = new HashSet<Worker>();
		Set<Worker> idles = new HashSet<Worker>();
		for (int i = 0; i < 5; i++)
			idles.add(new Worker());
		
		String ans = "";
		int time = 0;
		while (!pq.isEmpty() || !workers.isEmpty()) {
			Iterator<Worker> iter = workers.iterator();
			while (iter.hasNext()) {
				Worker w = iter.next();
				if (w.finished(time)) {
					ans += w.step;
					for (char c : get(w.step, finishedAfter).toCharArray())
						if (ans.indexOf(c) == -1 && containsAll(ans, finishedBefore.get(c)))
							pq.add(c);
					idles.add(w);
					iter.remove();
				}
			}
			iter = idles.iterator();
			while (iter.hasNext() & !pq.isEmpty()) {
				Worker w = iter.next();
				w.addStep(pq.remove(), time);
				iter.remove();
				workers.add(w);
			}
			time++;
		}
		
		System.out.println(ans);
		System.out.println(time);
	}
}

class Worker {
	char step = '.';
	int finish = 0;
	public void addStep(char c, int time) {
		step = c;
		finish = time + c - 4;
	}
	
	public boolean finished(int time) {
		return time >= finish;
	}
}