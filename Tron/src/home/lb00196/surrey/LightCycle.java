package home.lb00196.surrey;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class LightCycle {
	private int startX;
 	private int startY;
	private int y;
	private int x;
	private Direction direction;
	private Image image;
	private MyLine currentLine;
	
	


	public LightCycle(){
		this.x = 0;
		this.y = 0;
		this.direction = Direction.UP;
		
	}
	
	public LightCycle(int x, int y ,Direction d, String filename){
		this.x = x;
		this.y = y;
		this.direction = d;
		this.startX = x;
		this.startY = y;
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url2 = this.getClass().getResource(filename);
		
		image = tk.getImage(url2);
		if(startX != x){
			currentLine = new MyLine(startX, x, startY, y, Orientation.HORIZONAL);
		}else{
			currentLine = new MyLine(startX, x, startY, y, Orientation.VERTICAL);
		}
		
	}

	public Image getImage() {
		return image;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public Direction getDirection() {
		return direction;
	}
	
	public void update(){
		
		if (direction == Direction.UP){
			y--;
			y--;
		}
		
		if (direction == Direction.DOWN){
			y++;
			y++;
		}
		
		if (direction == Direction.RIGHT){
			x++;
			x++;
		}
		
		if (direction == Direction.LEFT){
			x--;
			x--;
		}
		
		
		
		if(startX != x){
			currentLine = new MyLine(startX, x, startY, y, Orientation.HORIZONAL);
		}else{
			currentLine = new MyLine(startX, x, startY, y, Orientation.VERTICAL);
		}
		
		
		
	}
	
	
	public void crash(int x, int y){
		this.x = x;
		this.y = y; 
		this.startX = x;
		this.startY = y;
		
	}
	
	public MyLine ChangeDirection(Direction d){
		MyLine line = null;
		if(startX != x){
			line = new MyLine(startX, x, startY, y, Orientation.HORIZONAL);
		}else{
			line = new MyLine(startX, x, startY, y, Orientation.VERTICAL);
		}
		startX = x;
		startY = y;
		
		
		direction = d;
		return line;
	} 
	

	public MyLine getCurrentLine() {
		return currentLine;
	}
}
