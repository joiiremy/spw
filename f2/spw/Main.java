package f2.spw;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame{
	public JDesktopPane jdpDesktop;
	public Main(){
		jdpDesktop = new JDesktopPane();
		setContentPane(jdpDesktop);
	}
	
	public static void main(String[] args){
		Main frame = new Main();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.setVisible(true);
		frame.getContentPane().setLayout(new BorderLayout());
		SpaceShip v = new SpaceShip(180, 550, 50, 50);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v, frame.jdpDesktop);
		System.out.println("main");
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		engine.start();
		
		
	}
}