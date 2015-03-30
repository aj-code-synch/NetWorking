package Networking;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; 

import Networking.Chat1.SenderThread;

public class ChatClient {
	static StartChat start_action;

	static JFrame frame;
	
	static JPanel panel_main,
                  panel_user,
                  panel_ip,
                  panel_port;

	static JTextField username,
					  ipaddress,
					  port;
	
	static JLabel username_lbl,
				  ipaddress_lbl,
				  port_lbl;

	static JButton startchat;
	
	public static void main(String args[]){




		username = new JTextField(10);
		username_lbl = new JLabel("User Name");
		panel_user = new JPanel();
		panel_user.setLayout(new FlowLayout());
		panel_user.add(username_lbl);
		panel_user.add(username);
		
		
		ipaddress = new JTextField(15);
		ipaddress.setText("127.0.0.1");
		ipaddress_lbl = new JLabel("IP Address");
        panel_ip = new JPanel();
        panel_ip.setLayout(new FlowLayout());
        panel_ip.add(ipaddress_lbl);
        panel_ip.add(ipaddress);
        
		
		
		port = new JTextField(4);
		port.setText("5000");
		port_lbl = new JLabel("Port");
		panel_port = new JPanel();
		panel_port.setLayout(new FlowLayout());
		panel_port.add(port_lbl);
		panel_port.add(port);
		

		startchat = new JButton("Start");
		start_action = new StartChat();
		startchat.addActionListener(start_action);


		panel_main = new JPanel();
		panel_main.setLayout(new BoxLayout(panel_main, BoxLayout.Y_AXIS));
		
		

		panel_main.add(panel_user);
		panel_main.add(panel_ip);
		panel_main.add(panel_port);
		panel_main.add(startchat);

		frame = new JFrame();
		frame.setSize(400, 400);
		frame.add(panel_main);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	static class StartChat implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String userid    = username.getText();
			String ip        = ipaddress.getText();
			int port_no   = Integer.parseInt(port.getText());
			
			Chat1 myclient = new Chat1(userid, ip, port_no);
			myclient.startchat();
			
			

		}

	}
}
