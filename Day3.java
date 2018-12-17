import java.util.*;
import java.awt.Point;
import java.io.*;

public class Day3 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day3.dat"));

		//part1(scan);
		part2(scan);
	}

	public static void part1(Scanner scan) {
		int[][] felt = new int[1000][1000];
		int count = 0;
		
		while(scan.hasNext()) {
			String[] line = scan.nextLine().split(" ");
			String[] loc = line[2].split("[,:]");
			String[] dimStrings = line[3].split("x");
			
			Point point = new Point(Integer.parseInt(loc[0]), Integer.parseInt(loc[1]));
			Point dims = new Point(Integer.parseInt(dimStrings[0]), Integer.parseInt(dimStrings[1]));
			
			for (int r = (int) point.getY(); r < point.getY() + dims.getY(); r++)
				for (int c = (int) point.getX(); c < point.getX() + dims.getX(); c++) {
					felt[r][c]++;
					if (felt[r][c] == 2)
						count++;
				}
		}
		
		System.out.println(count);
	}

	public static void part2(Scanner scan) {
		List<Claim> list = new ArrayList<Claim>();
		
		while(scan.hasNext()) {
			String[] line = scan.nextLine().split(" ");
			String[] loc = line[2].split("[,:]");
			String[] dims = line[3].split("x");
			
			list.add(new Claim(line[0], loc[0], loc[1], dims[0], dims[1]));
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (!hasIntersections(list, i))
				System.out.println(list.get(i).id);
		}
	}
	
	public static boolean hasIntersections(List<Claim> list, int i) {
		for (int j = 0; j < list.size(); j++) {
			if (j != i && list.get(i).intersects(list.get(j)))
				return true;
		}
		return false;
	}
	
}

class Claim {
	int x, y, width, height;
	String id;
	
	public Claim(String id, String x, String y, String width, String height) {
		this.id = id;
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
		this.width = Integer.parseInt(width);
		this.height = Integer.parseInt(height);
	}
	
	public boolean intersects(Claim other) {
		if (x < other.x) {
			if (x + width <= other.x)
				return false;
		} else {
			if (other.x + other.width <= x)
				return false;
		}
		
		if (y < other.y) {
			if (y + height <= other.y)
				return false;
		} else {
			if (other.y + other.height <= y)
				return false;
		}
		return true;
	}
}