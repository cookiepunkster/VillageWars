package VGCustomClasses;

import java.io.*; 
import java.net.*;
import java.util.Arrays; 
import VGSingletons.*;

public class VGClientUDP {   
	String data;
	String data1;
	String data2;
	String monsterCount;
	int[] monsterPerCount = new int[8];
	String playerLevel;
	String playerCoin;
	String playerName;
	String playerIPAddress;
	int hitCount;
	int troopInPosition[][] = new int[10][10];
	
//public void cRun(String pName, String ipAddress,int levelNo, int[] monsterCountReceived, int goldCount) 
	public void cRun() throws IOException{
		int y;
		monsterPerCount = VGPlayerSingleton.getInstance().getPlayerTroops();
		String countPerTroop;
		 System.out.println("FROM CLIENT");
		 DatagramSocket clientSocket = new DatagramSocket(9876);   
		 InetAddress IPAddress = InetAddress.getByName(VGPlayerSingleton.getInstance().getIPaddress());  
		 InetAddress playerIPAddress2 = InetAddress.getByName("192.168.10.12");
		 byte[] sendData = new byte[1024];       
		 byte[] receiveData = new byte[1024];   
	     data = getTheDataFromTheClient(playerIPAddress2.toString());
	     sendData = data.getBytes();       
		 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);       
		 clientSocket.send(sendPacket);       
		 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);     
		 clientSocket.receive(receivePacket);       
	     String modifiedSentence = new String(receivePacket.getData());      
	     System.out.println("FROM SERVER:" + modifiedSentence);              
	     clientSocket.close();    
	    
	}
	public void cAttack() throws IOException{
		int y;
		String countPerTroop;
		 System.out.println("FROM CLIENT:(Attack)");
		 DatagramSocket clientSocket = new DatagramSocket();   
		 InetAddress IPAddress = InetAddress.getByName(VGPlayerSingleton.getInstance().getIPaddress());  
		 byte[] sendData = new byte[1024];       
		 byte[] receiveData = new byte[1024];   
	     data1 = "attack"+"_"+VGPlayerSingleton.getInstance().getIPaddress();
	     sendData = data1.getBytes();       
		 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);       
		 clientSocket.send(sendPacket);       
		
		 
		 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);     
		 clientSocket.receive(receivePacket);       
	     String modifiedSentence = new String(receivePacket.getData()); 
	     parseThedefenderTroopInPosition(modifiedSentence);
	     System.out.println("FROM SERVER:(Attack)" + modifiedSentence);              
	     clientSocket.close(); 
	}
	
	public void parseThedefenderTroopInPosition(String receivedTroopInPosition)
	{
		 int[][] defenderPositionValue = new int[10][10];
		 
		   String[] positionPer10 = receivedTroopInPosition.split("/");
	  	   String[] positionArray = new String[positionPer10.length];
	  	   for(int n=0; n< positionPer10.length; n++)
	  	   {
	  		   String rowArray = positionPer10[n];
	  		    positionArray[n] = rowArray;
	  	   }
	  	   
	  	   
	  	 //SEPARATE them
	  	   for(int i=0; i<positionArray.length; i++)
	  	   {
	  		   String[] perArray = positionArray[i].split(",");
	  		   int[][] valueInArray = new int[positionArray.length][perArray.length];
	  		 
	  		   for(int j=0; j<perArray.length-1; j++ )
	  		   {
	  			   defenderPositionValue[i][j] = Integer.parseInt(perArray[j]);
	  		   }
	  	   }
	  	   
	  	   
	  	   for(int i=0; i<10; i++){
	  		   for(int j=0; j<10; j++){
	  			 System.out.print(defenderPositionValue[i][j]+" ");
	  		   }
	  		  System.out.println();
	  	   }
	  	   
	  	   VGPropertiesSingleton.getInstance().getFieldPanel().resetField();
	  	   VGGameSingleton.getInstance().setTroopInPosition(defenderPositionValue);
	  	  VGPropertiesSingleton.getInstance().getFieldPanel().showDefenderTroop();
	  	 VGPropertiesSingleton.getInstance().getFieldPanel().attackTheDefender();
	
	}
	
	
	public void sendHitCount() throws IOException{
		
		hitCount = VGPlayerSingleton.getInstance().getHitCount();
		String attackerName = VGPlayerSingleton.getInstance().getPlayerName();
		 System.out.println("FROM CLIENT: (HIT COUNT)");
		 DatagramSocket clientSocket = new DatagramSocket();   
		 InetAddress IPAddress = InetAddress.getByName(VGPlayerSingleton.getInstance().getIPaddress());  
		 byte[] sendData = new byte[1024];       
		 byte[] receiveData = new byte[1024];   
	     data2 = "sendHitCount"+"_"+attackerName+"_"+Integer.toString(VGPlayerSingleton.getInstance().getHitCount());
	     sendData = data2.getBytes();       
		 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);       
		 clientSocket.send(sendPacket);       
		 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);     
		 clientSocket.receive(receivePacket);       
	     String modifiedSentence = new String(receivePacket.getData());      
	     System.out.println("FROM SERVER(hit count):" + modifiedSentence);              
	     clientSocket.close(); 
	}
	
	
	
	public String getTheDataFromTheClient(String clientIPAddress)
	{
		String sentence = "";
		 monsterPerCount = VGPlayerSingleton.getInstance().getPlayerTroops();
	     playerName = VGPlayerSingleton.getInstance().getPlayerName();
	     playerIPAddress = clientIPAddress;
		 playerLevel = Integer.toString(VGPlayerSingleton.getInstance().getLevel());
		// monsterCount = Arrays.toString(monsterPerCount);
		 playerCoin = Integer.toString(VGPlayerSingleton.getInstance().getGoldCoins());
		 troopInPosition = VGGameSingleton.getInstance().getTroopInPosition();
		 
		 String troopData="";
		 for(int m=0; m<8; m++)
		 {
			 troopData = troopData + monsterPerCount[m]+",";
		 }
		 
		//PASS THE DATA TO THE SERVER - THE SEQUENCE IS THIS - pName, levelNo, int monsterCount, int goldCount, int[][] defenderTroop
		 sentence = "position"+"_"+playerName+"_"+playerIPAddress+"_"+playerLevel+"_"+troopData+"_"+playerCoin;
		 
		 String sentence1 = "_";
		 String separator = "";
		 
		 for(int i=0; i<10; i++){
			// sentence1 = sentence1+"/";
			 for(int j=0; j<10; j++){
				
				   if(j==9)
				   {
					   sentence1 = sentence1 +(Integer.toString(troopInPosition[i][j]));
				   }
				   
				   else{
					   sentence1 = sentence1 +(Integer.toString(troopInPosition[i][j]))+",";
				   }
			 }
			 sentence1 = sentence1+"/";
		 }
		 
		 sentence = sentence + sentence1;
		 
		 return sentence;
		 
	}
 } 