package mainFile;
import javax.swing.JFrame;
import userInterface.MainGameFrame;
import userInterface.InitialFrame;
import gameLogic.conditions;

public class VillageWars {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  MainGameFrame frame1 = new MainGameFrame();
	//	  InitialFrame frame2 = new InitialFrame();
		  
		  frame1.setDefaultLookAndFeelDecorated(true);
		  frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  frame1.setSize(1000, 600);
		  frame1.setLocationRelativeTo(null);
		  frame1.setVisible(true);
		  
		  conditions listener1 = new conditions(frame1);
		  frame1.getButton().addActionListener(listener1);
		//  frame1.getTextArea().addActionListener(listener1);
		  
		//   conditions listener2 = new conditions();
		 // frame2.getButton1Barb().addActionListener(listener2);
		  
		//DUH.
	}

}
