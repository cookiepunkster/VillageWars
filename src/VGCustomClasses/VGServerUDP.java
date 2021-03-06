package VGCustomClasses;
import java.awt.BorderLayout;
import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import VGFrames.VGGameFrame;
import java.io.*; import java.net.*; 

public class VGServerUDP {   
	static ArrayList<Client> clientList = new ArrayList<Client>();
	static ArrayList<IPAddress> IPList = new ArrayList<IPAddress>();
	static ArrayList<HitCount> hitCountList = new ArrayList<HitCount>();
	static int playerMove;
	static String attackerIPAddress;
	static String playerIPAddress;
	static int numberOfClients = 0;
	static int attackerHit = 0;
	
	public static void main(String args[]) throws IOException {
		     
		 byte[] receiveData = new byte[1024];             
		 byte[] sendData = new byte[1024];  


		 byte[] receiveData1 = new byte[1024];             
		 byte[] sendData1 = new byte[1024];     
		 
		 int flag = 0, flag1 = 0;
		// String move;
		 int n = 10;

		 
		 DatagramSocket serverSocket = new DatagramSocket(9876); 
		 
				 while(true){                   
					 
						 DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);                   
						 serverSocket.receive(receivePacket);             
					
						 String receivedData = new String( receivePacket.getData()); 
						 parseTheReceivedData(receivedData);
						 
						 InetAddress IPAddress = receivePacket.getAddress();    
						 playerIPAddress = receivePacket.getAddress().toString().replace("/", "");
						 
						 System.out.println("IP: "+playerIPAddress);
 IPList.add(new IPAddress(playerIPAddress));
						 
						 if(IPList.size() > 0){
							 openNewFrame();
						 
						 }
						 int port = receivePacket.getPort();                   
						 
						 //openNewFrame("Jolly");
						 
						 
						 if(playerMove == 1){
							 String capitalizedSentence = receivedData.toUpperCase();                   
							 sendData = capitalizedSentence.getBytes();                   
							 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);                  
			
							 serverSocket.send(sendPacket); 	
						 }
						 
						 else if(playerMove == 2){   //playerMove == 2
							 attackerIPAddress = playerIPAddress;
							 System.out.println("Attacker IP address:"+attackerIPAddress);
						
							 //find the attacker at client arraylist
							 
							 int attackerIndex = 0;
							 int enemyIndex = 0;
							 
							 for(int i = 0 ; i<clientList.size() ; i+=1)
							 {
								 
								 if(clientList.get(i).getIPAddress().equals(attackerIPAddress))
								 {
									 attackerIndex = i;
									 break;
								 }
								 
							 }
							  int[] attackerTroop = new int[8];
							  int[][] defenderTroopInPosition = new int[10][10];
							
							 attackerTroop = clientList.get(attackerIndex).getClientTroopCount();
							
							 String troopData="";
							   
							 for(int m=0; m<8; m++)
							 {
								 troopData = troopData + attackerTroop[m]+",";
							 }
							 
							 //find the one not the attacker
							 
							 while(true)
							 {
								
								enemyIndex = (new Random()).nextInt(clientList.size()) + 0;
								
								if(enemyIndex != attackerIndex)
									break;
										 
							 }
							 
							 byte[] defenderTroopPositionData = new byte[1024];
							 
							 System.out.println("Enemy: "+clientList.get(enemyIndex).getClientName());
							 
							 String sentence1 = "";
							 defenderTroopInPosition = clientList.get(enemyIndex).getPositionValue();
							 for(int i=0; i<10; i++){
								// sentence1 = sentence1+"/";
								 for(int j=0; j<10; j++){
									   sentence1 = sentence1 +(Integer.toString(defenderTroopInPosition[i][j]))+",";
								 }
								 sentence1 = sentence1+"/";
							 }
							 
							// sentence = sentence + sentence1;
							 
					
							 //send the troopInPosition of the defender data
							 InetAddress IPAddress2 = InetAddress.getByName(clientList.get(enemyIndex).getIPAddress());
							 defenderTroopPositionData = sentence1.getBytes();                   
							 DatagramPacket sendPacket1 = new DatagramPacket(defenderTroopPositionData,defenderTroopPositionData.length,IPAddress, port);                  
							 serverSocket.send(sendPacket1); 
							 //playerMove = 0;
							 
						 }
						 
