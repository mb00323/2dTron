package home.lb00196.surrey;

public enum Direction {
	UP(0), DOWN(Math.PI), LEFT(Math.PI * 1.5), RIGHT(Math.PI * 0.5);

	private double angle;

	private Direction(double angle) {
		this.angle = angle;
	}

	public double getAngle() {
		return angle;
	}

}
