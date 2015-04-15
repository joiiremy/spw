package f2.spw;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LifepointSpaceship extends Lifepoint{
	public int lp = 7;
	public boolean life = true;
	public LifepointSpaceship (Graphics2D g){
		super(g);
		setImageLifepoint("f2/image/shiplife.png");

	}
	public void drawLifePoint() {
		if( lp < 4){
			for (int i = 0; i < lp; i++) {
				g.drawImage(this.img, 0 + (i * 30), 550, 30, 30, null);
			}
		}else{
			g.drawImage(this.img, 0, 550, 30, 30, null);
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
