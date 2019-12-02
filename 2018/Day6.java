import java.util.*;
import java.io.*;

public class Day6 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day6.dat"));

		//part1(scan);
		part2(scan);
	}

	public static void part1(Scanner scan) {
		Set<Point> points = new HashSet<Point>();
		char name = 'A';
		while (scan.hasNextLine()) {
			String[] line = scan.nextLine().split("[\\s,]+");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			points.add(new Point(name, x,y));
			name++;
		}
		
		char[][] map = new char[400][400];
		for (int r = 0; r < 400; r++) {
			for (int c = 0; c < 400; c++) {
				map[r][c] = closest(r,c,points);
			}
		}
		
		HashMap<Character,Integer> areas = new HashMap<Character,Integer>();
		for (char[] row : map) {
			for (char c : row) {
				if (!areas.containsKey(c))
					areas.put(c, 0);
				areas.put(c, areas.get(c) + 1);
			}
		}
		
		for (int i = 0; i < 400; i++) {
			areas.remove(map[i][0]);
			areas.remove(map[i][399]);
			areas.remove(map[0][i]);
			areas.remove(map[399][i]);
		}
		
		int max = 0;
		for (int val : areas.values())
			if (val > max)
				max = val;
		System.out.println(max);
		
	}
	
	public static char closest(int r, int c, Set<Point> points) {
		char ans = 'A';
		int min = Integer.MAX_VALUE;
		for (Point p : points) {
			int dist = p.distance(r,c);
			if (dist < min) {
				min = dist;
				ans = p.name;
			}
		}
		int count = 0;
		for (Point p : points)
			if (p.distance(r,c) == min)
				count++;
		
		return count > 1 ? '.' : ans;
	}
	
	public static int max(Scanner scan) {
		int max = 0;
		while (scan.hasNextLine()) {
			String[] line = scan.nextLine().split("[\\s,]+");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			if (x > max)
				max = x;
			if (y > max)
				max = y;
		}
		return max;
	}

	public static void part2(Scanner scan) {
		Set<Point> points = new HashSet<Point>();
		char name = 'A';
		while (scan.hasNextLine()) {
			String[] line = scan.nextLine().split("[\\s,]+");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			points.add(new Point(name, x,y));
			name++;
		}
		
		int count = 0;
		for (int r = 0; r < 400; r++) {
			for (int c = 0; c < 400; c++) {
				if (totalDist(r,c,points) < 10000)
					count++;
			}
		}
		
		System.out.println(count);
	}
	
	public static int totalDist(int r, int c, Set<Point> points) {
		int tot = 0;
		for (Point p : points)
			tot += p.distance(r, c);
		return tot;
	}
}

class Point {
	int r, c;
	char name;
	public Point(char newName, int x, int y) {
		r = y;
		c = x;
		name = newName;
	}
	
	public int distance(int otherRow, int otherCol) {
		return Math.abs(r - otherRow) + Math.abs(c - otherCol);
	}
}
