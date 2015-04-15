package f2.spw;

import java.awt.Graphics2D;

public class LifepointBoss extends Lifepoint {
	public int lpboss = 20;
	public LifepointBoss (Graphics2D g){
		super(g);
		setImageLifepoint("f2/image/bosslife.png");
	}
	public void drawLifePoint() {
		if( lpboss < 4){
			for (int i = 0; i < lpboss; i++) {
				g.drawImage(this.img, 0 + (i * 30), 20, 25, 25, null);
			}
		}else{
			g.drawImage(this.img, 0, 20, 25, 25, null);
		}
	}
	
	public void getHit() {

	}
	public boolean isAlive(){
		return life;
	}
}
