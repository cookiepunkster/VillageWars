package userInterface;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.Graphics;
import javax.swing.border.Border;
import gameLogic.conditions;
import userInterface.MainGameFrame;

public class InitialFrame extends JFrame{
    JButton selectTroops;
    JPanel villagePanel;
    JPanel bottomPanel;
    JPanel topPanel;
    JPanel container;
    JPanel chatPanel;
	ImageIcon image1;
	ImageIcon tower;
	JLabel label1;
	JButton sendButton;
	JTextArea textArea1;
	JTextArea textArea2;
	JTextArea nameTextArea1;
	JTextArea goldsArea;
    JButton button1Barb;
    JButton button2Archer;
    JButton button3Giant;
    JButton button4WallBreaker;
    JButton button5Wizard;
    JButton button6HogRider;
    JButton button7Dragon;
    JButton button8Wall;
    JButton removeTroop1;
	JButton removeTroop2;
	JButton removeTroop3;
	JButton removeTroop4;
	JButton removeTroop5;
	JButton removeTroop6;
	JButton removeTroop7;
	JButton removeTroop8;
	JTextArea countTroop1;
	JTextArea countTroop2;
	JTextArea countTroop3;
	JTextArea countTroop4;
	JTextArea countTroop5;
	JTextArea countTroop6;
	JTextArea countTroop7;
	JTextArea countTroop8;
	JButton attackButton;
    int[] count = new int[8];
    
