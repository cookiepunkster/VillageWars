package VGPanels;

import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;
import javax.swing.Timer;

import VGCustomClasses.VGClientUDP;

import java.util.concurrent.TimeUnit;

import VGSingletons.VGGameSingleton;
import VGSingletons.VGPlayerSingleton;
import VGSingletons.VGPropertiesSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import VGSingletons.VGPlayerSingleton;
import VGSingletons.VGPropertiesSingleton;

public class VGFieldPanel extends JPanel {
	
	private JPanel buttonsPanel;
	private JPanel topPanel;
	private JButton[][] buttons = new JButton[10][10];
	private int[][] buttonsValue = new int[10][10];
	private int[][] visitedValue = new int[10][10];
	private JLabel labelTimer = new JLabel("Timer: ");
	private JLabel labelHit = new JLabel("Hit Count: ");
	private JTextArea timerTextArea = new JTextArea(1,10);
	private JTextArea hitTextArea = new JTextArea(1,10);
	int flag = 0;
	int randXT;
	int randYT;
	int timer;
	int seconds = 60;
	int minute;
	int second;
	 VGClientUDP clientUDP = new VGClientUDP();
	 

	public VGFieldPanel() {
		
		setLayout(new BorderLayout());
		
		topPanel = new JPanel();
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(10,10,1,1));
		topPanel.setBackground(Color.GRAY);
		buttonsPanel.setBackground(Color.GREEN);
		topPanel.setPreferredSize(new Dimension(970, 35));  //width, height 
		buttonsPanel.setPreferredSize(new Dimension(950, 480));
		addTimer();
		add(topPanel, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.CENTER);
		
