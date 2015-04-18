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
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
		
	}
	
	public void enemyShoot(GameEngine g){
		g.generateEnemyBullet(x + 10 ,y + 20);	
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