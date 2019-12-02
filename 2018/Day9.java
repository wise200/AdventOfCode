import java.util.*;
import java.io.*;

public class Day9 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day9.dat"));
		
		long begin = System.nanoTime();
		//part1(scan);
		part2(scan);
		long end = System.nanoTime();
		double time = (end-begin) / Math.pow(10,9);
		System.out.println("\t\t Program finished after " + time + " seconds");
	}

	public static void part1(Scanner scan) {
		int players = scan.nextInt();
		int marbles = scan.nextInt();
		ArrayList<Integer> circle = new ArrayList<Integer>();
		int current = 0;
		int[] scores = new int[players];
		
		for (int i = 0; i <= marbles; i++) {
			if (i % 23 == 0 && i != 0) {
				scores[playerIndex(i,players)] += i;
				current = lowerIndex(current, circle.size());
				scores[playerIndex(i,players)] += circle.remove(current);
			} else {
				current = nextIndex(current, circle.size());
				circle.add(current, i);
			}
		}
		int max = 0;
		for (int score : scores)
			if (score > max)
				max = score;
		System.out.println(max);
		
	}
	
	public static int nextIndex(int current, int size) {
		if (size == 0)
			return 0;
		int val = (current+2) % size;
		return val == 0 ? size : val;
	}
	
	public static int lowerIndex(int current, int size) {
		int val = current - 7;
		return val < 0 ? size + val : val;
	}
	
	public static int playerIndex(int index, int players) {
		return (index-1) % players;
	}

	public static void part2(Scanner scan) {
		int players = scan.nextInt();
		int marbles = scan.nextInt() * 100;
		CircularList circle = new CircularList();
		long[] scores = new long[players];
		
		for (int i = 1; i <= marbles; i++) {
			if (i % 23 == 0) {
				scores[playerIndex(i, players)] += i + circle.remove();
			} else {
				circle.add(i);
			}
		}
		long max = 0;
		for (long score : scores)
			if (score > max)
				max = score;
		System.out.println(max);
	}
}

class CircularList {
	Node current;
	int size;
	public CircularList() {
		current = new Node(0);
		size = 1;
	}
	
	public void add(int val) {
		current = current.next;
		current.next = new Node(val, current, current.next);
		current = current.next;
		current.next.prev = current;
		size++;
	}
	
	public int remove() {
		size--;
		for (int i = 0; i < 6; i++)
			current = current.prev;
		int val = current.prev.val;
		current.prev = current.prev.prev;
		current.prev.next = current;
		return val;
	}
	
	public String toString() {
		String str = "";
		Node n = current;
		for (int i = 0; i < size; i++) {
			str += n.val + " ";
			n = n.next;
		}
		return str;
	}
}

class Node {
	int val;
	Node next, prev;
	public Node(int val) {
		this.val = val;
		next = this;
		prev = this;
	}
	public Node(int val, Node prev, Node next) {
		this.val = val;
		this.prev = prev;
		this.next = next;
	}
}