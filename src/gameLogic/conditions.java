package gameLogic;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.StyleContext;
import java.io.*;

import userInterface.InitialFrame;
import userInterface.MainGameFrame;
import userInterface.mechanicsFrame;
import mainFile.VillageWars;

public class conditions implements ActionListener {
	//JButton home;
	JPanel mechanicsCont;
	CardLayout cl;
	ImageIcon mechanics2;
	JButton left;
	JPanel getMech1;
	JPanel getMech2;
	JPanel panel2;
	JButton pButton;
	JButton hButton;
	MainGameFrame frame1 = new MainGameFrame();
	JTextArea nameContainer;
    JTextArea goldsArea;
	JTextArea countT1;
	JTextArea countT2;
	JTextArea countT3;
	JTextArea countT4;
	JTextArea countT5;
	JTextArea countT6;
	JTextArea countT7;
	JTextArea countT8;
	JButton removeT1;
	JButton removeT2;
	JButton removeT3;
    JButton removeT4;
	JButton removeT5;
	JButton removeT6;
	JButton removeT7;
	JButton removeT8;
	JButton button1BarbClicked;
	JButton button2ArcherClicked;
	JButton button3GiantClicked;
	JButton button4WallBreakerClicked;
	JButton button5WizardClicked;
	JButton button6HogRiderClicked;
	JButton button7DragonClicked;
	JButton button8WallClicked;
	JButton positionTroops;
	JPanel startPanel;
	String playerName;
	InitialFrame frame2 = new InitialFrame();
	 int[] count = new int[8];
	 int golds = 100;
	mechanicsFrame frame3 = new mechanicsFrame();
	JButton rightClicked;
	JButton leftClicked;
	public conditions(InitialFrame frame2)
	{
		button1BarbClicked = frame2.getButton1Barb();
		button2ArcherClicked = frame2.getButton2Archer();
		button3GiantClicked = frame2.getButton3Giant();
		button4WallBreakerClicked = frame2.getButton4WallBreaker();
		button5WizardClicked = frame2.getButton5Wizard();
		button6HogRiderClicked = frame2.getButton6HogRider();
		button7DragonClicked = frame2.getButton7Dragon();
		button8WallClicked = frame2.getButton8Wall();
		goldsArea = frame2.getGoldArea();
		countT1 = frame2.getTextAreaCountTroop1();
		countT2 = frame2.getTextAreaCountTroop2();
		countT3 = frame2.getTextAreaCountTroop3();
		countT4 = frame2.getTextAreaCountTroop4();
		countT5 = frame2.getTextAreaCountTroop5();
		countT6 = frame2.getTextAreaCountTroop6();
		countT7 = frame2.getTextAreaCountTroop7();
		countT8 = frame2.getTextAreaCountTroop8();
		removeT1 = frame2.getRemoveTroop1();
		removeT2 = frame2.getRemoveTroop2();
		removeT3 = frame2.getRemoveTroop3();
		removeT4 = frame2.getRemoveTroop4();
		removeT5 = frame2.getRemoveTroop5();
		removeT6 = frame2.getRemoveTroop6();
		removeT7 = frame2.getRemoveTroop7();
		removeT8 = frame2.getRemoveTroop8();
		positionTroops = frame2.getPositionButton();
		count = frame2.getCount();
	}
	
