package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class BulletofEnemy extends Bullet{
	public BulletofEnemy(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	
	public void bulletProceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		
	}
	
}
