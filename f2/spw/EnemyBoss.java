package f2.spw;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class EnemyBoss extends Enemy {
	GameEngine g;
	private int step = 8;
	private int width = 100;
	private int height = 100;
	public int lp = 20;
	public EnemyBoss(int x, int y){
		super(x, y);
		setImage("f2/image/boss.png");
		
	}

	public void enemyShoot(final GameEngine g){
		this.g = g;
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				g.generateEnemyBullet(x + 50, y + 100);
			}
		});
		
		timer.start();
		if(!this.isAlive()){
			timer.stop();
		}
	}
	
	@Override
	public void draw(Graphics2D g){
		g.drawImage(this.img, x, y, width, height, null);
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
