package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpaceShip extends Sprite{
	
	private int count = 0;
	int step = 12;
	public Image spaceshipPic;
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setImage("f2/image/spaceship.png");
//		try{
//			File file = new File("f2/image/spaceship.png");
//			spaceshipPic = ImageIO.read(file);
//		}catch(IOException e){
//			e.printStackTrace();
//		}
		
		
	}
	public void countItemBullet(){
		count ++;
	}
	public int getCountItemBullet(){
		return count;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(this.img, x, y, width, height, null);
	}

	public void move(int directionX, int directionY){
		if(directionX != 0){
			x += (step * directionX);
			if(x < 0){
				x = 0;
			}
			if(x > 400 - width){
				x = 400 - width;
			}
		}else{
			y += (step * directionY);
				if(y < 0){
					y = 0;
				}
				if(y > 600 - height){
					y = 600 - height;
				}
			}
		}

}

