package VGCustomClasses;

public class Client {
	//Store pName, levelNumber, troopCount[], playerGold, positionValue[][] 
	private String clientName;
	private int clientLevelNo;
	private int[] clientTroopCount = new int[8];
	private int clientGold;
	private int[][] clientPositionValue = new int[10][10];
	private String playerIPAddress;
	private String sentence;
	
	public Client()
	{
		
	}

	public Client(String name, int levelNumber, int[] troopCount, int playerGold, int[][] positionValue , String sentence, String playerIPAddress ){
		this.clientName = name;
		this.clientLevelNo = levelNumber;
		this.playerIPAddress = playerIPAddress;
		this.sentence = sentence;
		
		for(int i=0; i<8; i++)
		{
			clientTroopCount[i] = troopCount[i];
		}
		
		this.clientGold = playerGold;
		for(int i=0; i<10; i++)
		{
			for(int j=0; j<10; j++)
			{
				clientPositionValue[i][j] = positionValue[i][j];
			}
		}
		
		
	}
	
	public String getClientName()
	{
		return clientName;
	}
	
	public String getIPAddress()
	{
		return playerIPAddress;
	}
	
	public int getLevelNumber()
	{
		return clientLevelNo;
	}
	
	public int[] getClientTroopCount()
	{
		return clientTroopCount;
	}
	
	public int getPlayerGold()
	{
		return clientGold;
	}
	
	public int[][] getPositionValue()
	{
		return clientPositionValue;
	}
}