						 else if(playerMove == 3){  //playerMove == 3
							 byte[] winnerName = new byte[1024];
							 String clientName = "";
							 int max = 0;
							 if(attackerHit == numberOfClients)
							 {
								 for(int i = 0 ; i<hitCountList.size() ; i+=1)
								 {
									 
									 if(hitCountList.get(i).getHitCount() > max)
									 {
										 max = hitCountList.get(i).getHitCount();
									 }
									 
								 }
								 
								 String maximum = Integer.toString(max);
								 
								 for(int i = 0 ; i<hitCountList.size() ; i+=1)
								 {
									 
									 if(hitCountList.get(i).getHitCount() == max)
									 {
										 clientName = hitCountList.get(i).getClientName();
									 }
									 
								 }
								 
								 
								 winnerName = clientName.getBytes();                   
								 DatagramPacket sendPacket2 = new DatagramPacket(winnerName,winnerName.length,IPAddress, port);                  
								 serverSocket.send(sendPacket2); 
								 
								 
								 System.out.println("Winner name: "+clientName);
							 }
						
							// max = 0;
							 playerMove = 0;
						 }
						 
						   
					  	 else{  //playerMove = 0   //load
							 String loadTrial = "Load Tester Data Received";
							 System.out.println("Load tester IP Address"+playerIPAddress);
							 byte[] loadTesterData = new byte[1024];
							 loadTesterData = loadTrial.getBytes(); 
							 DatagramPacket sendPacket3 = new DatagramPacket(loadTesterData,loadTesterData.length,IPAddress, port);                  
							 serverSocket.send(sendPacket3); 
							
						 }
					
				} 

		
		 }

	

    public static void openNewFrame()
    {
   	
         JFrame mainFrame = new JFrame();
   	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         mainFrame.setSize(200,100);
         mainFrame.setLayout(new BorderLayout());
         mainFrame.setVisible(true);
         mainFrame.setResizable( false );
         mainFrame.setLocationRelativeTo(null);
         JPanel panel1 = new JPanel();
     	   JTextArea textArea = new JTextArea();
     	   panel1.add(textArea);
     	   mainFrame.add(panel1);
     	   
     	   for(int i=0; i< IPList.size(); i++)
     	   {
     		   String ip = IPList.get(i).getIPClient();
     		   textArea.append(ip+" ");
     	   }
         
         	
    }

     
     public static void parseTheReceivedData(String sentence)
     {
		  	   String pName = "";
		  	   int levelNumber;
		  	   int playerGold;
		  	   String move;
		  	   
		  	   int[] count = new int[8];
		  	   int[][] positionValue = new int[10][10];
		  	   String countArray;
		  	   String clientIP;

		  	   String[] receivedData = sentence.split("_");
		  	   String[] dataFromClient = new String[receivedData.length];
		  	   for(int i=0; i< receivedData.length; i++)
		  	   {
		  		      String data =  receivedData[i];
		  		      dataFromClient[i] = data;
		  	   }
		 
		  	   
		  	   System.out.println("Data Received");
		  	   move = dataFromClient[0];
		  	   if(move.equals("position"))
		  	   {
		  		   		  playerMove = 1;
					  	   pName = dataFromClient[1];
					  	   clientIP = dataFromClient[2];
					  	   clientIP = clientIP.replace("/", "");
					  	   
					  	   levelNumber = Integer.parseInt(dataFromClient[3]);
					  	   
					  	   String[] troops = dataFromClient[4].split(",");
					  	   int[] troopCount = new int[troops.length];
					  	   for(int i=0; i< troops.length; i++)
					  	   {
					  		    int perMonster = Integer.parseInt(troops[i]);
					  		    troopCount[i] = perMonster;
					  	   }
					  		   
					  		
					  	   playerGold = Integer.parseInt(dataFromClient[5]);
					  	   
					  	   String[] positionPer10 = dataFromClient[6].split("/");
					  	   String[] positionArray = new String[positionPer10.length];
					  	   for(int n=0; n< positionPer10.length; n++)
					  	   {
					  		   String rowArray = positionPer10[n];
					  		    positionArray[n] = rowArray;
					  	   }
					
					  	   System.out.println("Player name: "+pName);
					  	   System.out.println("Client IP: "+clientIP);
					  	   
					  	   System.out.println("Level:"+levelNumber);
					  	   
					  	   for(int i : troopCount)
					  	   {
					  		   System.out.print(i);
					  	   }
					  	   
					  	   System.out.println();
					  	   System.out.println("Player's Gold: "+playerGold);
					  	   
					  	
					  	   //SEPARATE them
					  	   for(int i=0; i<positionArray.length-1; i++)
					  	   {
					  		   String[] perArray = positionArray[i].split(",");
					  		   int[][] valueInArray = new int[positionArray.length][perArray.length];
					  		 
					  		   for(int j=0; j<perArray.length-1; j++ )
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
					  	   
					  	 
					  	 clientList.add(new Client(pName, levelNumber, troopCount, playerGold, positionValue, sentence, clientIP));
					  	// setMove(move);
					  	 numberOfClients++;
		  	   }
		  	   else if(move.equals("attack")){
		  		    playerMove = 2;
		  		    playerIPAddress = dataFromClient[1];
		  		    setAttackerIPaddress(playerIPAddress);		 
		     	 }
		  	  
		  	   else if(move.equals("sendHitCount")){  // move is send hit count
		  		   String attackerName;
		  		    int hitCount = 0;
		  		 
		  		    playerMove = 3;
		  		    attackerName = dataFromClient[1];
		  		    System.out.println("attacker name: "+attackerName);
		  		    System.out.println("data: "+dataFromClient[2]);
		  		    hitCount = Integer.parseInt(dataFromClient[2]);
		  		    hitCountList.add(new HitCount(attackerName, hitCount));
		  		    attackerHit++;
		  	   }
		  	   
		  	 else{  //load_tester // load 
		  		   String sendPacket;
		  		   playerMove = 4;
		  		   sendPacket = dataFromClient[1];
		  	   }
		  	
     }
     
     public static void setAttackerIPaddress(String playerIPAddress)
     {
    	   attackerIPAddress = playerIPAddress;
     }
     


} 