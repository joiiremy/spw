package f2.spw;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Image;


public abstract class Sprite {
	int x;
	int y;
	int width;
	int height;
	
	public Sprite(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	abstract public void draw(Graphics2D g);

	public Image img;
	public void setImage(String path){
		try{
			File file = new File(path);
			img = ImageIO.read(file);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public Double getRectangle() {
		return new Rectangle2D.Double(x, y, width, height);
	}
}
