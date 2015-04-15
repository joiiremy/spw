package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Lifepoint{
	Graphics2D g;
	public boolean life = true;
//	public Image lifePic;
	public Image img;
	public Lifepoint(Graphics2D g){
		this.g = g;
	}
	public void setImageLifepoint(String path){
		try{
			File file = new File(path);
			img = ImageIO.read(file);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void drawLifePoint() {

	}
	
	public void getHit() {

	}
	public boolean isAlive(){
		return life;
	}
}
