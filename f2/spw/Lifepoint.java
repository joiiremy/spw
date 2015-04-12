package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Lifepoint{
	GameReporter reporter;
	Graphics2D g;
	public int lp = 3;
	public boolean life = true;
	private Image lifePic;
	public Lifepoint(Graphics2D g){
		this.g = g;
		try{
			File file = new File("f2/image/life.png");
			lifePic = ImageIO.read(file);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void drawLifePoint() {
		if( lp < 4){
			for (int i = 0; i < lp; i++) {
				g.drawImage(lifePic, 0 + (i * 30), 10, 30, 30, null);
			}
		}else{
			g.drawImage(lifePic, 0, 10, 30, 30, null);
		}
		
	}
	
	public void getHit() {
		lp--;
		drawLifePoint();
		if(lp < 0){
			life = false;
		}
	}
	public boolean isAlive(){
		return life;
	}
}
