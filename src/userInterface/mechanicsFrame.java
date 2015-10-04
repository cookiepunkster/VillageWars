package userInterface;

import java.awt.*;
import javax.swing.*;
import java.io.*;

import javax.swing.border.Border;
import gameLogic.conditions;
import userInterface.MainGameFrame;

public class mechanicsFrame extends JFrame {
	
	CardLayout cl;//card layout for 2 jpanels
	JButton left;
	JButton right;
	//JButton mechanicsButton;
	JPanel mechanicsContainer;
	JPanel mechanicsPanel1;
	JPanel mechanicsPanel2;
	ImageIcon mechanics1;
	ImageIcon mechanics2;
	JButton home;
	
	public mechanicsFrame(){
		
		 setDefaultLookAndFeelDecorated(true);
		 getContentPane().setLayout(new BorderLayout());
		 cl = new CardLayout();
		 mechanicsContainer = new JPanel();
		 mechanicsPanel1 = new JPanel();
		 mechanicsPanel2 = new JPanel();
		 home = new JButton("home");
		 home.setBounds(438, 11, 95, 23);
		 left = new JButton("prev");
		 left.setBounds(899, 312, 95, 23);
		 right = new JButton("next");
		 right.setBounds(899, 312, 95, 23);
		 mechanics1 = new ImageIcon(getClass().getResource("mechanics1.png"));
		 mechanics2 = new ImageIcon(getClass().getResource("2mechanics.png"));

		 mechanicsPanel1 = new JPanel(){ //Add image 
			  public void paintComponent(Graphics g)
			  {
				  Dimension d = getSize();
				  g.drawImage(mechanics1.getImage(), 0, 0, d.width, d.height, null);
			  }
		      }; 
		      
		      mechanicsPanel2 = new JPanel(){ //Add image 
				  public void paintComponent(Graphics g)
				  {
					  Dimension d = getSize();
					  g.drawImage(mechanics2.getImage(), 0, 0, d.width, d.height, null);
				  }
			      }; 
		
		 
		 mechanicsContainer.setLayout(cl);
		 mechanicsContainer.add(mechanicsPanel1, "1");
		 mechanicsContainer.add(mechanicsPanel2, "2");
		 cl.show(mechanicsContainer, "1");
		 mechanicsPanel1.setLayout(null);
		 mechanicsPanel1.add(right);
		 mechanicsPanel2.setLayout(null);
		 mechanicsPanel2.add(left);
	//	 mechanicsPanel2.add(home);
		 getContentPane().add(mechanicsContainer, BorderLayout.CENTER);
		
		
		 
	}
	
	public JButton getHome(){
		return home;
	}
	
	public CardLayout getCl(){
		return cl;
	}
	
	public JButton getLeft(){
		return left;
	}
	
	public JButton getRight(){
		return right;
	}
	
	public JPanel getCont(){
		return mechanicsContainer;
	}
	
	public JPanel getMechanicsPanel1(){
		return mechanicsPanel1;
	}
	
	public JPanel getMechanicsPanel2(){
		return mechanicsPanel2;
	}
	

	
}
