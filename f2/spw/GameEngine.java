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
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private SpaceShip v;	
	
	private Timer timer;
	private double itemTimer = 0;
	private double itemClearMapTimer = 0; 
	private double enemyTimer = 0;
	private double bulletReset = 0;
	
	public long score = 0;
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
	///////////////////// LIFEPOINT /////////////////////////

	///////////////////// ENERMY JOB ////////////////////////
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
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
	
	private void process(){
		enemyTimer += 0.05;
		if( enemyTimer > 1 ){
			generateEnemy();
			enemyTimer = 0;
		}
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
	
	private void bulletProcess(){
		if(upgrade != 0){
			bulletReset += 0.05;
			if( bulletReset == 3 ){
				upgrade = 0;
			}
		}
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
	
	
	private void itemProcess(){
		itemTimer += 0.05;
		itemClearMapTimer += 0.05;
		if( itemClearMapTimer > 5 ){
			System.out.println("itemClear Timer :" + itemClearMapTimer);
			generateItemClearMap();
			itemClearMapTimer = 0;
		}
		if( itemTimer > 2){
			generateItemBullet();
			itemTimer = 0;
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
				System.out.println(v.getCountItemBullet());
				if( v.getCountItemBullet() >= 3 ){
					upgrade = 1;
				}
			}
		}
		
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
