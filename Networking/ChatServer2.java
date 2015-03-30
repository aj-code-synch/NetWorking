package Networking;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Networking.ChatClient.StartChat;

public class ChatServer2 {

	private static int port; 
	private static String ip_add;
	private Socket socket;
	private ArrayList<Socket> clients;
	private ServerSocket ss;
	private static ChatServer server_runner;

	static JFrame frame;
	
	static JPanel 	panel_main,
					panel_ip,
					panel_port;

	static JTextField ipaddress_inp,
					  port_inp;

	static JLabel	ipaddress_lbl,
					port_lbl;

	static JButton startserver;
	
	static StartServer start_action;

	public static void main(String[] args) {



		ipaddress_inp = new JTextField(15);
		ipaddress_inp.setText("127.0.0.1");
		ipaddress_lbl = new JLabel("IP Address");
		panel_ip = new JPanel();
		panel_ip.setLayout(new FlowLayout());
		panel_ip.add(ipaddress_lbl);
		panel_ip.add(ipaddress_inp);



		port_inp = new JTextField(4);
		port_inp.setText("5000");
		port_lbl = new JLabel("Port");
		panel_port = new JPanel();
		panel_port.setLayout(new FlowLayout());
		panel_port.add(port_lbl);
		panel_port.add(port_inp);


		startserver = new JButton("Start");
		start_action = new StartServer();
		startserver.addActionListener(start_action);


		panel_main = new JPanel();
		panel_main.setLayout(new BoxLayout(panel_main, BoxLayout.Y_AXIS));



		panel_main.add(panel_ip);
		panel_main.add(panel_port);
		panel_main.add(startserver);

		frame = new JFrame();
		frame.setSize(400, 400);
		frame.add(panel_main);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	static class StartServer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String ip        = ipaddress_inp.getText();
			int port_no   = Integer.parseInt(port_inp.getText());
            ChatServer serverRunner = new ChatServer(ip, port_no);
            Thread serverThread = new Thread(serverRunner);
            serverThread.start();


		}

	}
}

