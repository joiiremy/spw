package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpaceShip extends Sprite{

	int step = 10;
	private Image spaceshipPic;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		try{
			File file = new File("f2/image/spaceship.png");
			spaceshipPic = ImageIO.read(file);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
//		g.setColor(Color.GREEN);
//		g.fillRect(x, y, width, height);
		g.drawImage(spaceshipPic, x, y, width, height, null);
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
					y = 400 - height;
				}
			}
		}

}

