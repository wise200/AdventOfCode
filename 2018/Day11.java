import java.util.*;
import java.io.*;

public class Day11 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("Day11.dat"));

		long begin = System.nanoTime();
		//part1(scan);
		part2(scan);
		long end = System.nanoTime();
		double time = (end - begin) / Math.pow(10, 9);
		System.out.println("\t\t Program finished after " + time + " seconds");
	}

	public static void part1(Scanner scan) {
		int serial = 7315;
		int[][] powers = new int[300][300];
		for (int r = 0; r < 300; r++) {
			for (int c = 0; c < 300; c++) {
				int x = c + 1, y = r + 1;
				int id = x + 10;
				int pow = id * y;
				pow += serial;
				pow *= id;
				pow = (pow / 100) % 10;
				pow -= 5;
				powers[r][c] = pow;
			}
		}
		int maxR = 0, maxC = 0, maxVal = sumPowers(0,0,3,powers);
		for (int r = 0; r < powers.length - 3; r++) {
			for (int c = 0; c < powers[r].length - 3; c++) {
				int sum = sumPowers(r, c, 3, powers);
				if (sum > maxVal) {
					maxR = r;
					maxC = c;
					maxVal = sum;
				}
			}
		}
		
		int x = maxC + 1;
		int y = maxR + 1;
		
		System.out.println(maxVal + " @ " + x + "," + y);
		
	}
	
	public static int sumPowers(int row, int col, int size, int[][] powers) {
		int sum = 0;
		for (int r = row; r < row + size; r++)
			for (int c = col; c < col + size; c++)
				sum += powers[r][c];
		return sum;
	}

	public static void part2(Scanner scan) {
		int serial = 7315;
		int[][] powers = new int[300][300];
		for (int r = 0; r < 300; r++) {
			for (int c = 0; c < 300; c++) {
				int x = c + 1, y = r + 1;
				int id = x + 10;
				int pow = id * y;
				pow += serial;
				pow *= id;
				pow = (pow / 100) % 10;
				pow -= 5;
				powers[r][c] = pow;
			}
		}
		int maxR = 0, maxC = 0, maxSize = 1, maxVal = powers[0][0];
		for (int size = 1; size <= 300; size++) {
			for (int r = 0; r < powers.length - size; r++) {
				for (int c = 0; c < powers[r].length - size; c++) {
					int sum = sumPowers(r, c, size, powers);
					if (sum > maxVal) {
						maxR = r;
						maxC = c;
						maxSize = size;
						maxVal = sum;
					}
				}
			}
		}
		
		int x = maxC + 1;
		int y = maxR + 1;
		
		System.out.println(maxVal + " @ " + x + "," + y + "," + maxSize);
	}
}