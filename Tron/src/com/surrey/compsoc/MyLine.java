package com.surrey.compsoc;

public class MyLine implements Comparable<MyLine> {

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Orientation orientation;

	public MyLine(int x1, int x2, int y1, int y2, Orientation o) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.orientation = o;

	}

	@Override
	public int compareTo(MyLine o) {
		if (orientation == Orientation.HORIZONAL) {
			if (y1 < o.getY1()) {
				return 1;
			}
			if (y1 > o.getY1()) {
				return -1;
			}
			return 1;
		} else {
			if (x1 < o.getX1()) {
				return 1;
			}
			if (x1 > o.getX1()) {
				return -1;
			}
			return 1;
		}
	}

	public int getX1() {
		return x1;
	}

	public int getX2() {
		return x2;
	}

	public int getY1() {
		return y1;
	}

	public int getY2() {
		return y2;
	}

	public Orientation getOreintation() {
		return orientation;
	}

}
