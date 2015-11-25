package VGCustomClasses;

public class HitCount {
	//Store pName, levelNumber, troopCount[], playerGold, positionValue[][] 
	private String clientName;
	private int hitCountPerAttacker;
	public HitCount()
	{
		
	}

	public HitCount(String name, int hitCount){
		this.clientName = name;
		this.hitCountPerAttacker = hitCount;
		
	}
	
	public String getClientName()
	{
		return clientName;
	}
	
	public int getHitCount()
	{
		return hitCountPerAttacker;
	}
	
}

