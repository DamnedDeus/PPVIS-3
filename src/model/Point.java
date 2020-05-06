package model;

public class Point {
	private final Double x;
	private final Double y;
	
	public Point(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Point{" + "x=" + this.x + ", y=" + this.y + '}';
	}
}
