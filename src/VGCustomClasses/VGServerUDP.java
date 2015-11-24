package VGCustomClasses;
import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

import VGFrames.VGGameFrame;
import java.io.*; import java.net.*; 

public class VGServerUDP {   
	static ArrayList<Client> clientList = new ArrayList<Client>();
	static int playerMove;
	static String attackerIPAddress;
	static String playerIPAddress;
	
	public static void main(String args[]) throws IOException {
		     
		 byte[] receiveData = new byte[1024];             
		 byte[] sendData = new byte[1024];  
		 byte[] attackerData = new byte[1024];

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
						 
						 int port = receivePacket.getPort();                   
						 
						 //openNewFrame("Jolly");
						 
						 
						 if(playerMove == 1){
							 String capitalizedSentence = receivedData.toUpperCase();                   
							 sendData = capitalizedSentence.getBytes();                   
							 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);                  
			
							 serverSocket.send(sendPacket); 	
						 }
						 
						 else{   //playerMove == 2
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
							  int[] clientTroop = new int[8];
							
							 clientTroop = clientList.get(attackerIndex).getClientTroopCount();
							 String troopData="";
							   
							 for(int m=0; m<8; m++)
							 {
								 troopData = troopData + clientTroop[m]+",";
							 }
							 
							 //find the one not the attacker
							 
							 while(true)
							 {
								
								enemyIndex = (new Random()).nextInt(clientList.size()) + 1;
								
								if(enemyIndex != attackerIndex)
									break;
										 
							 }
							 
							 //send data
							 InetAddress IPAddress2 = InetAddress.getByName(clientList.get(enemyIndex).getIPAddress());
							 attackerData = troopData.getBytes();                   
							 DatagramPacket sendPacket1 = new DatagramPacket(attackerData, attackerData.length,IPAddress2, port);                  
							 serverSocket.send(sendPacket1); 
							 playerMove = 0;
							 
						 }
					
				} 

		
		 }

	

     public static void openNewFrame(String name)
     {
    	
    	 VGGameFrame vgObject = new VGGameFrame(name, "port");
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
		  	 //  System.out.println("Separate the values!");
		  	   String[] receivedData = sentence.split("_");
		  	   String[] dataFromClient = new String[receivedData.length];
		  	   for(int i=0; i< receivedData.length; i++)
		  	   {
		  		      String data =  receivedData[i];
		  		      dataFromClient[i] = data;
		  	   }
		  	   
		  	   
		  	/*   for(String i : dataFromClient)
		  	   {
		  		   System.out.println(i);
		  	   } */
		  	   
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
					  	   for(int i=0; i<positionArray.length; i++)
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
					  	   
					  	 //String name, int levelNumber, int[] troopCount, int playerGold, int[][] positionValue
					  	 clientList.add(new Client(pName, levelNumber, troopCount, playerGold, positionValue, sentence, clientIP));
					  	// setMove(move);
		  	   }
		  	   else{  // move is attack
		  		    playerMove = 2;
		  		    playerIPAddress = dataFromClient[1];
		  		    setAttackerIPaddress(playerIPAddress);
		  		    
		  	   }
     }
     
     public static void setAttackerIPaddress(String playerIPAddress)
     {
    	   attackerIPAddress = playerIPAddress;
     }
     


} 