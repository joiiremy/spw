package f2.spw;

import java.awt.Graphics2D;

public class EnemyBoss extends Enemy {
	private int step = 8;
	public int lp = 20;
	public int speedX = 1;
	public int speedY = 1;
	public EnemyBoss(int x, int y){
		super(x, y);
		setImage("f2/image/boss.png");
		
	}
	
	public void proceed(){
		x += step;
		if(x > Y_TO_FADE - 50){
			step *= -1;
		}else if( x < 0){
			step *= -1;
		}
	}
	
	
	
	
	
}
