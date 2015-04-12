package f2.spw;

public class EnemyShooting extends Enemy{

	public EnemyShooting(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		setImage("f2/image/enemyshooting.png");
	}
	
	public void enemyShoot(GameEngine g){
		g.generateEnemyBullet(x + 10 ,y + 20);	
	}
	
	
}
