import java.util.*;
import java.io.*;

public class Day8 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day8.dat"));

		//part1(scan);
		part2(scan);
	}

	public static void part1(Scanner scan) {
		System.out.println(new Node1(scan).sum);
	}

	public static void part2(Scanner scan) {
		System.out.println(new Node2(scan).val);
	}
}

class Node1 {
	int sum;
	ArrayList<Node1> children;
	public Node1(Scanner scan) {
		int numChildren = scan.nextInt();
		int metaEntries = scan.nextInt();
		children = new ArrayList<Node1>();
		for (int i = 0; i < numChildren; i++)
			children.add(new Node1(scan));
		sum = 0;
		for (int i = 0; i < metaEntries; i++)
			sum += scan.nextInt();
		for (Node1 child : children) 
			sum += child.sum;
	}
}

class Node2 {
	int val;
	ArrayList<Node2> children;
	ArrayList<Integer> metadata;
	public Node2(Scanner scan) {
		int numChildren = scan.nextInt();
		int metaEntries = scan.nextInt();
		children = new ArrayList<Node2>();
		metadata = new ArrayList<Integer>();
		for (int i = 0; i < numChildren; i++)
			children.add(new Node2(scan));
		for (int i = 0; i < metaEntries; i++)
			metadata.add(scan.nextInt());
		if (children.isEmpty()) {
			val = 0;
			for (int num : metadata)
				val += num;
		} else {
			for (int index : metadata)
				val += getChildVal(index);
		}
	}
	
	public int getChildVal(int index) {
		index -= 1;
		if (index >= children.size() || index < 0)
			return 0;
		return children.get(index).val;
	}
}