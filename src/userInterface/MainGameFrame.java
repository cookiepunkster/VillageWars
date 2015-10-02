package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import gameLogic.conditions;


public class MainGameFrame extends JFrame{
	JButton playButton;
	JButton menuButton;
    JPanel panel1;
	ImageIcon image1;
	JLabel label1;
	JTextArea nameTextArea;
	
	  public MainGameFrame()  //constructor
	  {
		  super("Main Frame");
		  setDefaultLookAndFeelDecorated(true);
	      image1 = new ImageIcon(getClass().getResource("startScreen.png"));
		  panel1 = new JPanel(){    //Village War screen
			  public void paintComponent(Graphics g)
			  {
				  Dimension d = getSize();
				  g.drawImage(image1.getImage(), 0, 0, d.width, d.height, null);
			  }
		      };
		  getContentPane().add(panel1);
		   panel1.setLayout(null);
		  
		  nameTextArea = new JTextArea();       // Name of the player must be inserted here
		  nameTextArea.setBounds(390, 318, 200, 25);
		  panel1.add(nameTextArea);
		  playButton = new JButton("Play");
		  playButton.setBounds(420, 350, 70, 23);
		  panel1.add(playButton);  //ADD TO THE PANEL
		  menuButton = new JButton("How to Play");
		  menuButton.setBounds(490, 350, 100, 23);
		  panel1.add(menuButton);
		  setResizable(false);
	  }
	  
	  public JButton getButton() {
		    return playButton;
		}
	  
	  public JPanel getPanel() {
		    return panel1;
		}
	  
	  
	  public JTextArea getPlayerName() {
		    return nameTextArea;
		}
}