	public InitialFrame(){
	     setDefaultLookAndFeelDecorated(true);
		 setLayout(new BorderLayout());
		 
		 chatPanel = new JPanel();    //chatSystem
		 bottomPanel = new JPanel();  //contains the troops
		 topPanel = new JPanel();     //contains the name of the player, number of golds, level attained, 
		 villagePanel = new JPanel();      // battlefield or the village (green background)
		 
		 
		 sendButton = new JButton("Send");
		 textArea1 = new JTextArea(25, 21); //height, width   uneditable text area for incoming and outcoming messages
		 textArea2 = new JTextArea(5, 14);   //type here text area
	     image1 = new ImageIcon(getClass().getResource("wee.png"));
		 tower = new ImageIcon(getClass().getResource("tower.png"));
	     label1 = new JLabel(tower);
	    
		 villagePanel = new JPanel(){ //Add image 
				  public void paintComponent(Graphics g)
				  {
					  g.drawImage(image1.getImage(), 0, 0, 800, 600, null);
				  }
			      }; 
		 villagePanel.add(label1);
		 
		 
//TOP PANEL
		 nameTextArea1 = new JTextArea(2, 10);
		 nameTextArea1.setEditable(false);
		 
		 goldsArea = new JTextArea(2, 10);
		 goldsArea.setEditable(false);
		 goldsArea.setText(Integer.toString(100));
		 topPanel.add(nameTextArea1);
		 topPanel.add(goldsArea);
		 
//CHAT PANEL			    
	    textArea1.setEditable(false);   //can not be edited
	    textArea2.setLineWrap (true);   // use this to keep the textArea not resizable
	    textArea2.setText("Type your message here."); 
	    chatPanel.add(textArea1);
	    chatPanel.add(textArea2);
	    chatPanel.add(sendButton);
	    
// BOTTOM PANEL
	    bottomPanel.setLayout(new BorderLayout());
		
		JPanel countPanel = new JPanel();
	    JPanel removeTroopPanel = new JPanel();
		JPanel troopPanel = new JPanel();
	
		
	
	    ImageIcon barb = new ImageIcon(getClass().getResource("barb.png"));  //insert picture in a button
	    button1Barb = new JButton(barb);
	    
	    ImageIcon archer = new ImageIcon(getClass().getResource("archer.png"));
	    button2Archer = new JButton(archer);
	    
	    ImageIcon giant= new ImageIcon(getClass().getResource("giant.png"));
	    button3Giant = new JButton(giant);
	 
	    ImageIcon wallbreaker= new ImageIcon(getClass().getResource("wallbreaker.png"));
	    button4WallBreaker = new JButton(wallbreaker);
	    
	    ImageIcon wizard= new ImageIcon(getClass().getResource("wizard.png"));
	    button5Wizard = new JButton(wizard);
	    
	    ImageIcon hogrider= new ImageIcon(getClass().getResource("hogrider.png"));
	    button6HogRider = new JButton(hogrider);
	    
	    ImageIcon dragon= new ImageIcon(getClass().getResource("dragon.png"));
	    button7Dragon = new JButton(dragon);
	    
	    ImageIcon wall= new ImageIcon(getClass().getResource("wall.png"));
	    button8Wall = new JButton(wall);
	    
//Group of text area that shows the number of count per troop
        countTroop1 = new JTextArea(1, 6);  //height, width
		countTroop2 = new JTextArea(1, 6);
	    countTroop3 = new JTextArea(1, 6);
		countTroop4 = new JTextArea(1, 6);
		countTroop5 = new JTextArea(1, 6);
		countTroop6 = new JTextArea(1, 6);
		countTroop7 = new JTextArea(1, 6);
	    countTroop8 = new JTextArea(1, 6);
	 //   attackButton =  new JButton("Attack");
	   // attackButton.setBounds(500, 500, 80, 60);
		
	    troopPanel.add(button1Barb);
	    troopPanel.add(button2Archer);
	    troopPanel.add(button3Giant);
	    troopPanel.add(button4WallBreaker);
	    troopPanel.add(button5Wizard);
	    troopPanel.add(button6HogRider);
	    troopPanel.add(button7Dragon);
	    troopPanel.add(button8Wall);
	  //  troopPanel.add(attackButton);
	    
	    removeTroop1 = new JButton("X");
	    removeTroop1.setFont(new Font("Arial", Font.BOLD, 9));
	    removeTroop1.setMargin(new Insets(1,1,3,3));
	    
	    removeTroop2 = new JButton("X");
	    removeTroop2.setFont(new Font("Arial", Font.BOLD, 9));
	    removeTroop2.setMargin(new Insets(1,1,3,3));
	    
	    removeTroop3 = new JButton("X");
	    removeTroop3.setFont(new Font("Arial", Font.BOLD, 8));
	    removeTroop3.setMargin(new Insets(1,1,3,3));
	    
	    removeTroop4 = new JButton("X");
	    removeTroop4.setFont(new Font("Arial", Font.BOLD, 8));
	    removeTroop4.setMargin(new Insets(1,1,3,3));
	    
	    removeTroop5 = new JButton("X");
	    removeTroop5.setFont(new Font("Arial", Font.BOLD, 8));
	    removeTroop5.setMargin(new Insets(1,1,3,3));
	    
	    removeTroop6 = new JButton("X");
	    removeTroop6.setFont(new Font("Arial", Font.BOLD, 8));
	    removeTroop6.setMargin(new Insets(1,1,3,3));
	    
	    removeTroop7 = new JButton("X");
	    removeTroop7.setFont(new Font("Arial", Font.BOLD, 8));
	    removeTroop7.setMargin(new Insets(1,1,3,3));
	    
	    removeTroop8 = new JButton("X");
	    removeTroop8.setFont(new Font("Arial", Font.BOLD, 8));
	    removeTroop8.setMargin(new Insets(1,1,3,3));
	    
		attackButton = new JButton("Attack");
	    	    
//add the text area of count on the top of x button
	    countPanel.add(countTroop1);
	    countPanel.add(Box.createHorizontalStrut(10));
	    countPanel.add(countTroop2);
	    countPanel.add(Box.createHorizontalStrut(10));	    
	    countPanel.add(countTroop3);
	    countPanel.add(Box.createHorizontalStrut(10));
	    countPanel.add(countTroop4);
	    countPanel.add(Box.createHorizontalStrut(10));
	    countPanel.add(countTroop5);
	    countPanel.add(Box.createHorizontalStrut(10));
	    countPanel.add(countTroop6);
	    countPanel.add(Box.createHorizontalStrut(10));
	    countPanel.add(countTroop7);
	    countPanel.add(Box.createHorizontalStrut(10));
	    countPanel.add(countTroop8);
	    countPanel.add(attackButton);
	    
	    removeTroopPanel.add(removeTroop1);
		removeTroopPanel.add(Box.createHorizontalStrut(60));
		removeTroopPanel.add(removeTroop2);
		removeTroopPanel.add(Box.createHorizontalStrut(60));
		removeTroopPanel.add(removeTroop3);
		removeTroopPanel.add(Box.createHorizontalStrut(60));
		removeTroopPanel.add(removeTroop4);
		removeTroopPanel.add(Box.createHorizontalStrut(60));
		removeTroopPanel.add(removeTroop5);
		removeTroopPanel.add(Box.createHorizontalStrut(60));
		removeTroopPanel.add(removeTroop6);
		removeTroopPanel.add(Box.createHorizontalStrut(60));
		removeTroopPanel.add(removeTroop7);
		removeTroopPanel.add(Box.createHorizontalStrut(60));
		removeTroopPanel.add(removeTroop8);
	    
	    troopPanel.setBackground(Color.black);
	    removeTroopPanel.setBackground(Color.black);
	    countPanel.setBackground(Color.black);
	    
	    bottomPanel.add(countPanel, BorderLayout.NORTH);
	    bottomPanel.add(removeTroopPanel, BorderLayout.CENTER);
	    bottomPanel.add(troopPanel, BorderLayout.SOUTH);

//color per panel	    
		chatPanel.setBackground(Color.gray);
		bottomPanel.setBackground(Color.black);
		topPanel.setBackground(Color.white);

//adjust this for the width and height per panel
		topPanel.setPreferredSize(new Dimension(1000, 30));   //width, height
        chatPanel.setPreferredSize(new Dimension(245, 600));
        villagePanel.setPreferredSize(new Dimension(200, 300));
		bottomPanel.setPreferredSize(new Dimension(800, 135)); 
	
		 add(chatPanel, BorderLayout.WEST);
		 add(topPanel, BorderLayout.NORTH);
		 add(villagePanel, BorderLayout.CENTER);
		 add(bottomPanel, BorderLayout.SOUTH);

	}
	
