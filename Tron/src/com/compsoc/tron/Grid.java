package com.compsoc.tron;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Grid {

	private int sizeX;
	private int sizeY;
	private Image image;
	
	public Grid(int x, int y, String url){
		this.sizeX = x;
		this.sizeY = y;
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url2 = this.getClass().getResource(url);
		
		image = tk.getImage(url2);
		
		
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public Image getImage() {
		return image;
	}
	
	
	
}
