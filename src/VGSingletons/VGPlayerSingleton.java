package VGSingletons;

import VGFrames.VGGameFrame;
import VGTCPChatSystem.VGGameClient;

public class VGPlayerSingleton {

	private static VGPlayerSingleton singleton = null;
	
	private static VGGameClient client = null;
	private String name;
	private	String IPaddress;
	
	private int level = 0;
	private int goldcoins = 100;
	private int hitCount = 0;
	private int[] playerTroops = new int[8];
	
	
	private VGPlayerSingleton() {
			
	}
	
	public static VGPlayerSingleton getInstance() {
		
		if(singleton == null) {
			singleton = new VGPlayerSingleton();
		}
		
		return singleton;
		
	}
	
	public static void setClient(String ipAddress, int port, String playerName, VGGameFrame frame) {
		
		if(client == null)
		{
			client = new VGGameClient(ipAddress,port,playerName,frame);
		}
		
	}
	
	public static VGGameClient getClient() {
		
		return client;
		
	}
	
	public void setStats(String name, String serverIPaddress) {
		
		this.name = name;
		this.IPaddress = IPaddress;
		
	}
	
	public void addHitCount()
	{
		hitCount = hitCount + 1;
	}
	
	public void updateLevel(int levelUpdate) {
		
		level = level + levelUpdate;
		
	}
	
	public void addGoldCoins(int coinsCount){
			goldcoins = goldcoins + coinsCount;
		
	}
	
	public void minusGoldCoins(int coinsCount) {
		
			goldcoins = goldcoins - coinsCount;
	
	}
	public void setPlayerTroops(int[] count)
	{
		for(int i=0; i<8; i++)
		{
			playerTroops[i] = count[i];
		}
	}
	
	public void resetGoldCount()
	{
		goldcoins = 100;
	}
	
	public int getLevel() {
		
		return level;
		
	}
	
	public int getGoldCoins() {
		
		return goldcoins;
		
	}
	
	public int getHitCount()
	{
		return hitCount;
	}
	
	public String getPlayerName() {
		
		return name;
		
	}
	
	public String getIPAddress()
	{
		return IPaddress;
	}
	
	public int[] getPlayerTroops()
	{
		return playerTroops;
	}

}
