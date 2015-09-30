package mainFile;
import javax.swing.JFrame;
import userInterface.MainGameFrame;
import userInterface.InitialFrame;
import gameLogic.conditions;

public class VillageWars {

	public static void main(String[] args) {
		
		  MainGameFrame frame1 = new MainGameFrame();
		  
		  frame1.setDefaultLookAndFeelDecorated(true);
		  frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  frame1.setSize(1000, 600);
		  frame1.setLocationRelativeTo(null);
		  frame1.setVisible(true);
		  
		  conditions listener1 = new conditions(frame1);
		  frame1.getButton().addActionListener(listener1);

	}

}
