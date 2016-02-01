import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUIMenu {
	JFrame frame = new JFrame("Chess");
	JPanel buttons = new JPanel(new GridLayout(1,3));
	public GUIMenu() {
		frame.setSize(new Dimension(600,600));
		frame.setLocationByPlatform(false);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		
		JButton play = new JButton("PLAY");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Board();
			}
		});
		JButton settings = new JButton("SETTINGS");
		JButton highscore = new JButton("HIGHSCORE");
		highscore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame score = new JFrame("HighScores");
				score.setSize(new Dimension(600,600));
				score.setLocationByPlatform(false);
				score.setVisible(true);
				score.setLayout(new GridLayout(11,3));
				
				
			}
		});
		
		buttons.add(highscore);
		buttons.add(play);
		buttons.add(settings);
		
		ImageIcon ic = new ImageIcon(new ImageIcon(getClass().getResource("Images/ChessPic1.jpg")).getImage());
		JLabel l = new JLabel(ic);
		frame.add(BorderLayout.NORTH, l);
		frame.add(BorderLayout.CENTER, buttons);
		frame.pack();
		frame.setVisible(true);
	}
}
