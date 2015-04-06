package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Item extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
		
	private int step = 8;
	protected boolean alive = true;
	private Image itemPic;
		
	public Item(int x, int y) {
		super(x, y, 20, 20);
		try{
			File file = new File("f2/image/item.png");
			itemPic = ImageIO.read(file);
		}catch(IOException e){
			e.printStackTrace();
		}
			
	}

		@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
//		g.setColor(Color.GREEN);
//		g.fillRect(x, y, width, height);
		g.drawImage(itemPic, x, y, width, height, null);
			
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}
