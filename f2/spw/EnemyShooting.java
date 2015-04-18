package f2.spw;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyShooting extends Enemy {
	GameEngine g;
	
	public EnemyShooting(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		setImage("f2/image/enemyshooting.png");
	}
	public void enemyShoot(GameEngine g) {
//		this.g = g;
//		Timer timer = new Timer(1000, new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				g.generateEnemyBullet(x + 10, y + 20);
//			}
//		});
//		
//		timer.start();
//		if(!this.isAlive()){
//			timer.stop();
//		}
		g.generateEnemyBullet(x + 10, y + 20);

	}
	
	public boolean isAlive(){
		return alive;
	}

}
