package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class ChatServer implements Runnable {

	private static int port; 
	private static String ip_add;
	private Socket socket;
	private ArrayList<Socket> clients;
	private ServerSocket ss;

	public ChatServer(String ip_add_in, int port_in) {
		// TODO Auto-generated constructor stub
		port = port_in;
		ip_add = ip_add_in;

		clients = new ArrayList<Socket>();
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println("ChatServer Start!!" + new Date() );

		try {
			ss = new ServerSocket(port, 10);

			while(true){
				socket = ss.accept();
				System.out.println("ChatServer Accepts!!" + new Date() );
				clients.add(socket);
				sendWelcomeMessage();
				
				Thread transmit = new Thread(new transmitMessagestoAll(socket));
			
				transmit.setName("" + socket.getPort());
				transmit.start();
				
//				(new Thread(new cleanUp(clients))).start();

				if(clients.isEmpty()) {
					System.out.println("All users Gone");
					break;
				}
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void sendWelcomeMessage() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println("Welcome To Chat Server");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public class transmitMessages implements Runnable {

		private Socket thisClient;
		private BufferedReader reader;
		private PrintWriter writer;
		String msg;
		public transmitMessages(Socket sock){
			thisClient = sock;
		}


		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {
				reader = new BufferedReader(new InputStreamReader(thisClient.getInputStream()));
				writer = new PrintWriter(thisClient.getOutputStream());

				msg = reader.readLine();
				while(msg!=null){
					System.out.println("Transmitting");
					writer.println(msg);
					writer.flush();
					msg = reader.readLine();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while(true){

			}


		}

	}

	public class transmitMessagestoAll implements Runnable {

		private ArrayList<Socket> clientList;
		private Socket currentClient;
		private BufferedReader reader;
		private PrintWriter writer;
		String msg;
		int idx;
		public transmitMessagestoAll(Socket curr){

			clientList = new ArrayList<Socket>();
			clientList = clients;
			currentClient = curr;
			idx = clientList.indexOf(curr);
		}


		@Override
		public void run() {
			// TODO Auto-generated method stub

			
			System.out.println(Thread.currentThread().getName());
			           
			
			while(!clientList.isEmpty()){

			
				System.out.println(clientList.size());
				try {
					reader = new BufferedReader(new InputStreamReader(currentClient.getInputStream()));
					msg = reader.readLine();


					while(msg!=null){
						for (Socket thisClient : clientList) {
							System.out.println(Thread.currentThread().getName() 
									           + ">>>>>"
									           + thisClient.getPort());
							writer = new PrintWriter(thisClient.getOutputStream());

							System.out.println("Transmitting");
							writer.println(msg);
							writer.flush();
							
						}
						msg = reader.readLine();
						System.out.println("-----------------");
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("User Gone! Breaking");
					clientList.remove(idx);
					System.out.println(clientList.size());
//					e.printStackTrace();
					break;
					

				}

			}



		}


	}



//	public class cleanUp implements Runnable {
//
//		private ArrayList<Socket> clientList;
//		private BufferedReader reader;
//		private PrintWriter writer;
//		String msg;
//		public cleanUp(ArrayList<Socket> sockList){
//
//			clientList = new ArrayList<Socket>();
//			clientList = sockList;
//		}
//
//		public void run(){
//			for (Socket thisClient : clientList) {
//
//				if(thisClient.isClosed()){
//					int idx = clientList.indexOf(thisClient);
//					clientList.remove(idx);
//				}
//			}
//		}
//
//	}
}



