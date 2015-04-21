package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
	GameOverFrame gop = new GameOverFrame();
	public LifepointSpaceship shipLifepoint;
	public LifepointBoss bossLifepoint;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private SpaceShip v;	
	
	private Timer timer;
	double wordBossTimer = 0;
	private double itemTimer = 0;
	private double itemClearMapTimer = 0;
	private double itemLifeTimer = 0;
	private double enemyTimer = 0;
	private double enemyshootingTimer = 0;
	private double bulletReset = 0;
	
	private boolean unlockBulletBoss = false;
	private boolean bossLife = false;
	private boolean stopgenerate = true;
	private boolean stop = true;
	private boolean bossComing = false;
	private boolean wordBoss = false;
	public long score = 0;
	private double difficulty = 0.1;
	private int upgrade = 0;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		shipLifepoint = new LifepointSpaceship(gp.big);		
		bossLifepoint = new LifepointBoss(gp.big);	
		
		gp.sprites.add(v);
			
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
				bulletProcess();				
				itemProcess();
			}
		});
		
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}

	///////////////////// ENERMY JOB ////////////////////////
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		addSpriteEnemy(e);
	}
	
	private void generateEnemyBoss(){
		bossComing = false;
		EnemyBoss eboss = new EnemyBoss(150, 20);
		eboss.enemyShoot(this);
		addSpriteEnemy(eboss);
	}
	
	private void generateEnemyShooting(){
		EnemyShooting es = new EnemyShooting((int)(Math.random()*390), 30);
//		es.enemyShoot(this);
		addSpriteEnemy(es);
	}
	
	private void addSpriteEnemy(Enemy e){
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	public void clearMap(){
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.alive = false;
		}
	}
	
