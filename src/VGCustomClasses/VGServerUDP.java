package VGCustomClasses;
import java.io.*; 
import java.net.*;
import java.util.ArrayList;

import VGFrames.VGGameFrame;
import java.io.*; import java.net.*; 

public class VGServerUDP {   
	static ArrayList<Client> clientList = new ArrayList<Client>();
	
	public static void main(String args[]) throws IOException {
		     
		 byte[] receiveData = new byte[1024];             
		 byte[] sendData = new byte[1024];     
		 
		 DatagramSocket serverSocket = new DatagramSocket(9876); 
		 
		 while(true){                   
			 
				 DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);                   
				 serverSocket.receive(receivePacket);             
			
				 String receivedData = new String( receivePacket.getData()); 
				 parseTheReceivedData(receivedData);
				 
				 InetAddress IPAddress = receivePacket.getAddress();                  
				 int port = receivePacket.getPort();                   
				 String capitalizedSentence = receivedData.toUpperCase();                   
				 sendData = capitalizedSentence.getBytes();                   
				 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);                  
				 serverSocket.send(sendPacket); 
				 
				 //if() {
					 
				 //}
		
		 }
		
	}

     public static void openNewFrame(String name)
     {
    	// VGGameStates temp = new VGGameStates(name,level, troops, money);
    	 VGGameFrame vgObject = new VGGameFrame(name, "port");
     }
	
     
     public static void parseTheReceivedData(String sentence)
     {
		  	   String pName = "";
		  	   int levelNumber;
		  	   int playerGold;
		  	  String playerIPAddress;
		  	   
		  	   int[] count = new int[8];
		  	   int[][] positionValue = new int[10][10];
		  	   String countArray;
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
		
		  	   pName = dataFromClient[0];
		  	   playerIPAddress = dataFromClient[1];
		  	   levelNumber = Integer.parseInt(dataFromClient[2]);
		  	   
		  	   String[] troops = dataFromClient[3].split(",");
		  	   int[] troopCount = new int[troops.length];
		  	   for(int i=0; i< troops.length; i++)
		  	   {
		  		    int perMonster = Integer.parseInt(troops[i]);
		  		    troopCount[i] = perMonster;
		  	   }
		  		   
		  		   
		  	   playerGold = Integer.parseInt(dataFromClient[4]);
		  	   
		  	   String[] positionPer10 = dataFromClient[5].split("/");
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
		  	 clientList.add(new Client(pName, levelNumber, troopCount, playerGold, positionValue, sentence, playerIPAddress));
     } 
  

} 