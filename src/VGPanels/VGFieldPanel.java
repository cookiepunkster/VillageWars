package VGPanels;

import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;

import VGSingletons.VGGameSingleton;
import VGSingletons.VGPlayerSingleton;
import VGSingletons.VGPropertiesSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import VGSingletons.VGPlayerSingleton;
import VGSingletons.VGPropertiesSingleton;

public class VGFieldPanel extends JPanel {
	//for trial lang
	String sentence;
	String monsterCount;
	int[] monsterPerCount = new int[8];
	String playerLevel;
	String playerCoin;
	String playerName;
	int troopInPosition[][] = new int[10][10];
	//
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
	int seconds = 300;
	int minute;
	int second;
    

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
		timerTextArea.setText("5:00");
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
					
				}else{
				seconds--;
				minute = (int) (TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60));
				second = (int) (TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60));
		
				timerTextArea.setText(minute + ":" + second);
				 
					  for(int m=0; m<8; m++)
					  {
						  for(int n=0; n< troop[m]; n++)
						  {
							  randXA = random.nextInt(9) + 1;
							  randYA = random.nextInt(9) + 0;
							  
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
					  randXB = random.nextInt(9) + 0;
					  randYB = random.nextInt(9) + 0;
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
	  
	    VGGameSingleton.getInstance().setButtonsValue(buttonsValue);
	   // printPositionValues();
	 
   }
 
 /*  public void printPositionValues()   //THIS IS JUST A BACKUP
   {
		 monsterPerCount = VGPlayerSingleton.getInstance().getPlayerTroops();
	     playerName = VGPlayerSingleton.getInstance().getPlayerName();
		 playerLevel = Integer.toString(VGPlayerSingleton.getInstance().getLevel());
		// monsterCount = Arrays.toString(monsterPerCount);
		 playerCoin = Integer.toString(VGPlayerSingleton.getInstance().getGoldCoins());
		 troopInPosition = VGGameSingleton.getInstance().getTroopInPosition();
		 
		 String troopData="";
		 for(int m=0; m<8; m++)
		 {
			 troopData = troopData + monsterPerCount[m]+",";
		 }
		 
		// System.out.println("TROOP DATA"+troopData);
		//PASS THE DATA TO THE SERVER - THE SEQUENCE IS THIS - pName, levelNo, int monsterCount, int goldCount, int[][] defenderTroop
		 sentence = playerName+"_"+playerLevel+"_"+troopData+"_"+playerCoin;
		 
		 String sentence1 = "_";
		 String separator = "";
		 
		 for(int i=0; i<10; i++){
			// sentence1 = sentence1+"/";
			 for(int j=0; j<10; j++){
				   sentence1 = sentence1 +(Integer.toString(troopInPosition[i][j]))+",";
			 }
			 sentence1 = sentence1+"/";
		 }
		 
		 sentence = sentence + sentence1;
		 System.out.println("Here's the data to be passed:"+sentence);
		 
		 separateTheValues(sentence);
   }
   
   public void separateTheValues(String sentence1)
   {
	   String pName = "";
	   int levelNumber;
	   int playerGold;
	   int[] count = new int[8];
	   int[][] positionValue = new int[10][10];
	   String countArray;
	   System.out.println("Separate the values!");
	   String[] receivedData = sentence1.split("_");
	   String[] dataFromClient = new String[receivedData.length];
	   for(int i=0; i< receivedData.length; i++)
	   {
		      String data =  receivedData[i];
		      dataFromClient[i] = data;
	   }
	   
	   
	   for(String i : dataFromClient)
	   {
		   System.out.println(i);
	   }
	   
	   System.out.println("Convert each one");
	   System.out.println();

	   pName = dataFromClient[0];
	   levelNumber = Integer.parseInt(dataFromClient[1]);
	   
	   String[] troops = dataFromClient[2].split(",");
	   int[] troopCount = new int[troops.length];
	   for(int i=0; i< troops.length; i++)
	   {
		    int perMonster = Integer.parseInt(troops[i]);
		    troopCount[i] = perMonster;
	   }
		   
		   
	   playerGold = Integer.parseInt(dataFromClient[3]);
	   
	   String[] positionPer10 = dataFromClient[4].split("/");
	   String[] positionArray = new String[positionPer10.length];
	   for(int n=0; n< positionPer10.length; n++)
	   {
		   String rowArray = positionPer10[n];
		    positionArray[n] = rowArray;
	   }

	   System.out.println("Player name: "+pName);

	   System.out.println("Level:"+levelNumber);
	   
	   for(int i : troopCount)
	   {
		   System.out.print(i);
	   }
	   
	   System.out.println();
	   System.out.println("Player's Gold: "+playerGold);
	   
	
	   //SEPARATE them
	   for(int i=0; i<positionArray.length; i++)
	   {
		   String[] perArray = positionArray[i].split(",");
		   int[][] valueInArray = new int[positionArray.length][perArray.length];
		 
		   for(int j=0; j<perArray.length; j++ )
		   {
			   positionValue[i][j] = Integer.parseInt(perArray[j]);
		   }
	   }
	   
	   System.out.println("PRINT POSITION VALUES");
	   for(int i=0; i<10; i++)
	   {
		   for(int j=0; j<10; j++)
		   {
			   System.out.print(positionValue[i][j]+" ");
		   }
		   System.out.println();
	   }
	   
   } */
   
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
	   VGPlayerSingleton.getInstance().resetGoldCount();
	   VGPropertiesSingleton.getInstance().getStatsPanel().updateGoldCount();
	   
	}
	
	public void positionTroop(int monster, int x, int y)
	{
	    ImageIcon troop = new ImageIcon(getClass().getResource(monster+".png"));  //insert picture in a button
				buttons[x][y].setIcon(troop);
				buttons[x][y].setEnabled(true);
				buttonsValue[x][y] = monster;
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