//	Class[] elist = {EnemyShooting.class, EnemyBoss.class, Enemy.class};
	
	private void process(){
		enemyTimer += 0.05;
		if( enemyTimer > 1  && stopgenerate){
			generateEnemy();
			enemyTimer = 0;
		}
		enemyshootingTimer += 0.05;
		if( enemyshootingTimer > 5 && stopgenerate){
			generateEnemyShooting();
			generateEnemyShooting();
			enemyshootingTimer = 0;
//			Enemy e = elist[Random.nextInt(2)].newInstance(x, y)
		}
		
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			if( e instanceof EnemyShooting){
				((EnemyShooting) e).enemyShoot(this);
			}
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 10;
			}
		}
		if(score > 200 && stop){
			stop = gotoBossStage();
		}
		
		gp.updateGameUI(this);
		shipLifepoint.drawLifePoint();
		// ===== BOSS STAGE BOSS STAGE BOSS STAGE BOSS STAGE BOSS STAGE BOSS STAGE===== // 
		if(bossLife){
			bossStage();
		}
		if(bossComing){
			generateEnemyBoss();
			unlockBulletBoss = true;
			bossLife = true;
			System.out.println("bossComing: " + bossComing);
		}
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				System.out.println("enermy crash");
				e.alive = false;
				shipLifepoint.getHit();
				if(!shipLifepoint.isAlive()){   
					die();
				}
				return;
			}
			
		}
		
	}
	
	/////////////////// BULLET JOB //////////////////////////
	public void generateBullet(int x, int y, int upgrade){
		switch(upgrade){
		case 0: Bullet bc = new Bullet(x + 25, y);
				addSpriteBullet(bc);
				break;
		case 1: Bullet bl = new Bullet(x, y);
				Bullet br = new Bullet(x + 50, y);
				addSpriteBullet(bl);
				addSpriteBullet(br);
				break;
		}
	}
	
	public void generateBossBullet(int x, int y){
		BulletBoss bb = new BulletBoss(x , y);
		addSpriteBullet(bb);
	}
	
	public void generateEnemyBullet(int x, int y){
		BulletofEnemy b = new BulletofEnemy(x + 10, y + 20);
		addSpriteBullet(b);
	}
	public void addSpriteBullet(Bullet b){
		gp.sprites.add(b);
		bullets.add(b);
	}
	
	private void bulletProcess(){
		if(upgrade != 0){
			bulletReset += 0.05;
			if( bulletReset > 7 ){
				upgrade = 0;
				bulletReset = 0;
			}
		}
		Iterator<Bullet> b_iter = bullets.iterator();
		while(b_iter.hasNext()){
			Bullet b = b_iter.next();
			b.bulletProceed();
		}
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double br;
		for(Bullet b : bullets){
			br = b.getRectangle();
			if( br.intersects(vr) && (b instanceof BulletofEnemy)){
				b.alive = false;
//				gp.sprites.remove(b);
				System.out.println("========= life point : " + shipLifepoint.lp);
				shipLifepoint.getHit();
				if(!shipLifepoint.isAlive()){   
					die();
				}
				break;
			}
			for(Enemy e: enemies){
				Rectangle2D.Double er = e.getRectangle();
				br = b.getRectangle();
				if(br.intersects(er)){
					b.alive = false;
//					gp.sprites.remove(b);
					if( e instanceof EnemyBoss ){
						bossLifepoint.getHit();
						if(!bossLifepoint.isAlive()){   
							die();
						}
					}else{
						e.alive = false;
					}
					
				}
			}
		}
	}

	///////////////// ITEM JOB ////////////////////////// 
	private void generateItemBullet(){
		ItemBullet i = new ItemBullet((int)(Math.random()*390), 30);
		gp.sprites.add(i);
		items.add(i);
	}
	
	private void generateItemClearMap(){
		ItemClearMap ic = new ItemClearMap((int)(Math.random()*390),30);
		gp.sprites.add(ic);
		items.add(ic);
	}
	
	private void generateItemLife(){
		ItemLife il = new ItemLife((int)(Math.random()*390), 30);
		gp.sprites.add(il);
		items.add(il);
	}
	
	private void itemProcess(){
		itemTimer += 0.05;
		itemClearMapTimer += 0.05;
		itemLifeTimer += 0.05;
		if( itemClearMapTimer > 5 && stopgenerate){
			generateItemClearMap();
			itemClearMapTimer = 0;
		}
		if( itemTimer > 3 && stopgenerate){
			generateItemBullet();
			itemTimer = 0;
		}
		if( itemLifeTimer > 7 && stopgenerate){
			generateItemLife();
			itemLifeTimer = 0;
		}
		
		Iterator<Item> i_iter = items.iterator();
		while(i_iter.hasNext()){
			Item i = i_iter.next();
			i.proceed();
			
			if(!i.isAlive()){
				i_iter.remove();
				gp.sprites.remove(i);
			}
		}
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double ir;
		for(Item i : items){
			ir = i.getRectangle();
			if(ir.intersects(vr)){
				i.alive = false;
				i.collectItem(this);
				if(i instanceof ItemBullet){
					v.countItemBullet();
				}
				if( v.getCountItemBullet() >= 3 ){
					upgrade = 1;
				}
			}
		}	
	}
	
/////////////////////////////////////////////////////////////////////
	public boolean die = false;
	public void die(){
		gop.createFrame();
		System.out.println("die");
		timer.stop();
//		die = true;
//		try{
//			Thread.sleep(1000);
//			System.out.println("delay");
//			timer.stop();
//		}catch(Exception e){
//			System.out.println(e);
//		}	
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP: 
			v.move(0,-1);
			break;
		case KeyEvent.VK_DOWN:
			v.move(0, 1);
			break;
		case KeyEvent.VK_X:
			generateBullet(v.x, v.y, upgrade);
			break;
		case KeyEvent.VK_LEFT:
			v.move(-1,0);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1,0);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		}
	}
	
	public boolean gotoBossStage(){
		wordBoss = true;
		stopgenerate = false;
		wordBossTimer += 0.05;
		if( wordBossTimer > 5 ){
			wordBoss = false;
			bossComing = true;
			return false;
		}
		return stop;
	}
	public boolean drawBossStage(){
		return wordBoss;
	}
	
	public void bossStage(){
		bossLifepoint.drawLifePoint();
	}
	
//	public boolean bossStage(){
//		bossLifepoint.drawLifePoint();
//		return bossLife;
//	}
	
	public boolean bossNow(){
		return bossLife;
	}
	
	public int getNumBossLife(){
		return bossLifepoint.lpboss;
	}
	public int getNumLife(){
		return shipLifepoint.lp;
	}
	
	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
