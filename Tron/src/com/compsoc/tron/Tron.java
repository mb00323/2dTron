package com.compsoc.tron;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * this is the main class for the game and is using the applet
 * 
 * @author Compsoc
 * 
 */
public class Tron extends Applet implements Runnable, KeyListener {

	/** the Serial Version for the class */
	private static final long serialVersionUID = 1L;
	/** the two light cycles that are rendered onscreen */
	private LightCycle[] lightCycle = new LightCycle[2];
	/** the object contained the grid */
	private Grid grid;
	/** the game loop thread */
	private Thread gameloop;
	/** primary drawing surface for the game */
	private Graphics2D g2d;
	/** buffered image that is copied to the screen */
	private BufferedImage backBuffer;

	/** control locks */
	private boolean playerOneControlsLocked = false;
	private boolean playerTwoControlsLocked = false;

	/** storage for the lines */
	private List<MyLine> horizonalLines = new ArrayList<MyLine>();
	private List<MyLine> verticalLines = new ArrayList<MyLine>();

	/**
	 * Initialises the game
	 */
	@Override
	public void init() {

		backBuffer = new BufferedImage(800, 900, BufferedImage.TYPE_INT_ARGB);
		g2d = backBuffer.createGraphics();
		setSize(800, 900);

		// set up the grid, lightcycles
		grid = new Grid(800, 800, "grid.jpg");
		lightCycle[0] = new LightCycle(200, 500, Direction.UP,
				"bluelightcycle.png");
		lightCycle[1] = new LightCycle(600, 500, Direction.UP,
				"orangelightcycle.png");
		// make this class the key listener the game
		addKeyListener(this);

	}

	/**
	 * this is after init() and starts the game thread
	 */
	@Override
	public void start() {
		gameloop = new Thread(this);
		gameloop.start();
	}

