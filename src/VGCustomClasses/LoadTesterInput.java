package VGCustomClasses;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoadTesterInput extends JFrame implements ActionListener{
	static JTextArea textArea;
	JButton btnAccept;
	String address;
	public LoadTesterInput() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblInputIpAdress = new JLabel("Input IP Adress of Server:");
		lblInputIpAdress.setBounds(30, 50, 150, 20);
		panel.add(lblInputIpAdress);
		
		textArea = new JTextArea();
		textArea.setBounds(183, 50, 171, 19);
		panel.add(textArea);
		
		btnAccept = new JButton("Accept");
		btnAccept.addActionListener(this);
		btnAccept.setBounds(160, 100, 89, 36);
		panel.add(btnAccept);
	
		setResizable(false);
    	pack();
	 
	    setSize(402,200);
		setVisible(true);
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAccept){
			//System.out.println(textArea.getText());
			LoadTester loadTester = new LoadTester();
		}
		
	}
	
	 public static String getAddress() {
		    return textArea.getText(); 
		}
	 
}
