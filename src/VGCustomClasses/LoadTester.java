package VGCustomClasses;

import javax.swing.JFrame;
import javax.swing.JPanel;

import VGSingletons.VGPropertiesSingleton;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class LoadTester extends JFrame{
	VGClientUDP clientUDP = new VGClientUDP();

	public LoadTester() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JButton btnSend = new JButton("Send");
		 btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	System.out.println(LoadTesterInput.getAddress());
					try {
						clientUDP.loadTester();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		});
		btnSend.setBounds(60, 68, 251, 44);
		panel.add(btnSend);
		

	    setResizable(false);
    	pack();
	 
	    setSize(402,200);
		setVisible(true);
	}
	
	public void getAddress(){
		
	}
	
}
