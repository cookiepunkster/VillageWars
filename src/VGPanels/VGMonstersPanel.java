package VGPanels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import VGCustomClasses.*;
import VGSingletons.VGPlayerSingleton;
import VGSingletons.VGPropertiesSingleton;

import java.util.*;

public class VGMonstersPanel extends JPanel implements ActionListener {
	 JTextField[] countTroop = new JTextField[9];
	 JButton[] troop = new JButton[8];
	 JButton[] removeButton = new JButton[8];
	
	 JTextArea goldTextArea = new JTextArea();
	 JButton reset;
	 VGMonsterButton attackButton;
	 JButton position = new JButton("Position");
	 int[] count = new int[8];
	 VGStatsPanel vgStatsObject = new VGStatsPanel();

	public VGMonstersPanel() {
		
		setLayout(new BorderLayout());
		setBackground(Color.black);
	    setLayout(new GridLayout(0,9,1,1));
	   // initialize(name, port);
		setUpCountTextArea();
		setUpButtons();
		setUpRemoveButtons();
	
		
		
	}
	
	private void setUpCountTextArea(){
		int i;
	
		
		for(i =0; i<8; i++)
		{
			countTroop[i] = new JTextField();
			countTroop[i].setEditable(false);
			countTroop[i].setSelectionColor(Color.black);
			
		}
		
		   
			for(i =0; i<8; i++)
			{
			    add(countTroop[i]);
			}
			
		 position.addActionListener(this);
		 add(position);
			
		
			
	}
	
private void setUpRemoveButtons(){
		
		int y;
		for(y= 0; y<8; y++)
		{
			removeButton[y] = new JButton("X");
			removeButton[y].addActionListener(this);
			add(removeButton[y]);
		}
		
	    reset = new JButton("Reset");
	    reset.addActionListener(this);
	    add(reset);
	
	}
	
	private void setUpButtons() {
		int i=0;
		    ImageIcon barb = new ImageIcon(getClass().getResource("barb.png"));  //insert picture in a button
		    troop[0] = new JButton(barb);
		    troop[0].setBackground(Color.YELLOW);
		    
		    ImageIcon archer = new ImageIcon(getClass().getResource("archer.png"));
		    troop[1] = new JButton(archer);
		    troop[1].setBackground(Color.PINK);
		    
		    ImageIcon giant= new ImageIcon(getClass().getResource("giant.png"));
		    troop[2] = new JButton(giant);
		    troop[2].setBackground(Color.ORANGE);
		    
		    ImageIcon wallbreaker= new ImageIcon(getClass().getResource("wallbreaker.png"));
		    troop[3] = new JButton(wallbreaker);
		    troop[3].setBackground(Color.WHITE);
		    
		    ImageIcon wizard= new ImageIcon(getClass().getResource("wizard.png"));
		    troop[4] = new JButton(wizard);
		    troop[4].setBackground(Color.BLUE);
		    
		    ImageIcon hogrider= new ImageIcon(getClass().getResource("hogrider.png"));
		    troop[5] = new JButton(hogrider);
		    troop[5].setBackground(Color.BLACK);
		    
		    ImageIcon dragon= new ImageIcon(getClass().getResource("dragon.png"));
		    troop[6] = new JButton(dragon);
		    troop[6].setBackground(Color.MAGENTA);
		    
		    ImageIcon wall= new ImageIcon(getClass().getResource("wall.png"));
		    troop[7] = new JButton(wall);
		    troop[7].setBackground(Color.RED);

			for(i=0; i<8; i++)
			{
				troop[i].addActionListener(this);
				add(troop[i]);
			}
		
			
			 attackButton = new VGMonsterButton(12, "Attack");
				attackButton.addActionListener(this);
				add(attackButton);
	}
	
	public Dimension getPreferredSize() {
		
		return new Dimension(VGPropertiesSingleton.getInstance().getMonstersPanelDimension().width,VGPropertiesSingleton.getInstance().getMonstersPanelDimension().height);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		
		for(int i = 0; i<8; i++){
			
			if(e.getSource() == troop[i]){
				
				VGPlayerSingleton.getInstance().minusGoldCoins(VGPropertiesSingleton.getInstance().getMonsterValues(i));
			  
				if(VGPlayerSingleton.getInstance().getGoldCoins() < 0){
					VGPlayerSingleton.getInstance().addGoldCoins(VGPropertiesSingleton.getInstance().getMonsterValues(i));
					JOptionPane.showMessageDialog(vgStatsObject,  "Not enough gold!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				
				else{
					if(count[i] < VGPropertiesSingleton.getInstance().getmonsterMaxBuys(i)){
						count[i]++; 
					}
					else{
						VGPlayerSingleton.getInstance().addGoldCoins(VGPropertiesSingleton.getInstance().getMonsterValues(i));
						JOptionPane.showMessageDialog(vgStatsObject, "You're maximum allowable number of " +VGPropertiesSingleton.getInstance().getMonsterName(i)+ " is " +VGPropertiesSingleton.getInstance().getmonsterMaxBuys(i), "Error", JOptionPane.WARNING_MESSAGE);
					}
			  }
				countTroop[i].setText(Integer.toString(count[i]));
				VGPropertiesSingleton.getInstance().getStatsPanel().updateGoldCount();
			}
			
			if(e.getSource() == removeButton[i]){
				if(count[i] != 0){
					count[i]--;
					VGPlayerSingleton.getInstance().addGoldCoins(VGPropertiesSingleton.getInstance().getMonsterValues(i));
					VGPropertiesSingleton.getInstance().getStatsPanel().updateGoldCount();
					countTroop[i].setText(Integer.toString(count[i]));
				
				}
			}
			
		}
		   
		   if(e.getSource() == position)
		   {
			
			   VGPlayerSingleton.getInstance().setPlayerTroops(count);
			   VGPropertiesSingleton.getInstance().getFieldPanel().position();
			 /*  VGClientUDP client = new VGClientUDP();
			   try {
				client.cRun();
			   } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			   } */
			   
		   }
		   
		   if(e.getSource() == attackButton)
	         {	
			     VGPlayerSingleton.getInstance().setPlayerTroops(count);
			     VGPropertiesSingleton.getInstance().getFieldPanel().attack();
	         } 
			
		   if(e.getSource() == reset)
	         {	
			    VGPropertiesSingleton.getInstance().getFieldPanel().reset();

				for(int j =0; j<8; j++)
				{
					count[j] = 0;
					countTroop[j].setText("");
					
				}
				
				
				
	         } 
		   		
		   
		
	}
	
}
