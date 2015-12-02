package VGCustomClasses;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class NetworkAnalyzer {
	JFrame mainFrame = new JFrame();
	JPanel panel1 = new JPanel();
	JTextArea textArea = new JTextArea();
	int noOfPuzzles = 0;
	int dimensionSize = 0;

	public NetworkAnalyzer(ArrayList IPList){          //constructor for Frame initialization

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(200,100);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setVisible(true);
        mainFrame.setResizable( false );
        mainFrame.setLocationRelativeTo(null);
        
        displayIPList(IPList);
        
        
      }
	
	public void displayIPList(ArrayList IPList)
	{
		
		String ipAdd = "";
		for(int i=0; i< IPList.size(); i++){
			ipAdd = IPList.get(i).getIPClient();
			textArea.append(ipAdd+" ");
			
		}
	}
}






