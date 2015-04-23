package f2.spw;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameOverFrame extends JInternalFrame{
	static final int xPosition = 50, yPosition = 150;
	public GameOverFrame() {
		super("IFrame #", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable
		setSize(300, 300);
		JPanel panel = new JPanel();
		JButton retryBtn = new JButton("Retry");
		JButton quitBtn = new JButton("Quit");
		retryBtn.setPreferredSize(new Dimension(10, 10));
		quitBtn.setPreferredSize(new Dimension(10, 10));
		panel.setLayout(new BorderLayout());
		panel.add(retryBtn, BorderLayout.NORTH);
		panel.add(quitBtn, BorderLayout.SOUTH);
		this.setContentPane(panel);
		// Set the window's location.
		setLocation(xPosition, yPosition);
	}
}