		setBackground(Color.GREEN);
		setButtons();
		init_visited();
		
	}
	
	public void addTimer()
	{
		
		add(topPanel);
		topPanel.add(labelTimer);
		timerTextArea.setText("1:00");
		topPanel.add(timerTextArea);
		topPanel.add(labelHit);
		topPanel.add(hitTextArea);
		

	}
	
	public void init_visited()
	{
		for(int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				visitedValue[i][j] = 10;
			}
		}
	}
	
	public void timerStart(){
		Timer timer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 int troop[] = VGPlayerSingleton.getInstance().getPlayerTroops();
				 Random random = new Random();
				 int randXA,randYA;
				
				if(seconds==0){
				((Timer)e.getSource()).stop();
					System.out.println("timer stop!");
				/*	try {
						clientUDP.sendHitCount();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/
					//printVisitedArray();
				}else{
				seconds--;
				minute = (int) (TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60));
				second = (int) (TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60));
		
				timerTextArea.setText(minute + ":" + second);
				
				//Clear all panels.
				for(int i=0;i<10;i++)
					for(int j=0;j<10;j++)
						buttons[i][j].setBackground(null);
				 
					  for(int m=0; m<8; m++)
					  {
						  for(int n=0; n< troop[m]; n++)
						  {
							  randXA = random.nextInt(10) + 0;
							  randYA = random.nextInt(10) + 0;
							  
						
							  
							   attackTroop(m, n, randXA, randYA); 
							  //  VGGameSingleton.getInstance().printDefenderHealth();
							  //buttons[randXB][randYB].setBackground(new JButton().getBackground());
						  }
						
					  }
			
				}
			}
			
		}
		
		);
		
		timer.start();
		//VGGameSingleton.getInstance().AttackerHealth();
	}
	
	private void setButtons() {
		
		for(int i = 0 ; i<10 ; i+=1)
		{
			for(int j = 0 ; j<10 ; j+=1)
			{
				
				buttons[i][j] = new JButton();
				buttons[i][j].setEnabled(false);
				buttonsValue[i][j] = 10;
				buttonsPanel.add(buttons[i][j]);
				
			}
			
		}
		
	}
	
   public void position(){
	  Random random = new Random();
	  int randXB,randYB;
	  int count[] = VGPlayerSingleton.getInstance().getPlayerTroops();
	  
	  for(int i = 0; i<8; i++)
	  {
		  System.out.print(count[i]+"_");
	  }
	  
	  if(flag==0){  //one time to position the tower hall
			
			
		 	 randXT = random.nextInt(10) + 0;
			 randYT = random.nextInt(10) + 0;
			
		    ImageIcon tower = new ImageIcon(getClass().getResource("tower.png"));  //insert picture in a button
			buttons[randXT][randYT].setIcon(tower);
			buttons[randXT][randYT].setEnabled(true);
			buttonsValue[randXT][randYT] = 8;
			flag++;
		 
	  System.out.println("");
			  for(int m=0; m<8; m++)
			  {
				  for(int n=0; n< count[m]; n++)
				  {
					  randXB = random.nextInt(10) + 0;
					  randYB = random.nextInt(10) + 0;
					  
					/*  if(randXB==randYB){
						  while(randXB==randYB){
							  randXB = random.nextInt(10) + 0;
							  randYB = random.nextInt(10) + 0;
						  }
					  }*/
					  
					 if(buttonsValue[randXB][randYB] == 10 ){
						  randXB = random.nextInt(10) + 0;
						  randYB = random.nextInt(10) + 0;
						 positionTroop(m, randXB, randYB); 
						 VGGameSingleton.getInstance().defenderTroopHealth(m, randXB, randYB);
					 } 
					
				  }
				
			  }
			  
				flag++;
	     } //end to flag
	  VGGameSingleton.getInstance().setTroopInPosition(buttonsValue);
	  
	 
		try {
			clientUDP.cRun();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	  
   }
   
   
   public void printPositionArray()
   {
	   for(int i=0; i<10; i++)
	   {
		 for(int j=0; j<10; j++)
		 {
			  System.out.print(buttonsValue[i][j]);
		 }
		 System.out.println();
	   }
   }
   
	public void attack() {
		VGGameSingleton.getInstance().attackerHealth();
		timerStart();
		
		
			try {
				clientUDP.cAttack();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void attackTheDefender()
	{
		VGGameSingleton.getInstance().attackerHealth();
		timerStart();
	}
	
	public void reset()
	{
		for(int i = 0 ; i<10 ; i+=1)
		{
			for(int j = 0 ; j<10 ; j+=1)
			{
				
				 buttons[i][j].setIcon(null);
				 buttons[i][j].setEnabled(false);
			}
			
		} 
		
		buttons[randXT][randYT].setIcon(null);
		buttons[randXT][randYT].setEnabled(false);
	   flag = 0;
	   
	  // VGGameSingleton.getInstance().resetTroopInPosition();
	   VGPlayerSingleton.getInstance().resetGoldCount();
	   VGPropertiesSingleton.getInstance().getStatsPanel().updateGoldCount();
	   
	}
	
	
	public void resetField()
	{
		for(int i = 0 ; i<10 ; i++)
		{
			for(int j = 0 ; j<10 ; j++)
			{
				
				 buttons[i][j].setIcon(null);
				 buttons[i][j].setEnabled(false);
			}
			
		} 
		
		//buttons[randXT][randYT].setIcon(null);
		//buttons[randXT][randYT].setEnabled(false);
	   //flag = 0;
	   
	    VGGameSingleton.getInstance().resetTroopInPosition();
	}
	
	
	public void positionTroop(int monster, int x, int y)
	{
	    ImageIcon troop = new ImageIcon(getClass().getResource(monster+".png"));  //insert picture in a button
				buttons[x][y].setIcon(troop);
				buttons[x][y].setEnabled(true);
				buttonsValue[x][y] = monster;
	}
	
	public void showDefenderTroop()
	{
				
				int[][] defenderTroopInPosition = new int[10][10];
				defenderTroopInPosition = VGGameSingleton.getInstance().getTroopInPosition();
				
				for(int x=0; x<10; x++){
					for(int y=0; y<10; y++){
						if(defenderTroopInPosition[x][y] == 0)
						{
							  ImageIcon troop = new ImageIcon(getClass().getResource(0+".png"));  //insert picture in a button
								buttons[x][y].setIcon(troop);
								buttons[x][y].setEnabled(true);
						}
						
						else if(defenderTroopInPosition[x][y] == 1)
						{
							  ImageIcon troop = new ImageIcon(getClass().getResource(1+".png"));  //insert picture in a button
								buttons[x][y].setIcon(troop);
								buttons[x][y].setEnabled(true);
						}
						
						else if(defenderTroopInPosition[x][y] == 2)
						{
							  ImageIcon troop = new ImageIcon(getClass().getResource(2+".png"));  //insert picture in a button
								buttons[x][y].setIcon(troop);
								buttons[x][y].setEnabled(true);
						}
						
						else if(defenderTroopInPosition[x][y] == 3)
						{
							  ImageIcon troop = new ImageIcon(getClass().getResource(3+".png"));  //insert picture in a button
								buttons[x][y].setIcon(troop);
								buttons[x][y].setEnabled(true);
						}
						
						else if(defenderTroopInPosition[x][y] == 4)
						{
							  ImageIcon troop = new ImageIcon(getClass().getResource(4+".png"));  //insert picture in a button
								buttons[x][y].setIcon(troop);
								buttons[x][y].setEnabled(true);
						}
						
						else if(defenderTroopInPosition[x][y] == 5)
						{
							  ImageIcon troop = new ImageIcon(getClass().getResource(5+".png"));  //insert picture in a button
								buttons[x][y].setIcon(troop);
								buttons[x][y].setEnabled(true);
						}
						

						else if(defenderTroopInPosition[x][y] == 6)
						{
							  ImageIcon troop = new ImageIcon(getClass().getResource(6+".png"));  //insert picture in a button
								buttons[x][y].setIcon(troop);
								buttons[x][y].setEnabled(true);
						}
						
						else if(defenderTroopInPosition[x][y] == 7)
						{
							  ImageIcon troop = new ImageIcon(getClass().getResource(7+".png"));  //insert picture in a button
								buttons[x][y].setIcon(troop);
								buttons[x][y].setEnabled(true);
						}
						
						else if(defenderTroopInPosition[x][y] == 8)
						{
							  ImageIcon troop = new ImageIcon(getClass().getResource("tower.png"));  //insert picture in a button
								buttons[x][y].setIcon(troop);
								buttons[x][y].setEnabled(true);
						}
						
						
						
					}
				}
	}
	
	public void attackTroop(int troop, int counter, int x, int y)
	{
		switch(troop){
	    case 0: buttons[x][y].setBackground(Color.YELLOW);
	    		updateStates(troop, counter, buttonsValue[x][y], x, y);
	  			break;
	    case 1: buttons[x][y].setBackground(Color.PINK);
			    updateStates(troop, counter, buttonsValue[x][y], x, y);
	    		break;
	    case 2: buttons[x][y].setBackground(Color.ORANGE);
	    		updateStates(troop, counter, buttonsValue[x][y], x, y);
	    		break;
	    case 3: buttons[x][y].setBackground(Color.WHITE);
	    		updateStates(troop, counter, buttonsValue[x][y], x, y);
	       		break;
	    case 4: buttons[x][y].setBackground(Color.BLUE);
	    		updateStates(troop, counter, buttonsValue[x][y], x, y);
	    		break;
	    case 5: buttons[x][y].setBackground(Color.BLACK);
	    		updateStates(troop, counter, buttonsValue[x][y], x, y);
	    		break;
	    case 6: buttons[x][y].setBackground(Color.MAGENTA);
	    		updateStates(troop, counter, buttonsValue[x][y], x, y);
	    		break;
	    case 7: buttons[x][y].setBackground(Color.RED);
	    		updateStates(troop, counter, buttonsValue[x][y], x, y);
	    		break;
        }

	}
	
	public void updateStates(int AttackerTroop, int counter, int DefenderTroop, int x, int y)
	{
		int attackerTroopValue;
		int defenderTroopValue;
		int currentAttackerTroopHealth;
		int currentDefenderTroopHealth;
	
		currentAttackerTroopHealth = VGGameSingleton.getInstance().getAttackerTroopHealth(AttackerTroop,counter); 
		currentDefenderTroopHealth = VGGameSingleton.getInstance().getDefenderTroopHealth(x,y);
		
		if(DefenderTroop == 8)  //It means that this is a tower
		{
			VGPlayerSingleton.getInstance().addHitCount();
			hitTextArea.setText(Integer.toString(VGPlayerSingleton.getInstance().getHitCount()));
		}
				else{
			   
				//System.out.println("currentDefenderTroopHealth: "+currentDefenderTroopHealth);
				if(currentDefenderTroopHealth != 0)
				{
					attackerTroopValue = VGGameSingleton.getInstance().getTroopValues(AttackerTroop);
					defenderTroopValue = VGGameSingleton.getInstance().getTroopValues(DefenderTroop);
				    if(attackerTroopValue < defenderTroopValue)
							{		
									VGGameSingleton.getInstance().minusDefenderTroopHealth(currentAttackerTroopHealth, currentDefenderTroopHealth, x,y);
									VGGameSingleton.getInstance().updateAttackerHealth(AttackerTroop, counter, 0);
									VGPlayerSingleton.getInstance().addGoldCoins(attackerTroopValue);
									VGPropertiesSingleton.getInstance().getStatsPanel().updateGoldCount();
							}
							
					 else if(attackerTroopValue > defenderTroopValue)
							{
								    VGGameSingleton.getInstance().minusAttackerTroopHealth(currentAttackerTroopHealth, currentDefenderTroopHealth, AttackerTroop, counter);
								    VGGameSingleton.getInstance().updateDefenderHealth(x, y, 0);
								    VGPlayerSingleton.getInstance().addGoldCoins(defenderTroopValue);
								    VGPropertiesSingleton.getInstance().getStatsPanel().updateGoldCount();
							}
							
							else{    //attackerTroopValue == DefenderTroopValue
								VGGameSingleton.getInstance().updateAttackerHealth(AttackerTroop, counter, 0);
								 VGGameSingleton.getInstance().updateDefenderHealth(x, y, 0);
								 VGPlayerSingleton.getInstance().addGoldCoins(attackerTroopValue);
								 VGPropertiesSingleton.getInstance().getStatsPanel().updateGoldCount();
							} 
				}
		}
	}
	
	
	
	public Dimension getPreferredSize() {
		
		return new Dimension(VGPropertiesSingleton.getInstance().getFieldPanelDimension().width,VGPropertiesSingleton.getInstance().getFieldPanelDimension().height);
	}

}
