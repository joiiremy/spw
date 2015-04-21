package f2.spw;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public class Main extends JFrame{
	JDesktopPane jdpDesktop;
	public Main(){
		//////////////////////////////////////
		jdpDesktop = new JDesktopPane();
		setContentPane(jdpDesktop);
		//////////////////////////////////////
	}
	public static void main(String[] args){
		JFrame frame = new JFrame("Space War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());
		
		SpaceShip v = new SpaceShip(180, 550, 50, 50);
		GamePanel gp = new GamePanel();
		GameOverFrame gop = new GameOverFrame();
		gop.setVisible(true);
		GameEngine engine = new GameEngine(gp, v);
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		if(engine.die){
			System.out.println("die in main");
		}
		engine.start();
		
	}
}

