package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	protected int step = 12;
	protected boolean alive = true;
	
	public Bullet(int x, int y) {
		super(x, y, 5, 8);
		
	}

	@Override
	public void draw(Graphics2D g) {
//		if(y < Y_TO_FADE)
//			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
//		else{
//			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
//					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
//		}
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
		
	}
	
	public boolean isAlive(){
		return alive;
	}

	public void bulletProceed() {
		// TODO Auto-generated method stub
		y -= step;
		if(y < 0){
			alive = false;
		}
	}
}