	/**
	 * this contains the key down events for the game
	 */
	@Override
	public void keyPressed(KeyEvent k) {
		int keyCode = k.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_A:
			if (!playerOneControlsLocked) {
				if (!(lightCycle[0].getDirection() == Direction.LEFT)) {
					MyLine temp = lightCycle[0].ChangeDirection(Direction.LEFT);
					if (temp.getOreintation() == Orientation.HORIZONAL) {
						horizonalLines.add(temp);
					} else {
						verticalLines.add(temp);
					}
				}

				playerOneControlsLocked = false;
			}
			break;

		case KeyEvent.VK_S:
			if (!playerOneControlsLocked) {
				if (!(lightCycle[0].getDirection() == Direction.DOWN)) {
					MyLine temp = lightCycle[0].ChangeDirection(Direction.DOWN);
					if (temp.getOreintation() == Orientation.HORIZONAL) {
						horizonalLines.add(temp);
					} else {
						verticalLines.add(temp);
					}
				}
				playerOneControlsLocked = false;
			}
			break;
		case KeyEvent.VK_D:
			if (!playerOneControlsLocked) {
				if (!(lightCycle[0].getDirection() == Direction.RIGHT)) {

					MyLine temp = lightCycle[0]
							.ChangeDirection(Direction.RIGHT);
					if (temp.getOreintation() == Orientation.HORIZONAL) {
						horizonalLines.add(temp);
					} else {
						verticalLines.add(temp);
					}
				}
				playerOneControlsLocked = false;

			}
			break;

		case KeyEvent.VK_W:
			if (!playerOneControlsLocked) {
				if (!(lightCycle[0].getDirection() == Direction.UP)) {

					MyLine temp = lightCycle[0].ChangeDirection(Direction.UP);
					if (temp.getOreintation() == Orientation.HORIZONAL) {
						horizonalLines.add(temp);
					} else {
						verticalLines.add(temp);
					}
				}
				playerOneControlsLocked = false;
			}
			break;

		case KeyEvent.VK_LEFT:
			if (!playerTwoControlsLocked) {
				if (!(lightCycle[1].getDirection() == Direction.LEFT)) {
					MyLine temp = lightCycle[1].ChangeDirection(Direction.LEFT);
					if (temp.getOreintation() == Orientation.HORIZONAL) {
						horizonalLines.add(temp);
					} else {
						verticalLines.add(temp);
					}
				}
				playerTwoControlsLocked = false;
			}
			break;

		case KeyEvent.VK_DOWN:
			if (!playerTwoControlsLocked) {
				if (!(lightCycle[1].getDirection() == Direction.DOWN)) {
					MyLine temp = lightCycle[1].ChangeDirection(Direction.DOWN);
					if (temp.getOreintation() == Orientation.HORIZONAL) {
						horizonalLines.add(temp);
					} else {
						verticalLines.add(temp);
					}
				}
				playerTwoControlsLocked = false;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (!playerTwoControlsLocked) {
				if (!(lightCycle[1].getDirection() == Direction.RIGHT)) {
					MyLine temp = lightCycle[1]
							.ChangeDirection(Direction.RIGHT);
					if (temp.getOreintation() == Orientation.HORIZONAL) {
						horizonalLines.add(temp);
					} else {
						verticalLines.add(temp);
					}
				}
				playerTwoControlsLocked = false;
			}
			break;
		case KeyEvent.VK_UP:
			if (!playerTwoControlsLocked) {
				if (!(lightCycle[1].getDirection() == Direction.UP)) {
					MyLine temp = lightCycle[1].ChangeDirection(Direction.UP);
					if (temp.getOreintation() == Orientation.HORIZONAL) {
						horizonalLines.add(temp);
					} else {
						verticalLines.add(temp);
					}
					playerTwoControlsLocked = false;
				}
			}
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent k) {

		/*
		 * int keyCode = k.getKeyCode(); switch (keyCode) {
		 * 
		 * case KeyEvent.VK_A: if (!playerOneControlsLocked) {
		 * 
		 * playerOneControlsLocked = false; } break;
		 * 
		 * case KeyEvent.VK_S: if (!playerOneControlsLocked) {
		 * 
		 * playerOneControlsLocked = false; } break; case KeyEvent.VK_D: if
		 * (!playerOneControlsLocked) {
		 * 
		 * playerOneControlsLocked = false; } break; case KeyEvent.VK_W: if
		 * (!playerOneControlsLocked) {
		 * 
		 * playerOneControlsLocked = false; } break;
		 * 
		 * case KeyEvent.VK_LEFT: if (!playerTwoControlsLocked) { ;
		 * playerTwoControlsLocked = false; } break;
		 * 
		 * case KeyEvent.VK_DOWN: if (!playerTwoControlsLocked) {
		 * 
		 * playerTwoControlsLocked = false; } break; case KeyEvent.VK_RIGHT: if
		 * (!playerTwoControlsLocked) {
		 * 
		 * playerTwoControlsLocked = false; } break; case KeyEvent.VK_UP: if
		 * (!playerTwoControlsLocked) {
		 * 
		 * playerTwoControlsLocked = false; } break; }
		 */

	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub

	}

	/**
	 * the run event for the thread
	 */
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		while (t == gameloop) {

			try {
				// update internals
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			repaint();
		}

	}

	/**
	 * thread kill for the game, don't call this
	 */
	@Override
	public void stop() {
		gameloop = null;
	}

	/**
	 * paint events for the game
	 */
	@Override
	public void paint(Graphics g) {

		g2d.drawImage(grid.getImage(), 0, 100, this);

		g2d.setColor(Color.ORANGE);

		for (MyLine m : horizonalLines) {
			g2d.drawLine(m.getX1(), m.getY1(), m.getX2(), m.getY2());
		}
		for (MyLine m : verticalLines) {
			g2d.drawLine(m.getX1(), m.getY1(), m.getX2(), m.getY2());
		}

		g2d.drawLine(lightCycle[0].getCurrentLine().getX1(), lightCycle[0]
				.getCurrentLine().getY1(), lightCycle[0].getCurrentLine()
				.getX2(), lightCycle[0].getCurrentLine().getY2());
		g2d.drawLine(lightCycle[1].getCurrentLine().getX1(), lightCycle[1]
				.getCurrentLine().getY1(), lightCycle[1].getCurrentLine()
				.getX2(), lightCycle[1].getCurrentLine().getY2());
		AffineTransform temp1 = new AffineTransform();
		temp1.translate(lightCycle[0].getX() - 5, lightCycle[0].getY() - 10);
		temp1.rotate(lightCycle[0].getDirection().getAngle(), 5, 10);

		g2d.drawImage(lightCycle[0].getImage(), temp1, this);
		AffineTransform temp2 = new AffineTransform();
		temp2.translate(lightCycle[1].getX() - 5, lightCycle[1].getY() - 10);
		temp2.rotate(lightCycle[1].getDirection().getAngle(), 5, 10);

		g2d.drawImage(lightCycle[1].getImage(), temp2, this);

		g2d.drawString(Integer.toString(lightCycle[0].getX()), 10, 110);
		g2d.drawString(Integer.toString(lightCycle[0].getY()), 10, 120);
		g2d.drawString(Integer.toString(lightCycle[1].getX()), 10, 130);
		g2d.drawString(Integer.toString(lightCycle[1].getY()), 10, 140);
		g.drawImage(backBuffer, 0, 0, this);
	}

	/**
	 * update for the game
	 */
	@Override
	public void update(Graphics g) {
		lightCycle[0].update();
		lightCycle[1].update();
		checkCollisions();
		paint(g);
	}

	public void checkCollisions() {
		Thread t = Thread.currentThread();

		// if lightcycle orientation is up or down check vertical lines
		// if lightcycle orientation is left or right check horizontal lines
		for (int i = 0; i < horizonalLines.size(); i++) {
			MyLine temp = horizonalLines.get(i);
			if (temp.getY1() == lightCycle[0].getY()) {
				if ((temp.getX1() >= lightCycle[0].getX() && temp.getX2() <= lightCycle[0].getX())
						|| (temp.getX2() >= lightCycle[1].getX() && temp
								.getX1() <= lightCycle[1].getX())) {
					lightCycle[0].crash(200, 500);
					lightCycle[1].crash(600, 500);
					horizonalLines.clear();
					verticalLines.clear();
					try {
						t.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			if (temp.getY1() == lightCycle[1].getY()) {
				if ((temp.getX1() >= lightCycle[1].getX() && temp.getX2() <= lightCycle[1]
						.getX())
						|| (temp.getX2() >= lightCycle[1].getX() && temp
								.getX1() <= lightCycle[1].getX())) {
					lightCycle[0].crash(200, 500);
					lightCycle[1].crash(600, 500);
					horizonalLines.clear();
					verticalLines.clear();
					try {
						t.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		for (int i = 0; i < verticalLines.size(); i++) {
			MyLine temp = verticalLines.get(i);
			if (temp.getX1() == lightCycle[0].getX()) {
				if ((temp.getY1() >= lightCycle[0].getY() && temp.getY2() <= lightCycle[0]
						.getY())
						|| (temp.getY2() >= lightCycle[0].getY() && temp
								.getY1() <= lightCycle[0].getY())) {
					lightCycle[0].crash(200, 500);
					lightCycle[1].crash(600, 500);
					horizonalLines.clear();
					verticalLines.clear();
					try {
						t.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			if (temp.getX1() == lightCycle[1].getX()) {
				if ((temp.getY1() >= lightCycle[1].getY() && temp.getY2() <= lightCycle[1]
						.getY())
						|| temp.getY2() >= lightCycle[1].getY()
						&& temp.getY1() <= lightCycle[1].getY()) {
					lightCycle[0].crash(200, 500);
					lightCycle[1].crash(600, 500);
					horizonalLines.clear();
					verticalLines.clear();
					try {
						t.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		if (lightCycle[0].getX() > 799 || lightCycle[0].getX() < 0
				|| lightCycle[0].getY() > 900 || lightCycle[0].getY() < 100) {
			lightCycle[0].crash(200, 500);
			lightCycle[1].crash(600, 500);
			horizonalLines.clear();
			verticalLines.clear();
			try {
				t.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (lightCycle[1].getX() > 799 || lightCycle[1].getX() < 0
				|| lightCycle[1].getY() > 900 || lightCycle[1].getY() < 100) {
			lightCycle[0].crash(200, 500);
			lightCycle[1].crash(600, 500);
			horizonalLines.clear();
			verticalLines.clear();
			try {
				t.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
