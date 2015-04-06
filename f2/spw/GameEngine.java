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
	public int lp = 3;
	public Lifepoint lifepoint;
//	public Lifepoint lifepoint = new Lifepoint(gp.big);
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private SpaceShip v;	
	
	private Timer timer;
	private double itemTimer = 0;
	private double enemyTimer = 0;
	
	private long score = 0;
	private double difficulty = 0.1;
	private int cntItem = 0;
	private int upgrade = 0;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		lifepoint = new Lifepoint(gp.big);
		
		
		gp.sprites.add(v);
			
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				lp();
				process();
				bulletProcess();
				itemTimer += 0.05;
				enemyTimer += 0.05;
				if( enemyTimer > 1 ){
					generateEnemy();
					enemyTimer = 0;
				}
				if( itemTimer > 10){
					generateItem();
					itemTimer = 0;
				}
				itemProcess();
			}
		});
		
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	///////////////////// LIFEPOINT /////////////////////////
//	public void lp(){
//		
//		gp.updateGameUI(this);
//		Lifepoint lifepoint = new Lifepoint(gp.big);
//		lifepoint.drawLifePoint();
//	}
	///////////////////// ENERMY JOB ////////////////////////
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
//		lifepoint.drawLifePoint();
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 10;
			}
		}
		
		gp.updateGameUI(this);
		lifepoint.drawLifePoint();
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				System.out.println("enermy crash");
				e.alive = false;
				lifepoint.getHit();
				if(!lifepoint.isAlive()){   
					die();
				}
				return;
			}
			
		}

	}
	
	/////////////////// BULLET JOB //////////////////////////
	protected void generateBullet(int x, int y, int upgrade){
		switch(upgrade){
		case 0: Bullet bc = new Bullet(x + 25, y + 25);
				addSpriteBullet(bc);
				break;
		case 1: Bullet bl = new Bullet(x, y);
				Bullet br = new Bullet(x + 50, y + 50);
				addSpriteBullet(bl);
				addSpriteBullet(br);
				break;
		}
	}
	
	private void bulletProcess(){
		Iterator<Bullet> b_iter = bullets.iterator();
		while(b_iter.hasNext()){
			Bullet b = b_iter.next();
			b.proceed();
		}
		Rectangle2D.Double br;
		for(Bullet b : bullets){
			for(Enemy e: enemies){
				Rectangle2D.Double er = e.getRectangle();
				br = b.getRectangle();
				if(br.intersects(er)){
					b.alive = false;
					e.alive = false;
					System.out.println("kill enermy");
				}
			}
		}
	}
	
	public void addSpriteBullet(Bullet b){
		gp.sprites.add(b);
		bullets.add(b);
	}

	///////////////// ITEM JOB ////////////////////////// 
	private void generateItem(){
		Item i = new Item((int)(Math.random()*390), 30);
		gp.sprites.add(i);
		items.add(i);
	}
	
	
	private void itemProcess(){
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
				cntItem++;
				if( cntItem >= 3 ){
					upgrade = 1;
				}
				collecItem();
			}
		}
		
	}
	private void collecItem(){
		score += 500;
		System.out.println("collect Item");
	}
	
	
	public void die(){
		timer.stop();
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
	public int getNumItem(){
		return cntItem;
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
