public class Point {
	private int x;
	private int y;
	
	public Point(int numX, int numY) {
		x = numX;
		y = numY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double distance(Point other) {
		return Math.sqrt(Math.pow((double)(other.x-x), 2) + Math.pow((double)(other.y-y), 2));
	}
	
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
}
