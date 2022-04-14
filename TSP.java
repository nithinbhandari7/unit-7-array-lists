import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class TSP {
	private int N;
	private Point[] points;
	private boolean[] marked;
	
	public TSP(String fileName) throws FileNotFoundException {
		Scanner fileScan = new Scanner(new File(fileName));
		
		int x = 0;
		int y = 0;
		while(fileScan.hasNextInt()) {
			N = fileScan.nextInt();
			points = new Point[N];
			marked = new boolean[N];
			for(int i = 0; i < N; i++) {
				x = fileScan.nextInt();
				y = fileScan.nextInt();
				points[i] = new Point(x, y);
			}
		}
		
		for(int i = 0; i < points.length; i++) {
			marked[i] = false;
		}
		
		fileScan.close();
	}
	
	public double distance(int i, int j) {
		return points[i].distance(points[j]);
	}
	
	public int findUnmarkedPointClosestToPoint(int j) {
		double minDistance = Integer.MAX_VALUE;
		int minIndex = 0;;
		for(int i = 0; i < points.length; i++) {
			if(!marked[i] && !(i==j)) {
				if(distance(j, i) < minDistance) {
					minDistance = distance(j, i);
					minIndex = i;
				}
			}
		}
		return minIndex;
	}

	public void applyDoubleNeighborHeuristic() {
		DecimalFormat df = new DecimalFormat("##.00");
		int pointA = 0;
		int pointB = findUnmarkedPointClosestToPoint(pointA);
		double lengthOfCycle = distance(pointA, pointB);
		ArrayList<Integer> indexes = new ArrayList();
		indexes.add(pointA);
		marked[pointA] = true;
		indexes.add(pointB);
		marked[pointB] = true;
		
		int p1 = 0;
		int p2 = 0;
		double s = 0;
		double t = 0;

		for(int i = 0; i < N-2; i++) {
			p1 = findUnmarkedPointClosestToPoint(pointA);
			p2 = findUnmarkedPointClosestToPoint(pointB);
			s = distance(p1, pointA);
			t = distance(p2, pointB);
			if(s < t) {
				pointA = p1;
				indexes.add(0, pointA);
				marked[pointA] = true;
				lengthOfCycle += s;
			} else {
				pointB = p2;
				indexes.add(pointB);
				marked[pointB] = true;
				lengthOfCycle += t;
			}
		}
		for(int i = 0; i < indexes.size() - 1; i++) {
			System.out.print(points[indexes.get(i)] + " ->- ");
			System.out.println("" + df.format(distance(indexes.get(i), indexes.get(i+1))) + " ->- ");
		}
		System.out.print(points[indexes.get(indexes.size() - 1)] + " ->- ");
		System.out.println(df.format(distance(indexes.get(indexes.size() - 1), indexes.get(0))) + " ->- " + points[indexes.get(0)]);
		
		lengthOfCycle += distance(pointA, pointB);
		System.out.println(indexes);
		System.out.println(lengthOfCycle);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		TSP test = new TSP("tspTest.txt");
		test.applyDoubleNeighborHeuristic();
	}
}
