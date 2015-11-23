package VGCustomClasses;
import java.io.*; 
import java.net.*;
import VGFrames.VGGameFrame;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


import java.io.*; import java.net.*; 
public class VGServerUDP {   
	/**
	 * GET FIRST THE INFO VALUES OF THE CLIENT when position value is clicked
	 * Then, return the troopCount to the one who first clicked
	 * Return the positionValues of the other Client
	 * Show it to the Frame
	 * Use the troopCount in attacking the village
	 */
	
	
	
	
	static ArrayList<Client> clientList = new ArrayList<Client>();
	public VGServerUDP() throws IOException {
		           
		 byte[] receiveData = new byte[1024];             
		 byte[] sendData = new byte[1024];     

		 byte[] receiveData1 = new byte[1024];             
		 byte[] sendData1 = new byte[1024];     
		 
		 int flag = 0, flag1 = 0;
		 
		 
		 while(true){                   
			 
			 if(flag == 0) {
				 
				 DatagramSocket serverSocket = new DatagramSocket(9876); 
				 
				 DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);                   
				 serverSocket.receive(receivePacket);             
			
				 String receivedData = new String( receivePacket.getData()); 
				 parseTheReceivedData(receivedData);
				 //flag++;
				 
				 InetAddress IPAddress = receivePacket.getAddress();                  
				 int port = receivePacket.getPort();                   
				 String capitalizedSentence = receivedData.toUpperCase();    
				 System.out.println(IPAddress.toString());
				 sendData = capitalizedSentence.getBytes();                   
				 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);                  
				 serverSocket.send(sendPacket); 	 
				 //openNewFrame("Jolly");
				 
				 flag = 1;
			 }
			 
			 if(flag == 1) {
				 
				 DatagramSocket serverSocket1 = new DatagramSocket(9877); 
				 
				 DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);                   
				 serverSocket1.receive(receivePacket);             
				 
				 String receivedData1 = new String( receivePacket.getData()); 
				 System.out.println("ATTACKED FROM: " + receivedData1);                   
				 flag++;
				
				 InetAddress IPAddress = receivePacket.getAddress();                  
				 int port = receivePacket.getPort();                   
				 String capitalizedSentence = receivedData1.toUpperCase();                   
				 sendData1 = capitalizedSentence.getBytes();                   
				 DatagramPacket sendPacket = new DatagramPacket(sendData1, sendData1.length, IPAddress, port);                  
				 serverSocket1.send(sendPacket); 	 
				 //openNewFrame("Jolly");
				 
			 }
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
		  	   
		  	 clientList.add(new Client(pName, levelNumber, troopCount, playerGold, positionValue));
		  	 printClientInfo();
     }
     
     public static void printClientInfo(){
    	 for (int i = 0; i < clientList.size(); i++) {
    		    Client clientInfo = clientList.get(i);
    		    System.out.println("Client number: "+i);
    		    clientInfo.getClientName();
    		    clientInfo.getLevelNumber();
    		    clientInfo.getClientTroopCount();
    		    clientInfo.getPlayerGold();
    		    clientInfo.getPositinValue();
    		    
    		    System.out.println("Client name" +  clientInfo.getClientName());
    		    System.out.println("Client Level Number " + clientInfo.getLevelNumber());
    		    System.out.println("Client goldMoney: "+ clientInfo.getPlayerGold());
    		}
     }
 
     
     
  
     //Store pName, levelNumber, troopCount[], playerGold, positionValue[][] 
} 