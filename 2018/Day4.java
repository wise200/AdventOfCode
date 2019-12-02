import java.util.*;
import java.io.*;

public class Day4 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day4.dat"));

		//part1(scan);
		part2(scan);
	}

	public static void part1(Scanner scan) {
		Queue<Event> pq = new PriorityQueue<Event>();
		while (scan.hasNextLine()) {
			pq.add(new Event(scan.nextLine()));
		}
		HashMap<Integer, Integer[]> guards = new HashMap<Integer, Integer[]>();
		
		while (!pq.isEmpty()) {
			int id = pq.remove().event;
			HashSet<Integer> times = new HashSet<Integer>();
			while (!pq.isEmpty() && (pq.peek().event == 1 || pq.peek().event == 0))
				times.add(pq.remove().minutes);
			Integer[] arr = guards.containsKey(id) ? guards.get(id) : array();
			boolean asleep = false;
			for (int i = 0; i < 60; i++) {
				if (times.contains(i))
					asleep = !asleep;
				if (asleep)
					arr[i]++;
			}
			guards.put(id, arr);
		}
		
		Integer[] maxGuard = guards.get(1499);
		int maxId = 1499;
		for (Map.Entry<Integer, Integer[]> entry : guards.entrySet()) {
			Integer[] guard = entry.getValue();
			if (sum(guard) > sum(maxGuard)) {
				maxGuard = guard;
				maxId = entry.getKey();
			}
		}
		
		int max = 0;
		for (int i = 0; i < 60; i++)
			if (maxGuard[i] > maxGuard[max])
				max = i;
		
		System.out.println(max * maxId);
		
	}
	
	public static int sum(Integer[] arr) {
		int sum = 0;
		for (int num : arr)
			sum += num;
		return sum;
	}
	
	public static Integer[] array() {
		Integer[] arr = new Integer[60];
		for (int i = 0; i < 60; i++)
			arr[i] = 0;
		return arr;
	}

	public static void part2(Scanner scan) {
		Queue<Event> pq = new PriorityQueue<Event>();
		while (scan.hasNextLine()) {
			pq.add(new Event(scan.nextLine()));
		}
		HashMap<Integer, Integer[]> guards = new HashMap<Integer, Integer[]>();
		
		while (!pq.isEmpty()) {
			int id = pq.remove().event;
			HashSet<Integer> times = new HashSet<Integer>();
			while (!pq.isEmpty() && (pq.peek().event == 1 || pq.peek().event == 0))
				times.add(pq.remove().minutes);
			Integer[] arr = guards.containsKey(id) ? guards.get(id) : array();
			boolean asleep = false;
			for (int i = 0; i < 60; i++) {
				if (times.contains(i))
					asleep = !asleep;
				if (asleep)
					arr[i]++;
			}
			guards.put(id, arr);
		}
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<Integer> maxs = new ArrayList<Integer>();
		ArrayList<Integer> minutes = new ArrayList<Integer>();
		
		for (Map.Entry<Integer, Integer[]> entry : guards.entrySet()) {
			ids.add(entry.getKey());
			Integer[] arr = entry.getValue();
			int max = 0;
			for (int i = 0; i < 60; i++)
				if (arr[i] > arr[max])
					max = i;
			minutes.add(max);
			maxs.add(arr[max]);
		}
		
		int maxIndex = 0;
		for (int i = 0; i < maxs.size(); i++)
			if (maxs.get(i) > maxs.get(maxIndex))
				maxIndex = i;
		
		System.out.println(ids.get(maxIndex) * minutes.get(maxIndex));
	}
}

class Event  implements Comparable<Event> {
	int month, day, hours, minutes, event;
	
	public Event(String line) {
		String[] info = line.split("[\\]\\[\\-:\\s#]+");
		System.out.println(Arrays.toString(info));
		month = Integer.parseInt(info[2]);
		day = Integer.parseInt(info[3]);
		hours = Integer.parseInt(info[4]);
		minutes = Integer.parseInt(info[5]);
		String type = info[6];
		if (type.equals("Guard"))
			event = Integer.parseInt(info[7]);
		else if (type.equals("wakes"))
			event = 1;
		else if (type.equals("falls"))
			event = 0;
		else
			throw new IllegalArgumentException("Uh oh!");
	}
	
	public int compareTo(Event other) {
		return hashCode() - other.hashCode();
	}
	
	public int hashCode() {
		return 1000000 * month + 10000 * day + 100 * hours + minutes;
	}
}

class Guard implements Comparable<Guard> {
	int id, totalSleep;
	boolean[] sleepTimes;
	
	public Guard(int newId, HashSet<Integer> times) {
		id = newId;
		totalSleep = 0;
		sleepTimes = new boolean[60];
		boolean asleep = false;
		for (int i = 0; i < 60; i++) {
			if (times.contains(i))
				asleep = !asleep;
			sleepTimes[i] = asleep;
			if (asleep)
				totalSleep++;
		}
	}
	
	public int compareTo(Guard other) {
		return other.totalSleep - totalSleep;
	}
}