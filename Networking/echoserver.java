package Networking;
import java.net.*;
import java.io.*;
import java.io.ObjectInputStream.GetField;
import java.util.*;

public class echoserver {



	static echoserver echo;

	public static class Client implements Runnable {

		Socket clientsocket;
		String hostName = "127.0.0.1";
		int portNumber = 5000;
	    static int onesleep = 0;

		public void run(){ 
			
			System.out.println("Client Run  " + new Date());
			
			try {
				clientsocket = new Socket(hostName,portNumber);
				PrintWriter writer = new PrintWriter(clientsocket.getOutputStream());

				for (int i = 0; i < 10; i++) {
					writer.println("test String   " + i );	
				}

				writer.flush();
				clientsocket.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}
	}

	public class Server implements Runnable {

		public void run(){

			System.out.println("Server Run" + new Date());
			int portNumber = 5000;
			ServerSocket ss = null;
			Socket serversocket = null;
			InputStreamReader streamReader; 

			try {
				ss = new ServerSocket(portNumber);
				serversocket = ss.accept();

				System.out.println("bufferreader read  " + new Date());

				streamReader = new InputStreamReader(serversocket.getInputStream());
				BufferedReader reader = new BufferedReader(streamReader);
				String fromReader;
				
				System.out.println(reader.ready());
                System.out.println(reader.readLine());
                
                
                
				while ((fromReader = reader.readLine()) != null) {
					System.out.println(fromReader);

				}
				
				System.out.println("After While in Server Run");


			} catch (IOException ex_server) {
				// TODO Auto-generated catch block
				System.out.println("Server Run Error    " + new Date());
				ex_server.printStackTrace();
	
			}
			  finally {
				  try {
					serversocket.close();
					  ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
			  }
			System.out.println("open" + new Date());
			System.out.println(serversocket.isBound());


		}
	}


	public void go(){

		Server server = new Server();
		Thread serverThread = new Thread(server);
		serverThread.start();

		Client client = new Client();
		Thread clientThread = new Thread(client);
		clientThread.start();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		echo = new echoserver();
		echo.go();





	}

}