	public conditions(mechanicsFrame frame3){
		leftClicked = frame3.getLeft();
		rightClicked = frame3.getRight();
		getMech1 = frame3.getMechanicsPanel1();
		getMech2 = frame3.getMechanicsPanel2();
		cl = frame3.getCl();
		mechanicsCont = frame3.getCont();
		//home = frame3.getHome();
	}
 
	
	public conditions(MainGameFrame frame1)
	{
		pButton = frame1.getButton();   //if pButton clicked
		hButton = frame1.getButton2(); //if hButton clicked
	//	playerName = frame1.getTextArea().getText();
		nameContainer = frame1.getPlayerName();
		startPanel =  frame1.getPanel();
		
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{		
		if(ae.getSource() == pButton)
		{
			if(nameContainer.getText().trim().length() == 0){
				JOptionPane.showMessageDialog(frame1,  "Please indicate your name!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else{
			InitialFrame frame2 = new InitialFrame();
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.setSize(1000, 700);
			frame2.setLocationRelativeTo(null);
			frame2.setVisible(true);
		    conditions listener2 = new conditions(frame2);
		    frame2.getButton1Barb().addActionListener(listener2);	 
		    frame2.getButton2Archer().addActionListener(listener2);
		    frame2.getButton3Giant().addActionListener(listener2);
		    frame2.getButton4WallBreaker().addActionListener(listener2);
		    frame2.getButton5Wizard().addActionListener(listener2);
		    frame2.getButton6HogRider().addActionListener(listener2);
		    frame2.getButton7Dragon().addActionListener(listener2);
		    
		    frame2.getButton8Wall().addActionListener(listener2);
		    frame2.getRemoveTroop1().addActionListener(listener2);
		    frame2.getRemoveTroop2().addActionListener(listener2);
		    frame2.getRemoveTroop3().addActionListener(listener2);
		    frame2.getRemoveTroop4().addActionListener(listener2);
		    frame2.getRemoveTroop5().addActionListener(listener2);
		    frame2.getRemoveTroop6().addActionListener(listener2);
		    frame2.getRemoveTroop7().addActionListener(listener2);
		    frame2.getRemoveTroop8().addActionListener(listener2);
		    
		    frame2.getTextArea().setText(nameContainer.getText());
		    
		    frame2.getPositionButton().addActionListener(listener2);
		
			}
		} else if(ae.getSource() == hButton){
			mechanicsFrame frame3 = new mechanicsFrame();
			frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame3.setSize(1000, 700);
			frame3.setLocationRelativeTo(null);
			frame3.setVisible(true);
			conditions listener3 = new conditions(frame3);
			frame3.getLeft().addActionListener(listener3);
			frame3.getRight().addActionListener(listener3);
			//frame3.getHome().addActionListener(listener3);
		}	
		
		if(ae.getSource() == rightClicked){
			cl.show(mechanicsCont, "2");
		}
		
		if(ae.getSource() == leftClicked){
			cl.show(mechanicsCont, "1");
		}
		
		/*if(ae.getSource() == home){
			frame3.dispose(); di gumagana yung dispose pag nandito pero gumagana siya pag nasa mechanicsFrame.java
		}*/
		
		if(ae.getSource() == button1BarbClicked)
		{
			System.out.println("Barbarian");
		     golds--;
			
			if(golds < 0){
				golds++;
				JOptionPane.showMessageDialog(frame2,  "Not enough gold!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else{
					if(count[0] < 100){
						count[0]++;
					}
					else{
						JOptionPane.showMessageDialog(frame2, "You're maximum allowable number of barbarians is 100!", "Error", JOptionPane.WARNING_MESSAGE);
					}
					
			}
			
			goldsArea.setText(Integer.toString(golds));
			countT1.setText(Integer.toString(count[0]));
			
		}
		
		if(ae.getSource() == button2ArcherClicked)
		{
			
			System.out.println("Archer");
			golds = golds - 5;
			if(golds < 0){
				golds = golds + 5;
				JOptionPane.showMessageDialog(frame2,  "Not enough gold!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else{
			
					if(count[1] < 75){
						count[1]++;
					}
					else{
						JOptionPane.showMessageDialog(frame2, "You're maximum allowable number of archers is 75!", "Error", JOptionPane.WARNING_MESSAGE);
					}
			 }
					goldsArea.setText(Integer.toString(golds));
					countT2.setText(Integer.toString(count[1]));
		}
		if(ae.getSource() == button3GiantClicked)
		{
			System.out.println("Giant");
			golds = golds - 10;
			
			if(golds < 0){
				golds = golds + 10;
				JOptionPane.showMessageDialog(frame2,  "Not enough gold!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			else{
			
					if(count[2] < 20){
						count[2]++;
					}
					else{
						JOptionPane.showMessageDialog(frame2,  "You're maximum allowable number of giants is 20!", "Error", JOptionPane.WARNING_MESSAGE);
					}
			}
			goldsArea.setText(Integer.toString(golds));
			countT3.setText(Integer.toString(count[2]));
		}
		
		if(ae.getSource() == button4WallBreakerClicked)
		{
			System.out.println("Wall Breaker");
			golds = golds - 10;
			
			if(golds < 0){
				golds = golds + 10;
				JOptionPane.showMessageDialog(frame2,  "Not enough gold!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			else{
		
					if(count[3] < 20){
						count[3]++;
					}
					else{
						JOptionPane.showMessageDialog(frame2,  "You're maximum allowable number of wall breakers is 20!", "Error", JOptionPane.WARNING_MESSAGE);
					}
			}
			goldsArea.setText(Integer.toString(golds));
			countT4.setText(Integer.toString(count[3]));
		}
		
		if(ae.getSource() == button5WizardClicked)
		{
			System.out.println("Wizard");
			golds = golds - 10;
			
			if(golds < 0){
				golds = golds + 10;
				JOptionPane.showMessageDialog(frame2,  "Not enough gold!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else{
					if(count[4] < 26){
						count[4]++;
					}
					else{
						JOptionPane.showMessageDialog(frame2,  "You're maximum allowable number of wizards is 26!", "Error", JOptionPane.WARNING_MESSAGE);
					}
			}
			goldsArea.setText(Integer.toString(golds));
			countT5.setText(Integer.toString(count[4]));
		}
		
		if(ae.getSource() == button6HogRiderClicked)
		{
			System.out.println("Hog Rider");
			golds = golds - 20;
			
			if(golds < 0){
				golds = golds + 20;
				JOptionPane.showMessageDialog(frame2,  "Not enough gold!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else{
					if(count[5] < 10){
						count[5]++;
					}
					else{
						JOptionPane.showMessageDialog(frame2,  "You're maximum allowable number of hog riders is 10!", "Error", JOptionPane.WARNING_MESSAGE);
					}
			}
			goldsArea.setText(Integer.toString(golds));
			countT6.setText(Integer.toString(count[5]));
		}
		
		if(ae.getSource() == button7DragonClicked)
		{
			System.out.println("Dragon");
			golds = golds - 30;
			
			if(golds < 0){
				golds = golds + 30;
				JOptionPane.showMessageDialog(frame2,  "Not enough gold!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else{
					if(count[6] < 5){
						count[6]++;
					}
					else{
						JOptionPane.showMessageDialog(frame2,  "You're maximum allowable number of dragons is 5!", "Error", JOptionPane.WARNING_MESSAGE);
					}
			}
			
			goldsArea.setText(Integer.toString(golds));
			countT7.setText(Integer.toString(count[6]));
		}
		
		if(ae.getSource() == button8WallClicked)
		{
			System.out.println("Wall");
			golds--;
			
			if(golds < 0){
				golds++;
				JOptionPane.showMessageDialog(frame2,  "Not enough gold!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else{
					if(count[7] < 25){
						count[7]++;
					}
					else{
						JOptionPane.showMessageDialog(frame2,  "You're maximum allowable number of walls is 25!", "Error", JOptionPane.WARNING_MESSAGE);
					}
			}
			goldsArea.setText(Integer.toString(golds));
			countT8.setText(Integer.toString(count[7]));
		}
		
		if(ae.getSource() == removeT1){
			if(count[0] != 0){
				count[0]--;
				golds = golds + 1;
			}
			countT1.setText(Integer.toString(count[0]));
		    goldsArea.setText(Integer.toString(golds));
		}
		
		if(ae.getSource() == removeT2){
			if(count[1] != 0){
				count[1]--;
				golds = golds + 5;
			}			
			countT2.setText(Integer.toString(count[1]));
			  goldsArea.setText(Integer.toString(golds));
			
		}
		
		if(ae.getSource() == removeT3){
			if(count[2] != 0){
				count[2]--;
				golds = golds + 10;
			}
			countT3.setText(Integer.toString(count[2]));
			  goldsArea.setText(Integer.toString(golds));
		}
		
		if(ae.getSource() == removeT4){
			if(count[3] != 0){
				count[3]--;
				golds = golds + 10;
			}
			countT4.setText(Integer.toString(count[3]));
			  goldsArea.setText(Integer.toString(golds));
		}
		
		if(ae.getSource() == removeT5){
			if(count[4] != 0){
				count[4]--;
				golds = golds + 10;
			}
			countT5.setText(Integer.toString(count[4]));
			  goldsArea.setText(Integer.toString(golds));
		}
		
		if(ae.getSource() == removeT6){
			if(count[5] != 0){
				count[5]--;
				golds = golds + 20;
			}
			countT6.setText(Integer.toString(count[5]));
			  goldsArea.setText(Integer.toString(golds));
		}
		
		
		if(ae.getSource() == removeT7){
			if(count[6] != 0){
				count[6]--;
				golds = golds + 30;
			}
			countT7.setText(Integer.toString(count[6]));
			  goldsArea.setText(Integer.toString(golds));
		}
		
		if(ae.getSource() == removeT8){
			if(count[7] != 0){
				count[7]--;
				golds = golds + 1;
			}
			countT8.setText(Integer.toString(count[7]));
			goldsArea.setText(Integer.toString(golds));
		}
		
		if(ae.getSource() == positionTroops){
			
		}
	
	}
	
	



}