	public JTextArea getTextArea(){
		return nameTextArea1;
	}
	
	  public JButton getButton1Barb() {
		    return button1Barb;
		}
	  
	  public JButton getButton2Archer() {
		    return button2Archer;
		}
	  
	  public JButton getButton3Giant() {
		    return button3Giant;
		}
	  public JButton getButton4WallBreaker() {
		    return button4WallBreaker;
		}
	  
	  public JButton getButton5Wizard() {
		    return button5Wizard;
		}
	  
	  public JButton getButton6HogRider() {
		    return button6HogRider;
		}
	  
	  public JButton getButton7Dragon() {
		    return button7Dragon;
		}
	  public JButton getButton8Wall() {
		    return button8Wall;
		}
	  
	 public JTextArea getGoldArea()
	 {
		 return goldsArea;
	 }
	 public JTextArea getTextAreaCountTroop1()
	 {
		 return countTroop1;
	 }
	 
	 public JTextArea getTextAreaCountTroop2()
	 {
		 return countTroop2;
	 }
	 
	 public JTextArea getTextAreaCountTroop3()
	 {
		 return countTroop3;
	 }
	 
	 public JTextArea getTextAreaCountTroop4()
	 {
		 return countTroop4;
	 }
	 
	 public JTextArea getTextAreaCountTroop5()
	 {
		 return countTroop5;
	 }
	 
	 public JTextArea getTextAreaCountTroop6()
	 {
		 return countTroop6;
	 }
	 
	 public JTextArea getTextAreaCountTroop7()
	 {
		 return countTroop7;
	 }
	 
	 public JTextArea getTextAreaCountTroop8()
	 {
		 return countTroop8;
	 }
	 
	 public JButton getRemoveTroop1()
	 {
		 return removeTroop1;
	 }
	 
	 public JButton getRemoveTroop2()
	 {
		 return removeTroop2;
	 }
	 
	 public JButton getRemoveTroop3()
	 {
		 return removeTroop3;
	 }
	 
	 public JButton getRemoveTroop4()
	 {
		 return removeTroop4;
	 }
	 
	 public JButton getRemoveTroop5()
	 {
		 return removeTroop5;
	 }
	 
	 public JButton getRemoveTroop6()
	 {
		 return removeTroop6;
	 }
	 
	 public JButton getRemoveTroop7()
	 {
		 return removeTroop7;
	 }
	 
	 public JButton getRemoveTroop8()
	 {
		 return removeTroop8;
	 }
}
