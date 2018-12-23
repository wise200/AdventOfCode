import java.util.*;
import java.io.*;

public class Day10 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day10.dat"));

		long begin = System.nanoTime();
		part1(scan);
		//part2(scan);
		long end = System.nanoTime();
		double time = (end - begin) / Math.pow(10, 9);
		System.out.println("\t\t Program finished after " + time + " seconds");
	}

	public static void part1(Scanner scan) {
		ArrayList<Point2> list = new ArrayList<Point2>();
		while (scan.hasNextLine()) {
			String[] line = scan.nextLine().split("[\\spositionvelocity=<>,]+");
			list.add(new Point2(line[1], line[2], line[3], line[4]));
		}
		char[][] sky = new char[320][320];
		int count = 0;
		for (int i = 0; i < 100000; i++) {
			if (inBounds(list)) {
				for (int r = 0; r < 320; r++)
					for (int c = 0; c < 320; c++)
						sky[r][c] = '.';
				for (Point2 p : list)
					sky[p.r][p.c] = '#';
				count++;
				print(sky, count, i);
			}
			for (Point2 p : list)
				p.move();
		}
		System.out.println("Final Count: " + count);
	}
	
	public static boolean inBounds(ArrayList<Point2> list) {
		for (Point2 p : list)
			if (!p.inBounds())
				return false;
		return true;
	}
	
	public static void print(char[][] arr, int count, int time) {
		System.out.println("#" + count + " @ " + time);
		
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++)
				System.out.print(arr[r][c]);
			System.out.println();
		}
		
		System.out.println("\n\n==============================================");
	}

	public static void part2(Scanner scan) {

	}
}

class Point2 {
	int r, c, dr, dc;
	
	public Point2(int x, int y, int xVel, int yVel) {
		r = y;
		dr = yVel;
		c = x;
		dc = xVel;
	}
	
	public Point2(String x, String y, String xVel, String yVel) {
		r = Integer.parseInt(y);
		dr = Integer.parseInt(yVel);
		c = Integer.parseInt(x);
		dc = Integer.parseInt(xVel);
	}
	
	public void move() {
		r += dr;
		c += dc;
	}
	
	public boolean inBounds() {
		return r > 0 && c > 0 && r < 320 && c < 320;
	}
}
