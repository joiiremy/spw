package f2.spw;

public class BulletBoss extends Bullet{
	public BulletBoss(int x, int y){
		super(x,y);
	}
	
	public void bulletProceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
}
