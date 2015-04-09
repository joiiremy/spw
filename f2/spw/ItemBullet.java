package f2.spw;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ItemBullet extends Item{
//	private Image img;
	public ItemBullet(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		super.setImage("f2/image/item.png");
	}
	
//	public void draw(Graphics2D g) {
//		g.drawImage(this.img, x, y, 20, 20, null);	
//	}
	
	public void collectItem(GameEngine g){	
		g.score += 500;
		System.out.println("collect Item");
	}
	

}
