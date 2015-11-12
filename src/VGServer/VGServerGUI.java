package VGServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import VGCustomClasses.VGServerUDP;

public class VGServerGUI extends JFrame implements ActionListener {

	private JTextArea TCPstatus;
	private JTextArea UDPstatus;
	
	private VGServerClass server;
	private JButton btnStartStop;
	
	VGServerGUI() {
		
		setLayout(new BorderLayout());
		
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new GridLayout(1,2));
		statusPanel.setBackground(Color.YELLOW);
		
		JPanel panelForTCP = new JPanel(new GridLayout(1,1));
		
		TCPstatus = new JTextArea("Status for TCP.\n\n", 80,80);
		TCPstatus.setEditable(false);
		panelForTCP.add(new JScrollPane(TCPstatus));

		Border borderForTCPStatus = BorderFactory.createLineBorder(Color.BLACK,1);
		TCPstatus.setBorder(borderForTCPStatus);		
		
		DefaultCaret caretForTCP = (DefaultCaret)TCPstatus.getCaret();
		caretForTCP.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		
		
		JPanel panelForUDP = new JPanel(new GridLayout(1,1));
		
		UDPstatus = new JTextArea("Status for UDP.\n\n",80,80);
		UDPstatus.setEditable(false);
		panelForUDP.add(new JScrollPane(UDPstatus));
		
		Border borderForUDPStatus = BorderFactory.createLineBorder(Color.BLACK, 1);
		UDPstatus.setBorder(borderForUDPStatus);
		
		DefaultCaret caretForUDP = (DefaultCaret)UDPstatus.getCaret();
		caretForUDP.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1,1));
		
		btnStartStop = new JButton("Start");
		btnStartStop.addActionListener(this);
		buttonsPanel.add(btnStartStop);
		
		
	
		statusPanel.add(panelForTCP);
		statusPanel.add(panelForUDP);
		
		add(buttonsPanel, BorderLayout.PAGE_END);
		add(statusPanel, BorderLayout.CENTER);
		
		pack();
		setSize(700,700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void appendEventForTCP(String str) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		String event = sdf.format(new Date()) + " :: " + str;
		TCPstatus.append(str);
		
	}
	
	public void appendEventForUDP(String str) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		String event = sdf.format(new Date()) + " :: " + str;
		UDPstatus.append(str);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		/*
		try {
			new VGServerUDP();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		if(server != null)
		{
			server.stop();
			appendEventForTCP("Server stopped.");
			server = null;
			btnStartStop.setText("Start");
			return;
		}
		
		btnStartStop.setText("Stop");
		
		server = new VGServerClass(1500, this);
		new ServerRunning().start();
		
	}
	
	public static void main(String[] args) {

		new VGServerGUI();

	}

	class ServerRunning extends Thread {
		
		public void run() {
			server.start();
			
			System.out.println("Server Crashed");
			//appendEvent("Server Crashed");
			server = null;
		}
		
	}
	
}
