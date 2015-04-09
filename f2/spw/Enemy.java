package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	private int step = 8;
	protected boolean alive = true;
	private Image enemyPic;
	
	public Enemy(int x, int y) {
		super(x, y, 40, 40);
//		try{
//			File file = new File("f2/image/enemy.png");
//			enemyPic = ImageIO.read(file);
//		}catch(IOException e){
//			e.printStackTrace();
//		}
		super.setImage("f2/image/enemy.png");
	}

	@Override
	public void draw(Graphics2D g) {
//		if(y < Y_TO_FADE)
//			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
//		else{
//			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
//					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
//		}
		g.drawImage(this.img, x, y, width, height, null);
		
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}