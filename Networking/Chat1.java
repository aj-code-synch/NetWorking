package Networking;

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import javax.swing.*;
import javax.xml.ws.handler.MessageContext.Scope;


public class Chat1 {

	JFrame frame;
	JPanel msg_pan, chat_pan,connect_pan;
	JButton send,connect;
	JTextField server_ip;
	JTextArea type_area, chat_area;

	private static String user;
	private SendMsg send_action = new SendMsg();
	private Connect connect_action = new Connect();
	private Socket writerSocket; 
	//	private Socket readerSocket;
	static String ip_address;
	static int port;
	private PrintWriter writer;
	private ReaderThread readerRunner;
	private SenderThread senderRunner;
	Thread senderThread;


	Thread  readerThread;

	public Chat1(String name, String ip_add_in, int port_in){
		user = name;
		ip_address = ip_add_in;
		port = port_in;
		
	}


	public void create_window() {

		frame = new JFrame(user);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chat_pan = new JPanel();

		//		Adding Main Chat Area 
		chat_area = new JTextArea(30,50);
		chat_area.setEditable(false);
		chat_area.setLineWrap(true);


		JScrollPane chat_scroll = new JScrollPane(chat_area);
		chat_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chat_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chat_pan.add(chat_scroll);



		msg_pan = new JPanel();


		send = new JButton("Send");
		send.addActionListener(send_action);
		type_area = new JTextArea(5,50);
		type_area.setLineWrap(true);

		JScrollPane type_scroll = new JScrollPane(type_area);
		type_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		msg_pan.add(type_scroll);
		msg_pan.add(send);
		msg_pan.setLayout(new BoxLayout(msg_pan, BoxLayout.X_AXIS));


//		connect_pan = new JPanel();
//		server_ip = new JTextField(15);
//		
//		connect = new JButton("Connect");
//		connect.addActionListener(connect_action);
//		connect_pan.add(server_ip);
//		connect_pan.add(connect);
		
		
		frame.getContentPane().add(BorderLayout.SOUTH, msg_pan);
		frame.getContentPane().add(BorderLayout.CENTER, chat_pan);
//		frame.getContentPane().add(BorderLayout.NORTH, connect_pan);
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.pack();


	}


//	public static void main(String ip_add_in, int port_in) {
//		// TODO Auto-generated method stub
//		ip_address = ip_add_in;
//		port = port_in;
//		Chat1 user1 = new Chat1("User1");
//
//		user1.startchat();
//
//	}

	class SendMsg implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String msg = type_area.getText();

			if (msg != null && msg != "") {
				msg = user + " :: "  +  msg ;
				senderRunner = new SenderThread(msg);
				type_area.setText(null);
				senderThread = new Thread(senderRunner);
				senderThread.start();
			}
			
			

		}


	}
	


	class Connect implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ip_address = server_ip.getText();
			connect_socket();
		}
		
	}

	public void setup_connection() {




	}

	public void startchat(){

        

		create_window();

//		System.out.println("Window Done");

		connect_socket();

		

	}
	
	public void connect_socket(){
		
		try {
			System.out.println("Start Chat" + new Date());
			System.out.println(ip_address);
			System.out.println(port);
			writerSocket = new Socket(ip_address,port);
			
			if(writerSocket.isBound())
			{
				readerRunner = new ReaderThread();
				readerThread = new Thread(readerRunner);
				readerThread.start();

				System.out.println("Thread Started");
	
			}
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public class ReaderThread implements Runnable {

		InputStreamReader streamReader; 
		BufferedReader reader;
		String msg;

		public void run(){

			try {

				System.out.println("Entered ReaderThread Run");
				streamReader = new InputStreamReader(writerSocket.getInputStream());
				reader = new BufferedReader(streamReader);

				while (true) {
					msg = reader.readLine();
					if (msg != null) {
						msg = msg + "\n";
						chat_area.append(msg);	
						
					}


				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Leaving ReaderThread Run");

		}

	}

	public class SenderThread implements Runnable{

		String msg;
		public SenderThread(String msg_in){
//			msg = msg_in + "\n";
			msg = msg_in;
		}

		public void run(){

			System.out.println("Entered SenderThread Run" + msg);


			try {

				if (writer == null) {
					writer = new PrintWriter(writerSocket.getOutputStream());	
				}

				System.out.println("Writer has Error-->" + writer.checkError());

				writer.println(msg);
				writer.flush();
//				writer.close();

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				System.out.println("Sender Thread Run Exception 1");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Sender Thread Run Exception 2");
				e.printStackTrace();
			}

			System.out.println("Leaving SenderThread Run");

		}
	}

}
