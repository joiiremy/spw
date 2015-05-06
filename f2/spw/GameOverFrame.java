package f2.spw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameOverFrame extends JInternalFrame{
//	Main m;
	static final int xPosition = 50, yPosition = 150;
	public void closeFrame(){
		this.setVisible(false);
	}
	public GameOverFrame(final GameEngine engine) {
		super("GAME OVER", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable
		setSize(300, 300);
		JPanel panel = new JPanel();
		JButton retryBtn = new JButton("Retry");
		retryBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try{
					closeFrame();
					engine.start();
				}catch(Exception e){
					System.out.println("error" + e);
				}
			}
		});
//		JButton quitBtn = new JButton("Quit");
		retryBtn.setPreferredSize(new Dimension(10, 10));
//		quitBtn.setPreferredSize(new Dimension(10, 10));
		panel.setLayout(new BorderLayout());
		panel.add(retryBtn, BorderLayout.CENTER);
//		panel.add(quitBtn, BorderLayout.SOUTH);
		this.setContentPane(panel);
		// Set the window's location.
		setLocation(xPosition, yPosition);
	}
}
