package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Image;
import javax.imageio.ImageIO;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
	private Image img;
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		try{
			File file = new File("f2/image/background.png");
			img = ImageIO.read(file);
		}catch(IOException e){
			e.printStackTrace();
		}
		big = (Graphics2D) bi.getGraphics();
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		big.setColor(Color.WHITE);
		big.drawImage(img, 0, 0, null);
		big.drawString(String.format("%d", reporter.getNumItem()), 0, 20);
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
