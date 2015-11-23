package VGCustomClasses;

public class Client {
	//Store pName, levelNumber, troopCount[], playerGold, positionValue[][] 
	private String clientName;
	private int clientLevelNo;
	private int[] clientTroopCount = new int[8];
	private int clientGold;
	private int[][] clientPositionValue = new int[10][10];
	
	public Client()
	{
		
	}

	public Client(String name, int levelNumber, int[] troopCount, int playerGold, int[][] positionValue ){
		this.clientName = name;
		this.clientLevelNo = levelNumber;
		
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
	
	public int[][] getPositinValue()
	{
		return clientPositionValue;
	}
}

