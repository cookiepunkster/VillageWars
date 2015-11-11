package VGSingletons;

import java.util.ArrayList;
import java.util.List;

public class VGGameSingleton {
	private static VGGameSingleton singleton = null;
	private int[] cost = {1,5,10,10,10,20,30,1};
	private int[][] defenderHealth = new int[10][10];
	List<Integer[]> troopList = new ArrayList<Integer[]>();
	//ArrayList<Integer[]> troopCount = new ArrayList<Integer[]>();
	ArrayList<Integer> troopValue = new ArrayList<Integer>();
	
	private VGGameSingleton() {
		initializeDefenderHealth();
	}
	
	
	public static VGGameSingleton getInstance() {
		
		if(singleton == null) {
			singleton = new VGGameSingleton();
		}
		
		return singleton;
		
	}
	
	public int getTroopValues(int i)
	{
		return cost[i];
	}
	
	
	public void initializeDefenderHealth()
	{
		for(int i=0; i<10; i++)
		{
			for(int j=0; j<10; j++)
			{
				defenderHealth[i][j] = 0;
			}
		}
		
	}
	
	public int getDefenderTroopHealth(int x, int y){
		  return defenderHealth[x][y];
	}
	
	public int getAttackerTroopHealth(int attackerTroop, int counter){
		Integer[] values = troopList.get(attackerTroop);
        return values[counter];
	}
	
	public void attackerHealth()  // count
	{
		 int troop[] = VGPlayerSingleton.getInstance().getPlayerTroops();
		
		for(int i=0; i<8; i++){
			Integer[] troopCount= new Integer[troop[i]];
			for(int j=0; j< troop[i]; j++){
					troopCount[j] = cost[i];
			}
			troopList.add(troopCount);  
		}
		 
	}
	
	
	public void printAttackerHealth()
	{
		
		for(int x = 0; x<troopList.size(); x++){    // troopList Size
	        Integer[] values = troopList.get(x);
	        for(int y=0; y<values.length; y++)   // do not include the last index in every array
	        {
	        	System.out.print(values[y]+" ");
	        
	        }
	        System.out.println("");
		}

	}

	public void defenderTroopHealth(int troop, int x, int y)
	{
		
				defenderHealth[x][y] = cost[troop];
		
	}
	
	public void printDefenderHealth()
	{
		for(int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				System.out.print(defenderHealth[i][j] +" ");
			}
			System.out.println("");
		}
	}
	
	public void minusDefenderTroopHealth(int currentAttackerHealth, int currentDefenderHealth, int x, int y)
	{
		 defenderHealth[x][y] = currentAttackerHealth - currentDefenderHealth;
	}
	
	public void minusAttackerTroopHealth(int currentAttackerHealth, int currentDefenderHealth, int attackerTroop, int counter)
	{
		Integer[] values = troopList.get(attackerTroop);
        values[counter] = currentAttackerHealth - currentDefenderHealth;
	}
	
	public void updateAttackerHealth(int attackerTroop, int counter, int value)
	{
		Integer[] values = troopList.get(attackerTroop);
        values[counter] = 0;
	}
	
	public void updateDefenderHealth(int x, int y, int value)
	{
		defenderHealth[x][y] = value;
	}
	
	

}
