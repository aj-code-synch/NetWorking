package Networking;

import java.io.*;
import java.net.*;

public class DailyAdvice {
	
	ServerSocket ss;
	Socket sock;
	BufferedReader buff_reader;

	public void go() {



		try {

			ss = new ServerSocket(5000);
			System.out.println("Server open");
			sock = new Socket(InetAddress.getLocalHost(), 5000);
			System.out.println("Socket Created");


			
//			Socket -> streamreader -> bufferreadr -> filereader
			InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
			
			File testFile = new File("");
		    String currentPath = testFile.getAbsolutePath();
		    System.out.println("current path is: " + currentPath);
			
			File myfile = new File("advice.txt");
			FileReader fileReader = new FileReader(myfile);
			
			
			
			buff_reader = new BufferedReader(fileReader);
			
			String advice;
			while ((advice = buff_reader.readLine()) != null) {
				System.out.println("Today's Advice" + advice);	
			}
			

		} catch (IOException ex) {
			// TODO: handle exception
			System.out.println("Error");
			ex.printStackTrace();
		} 
		
		finally {

			try {
				if(buff_reader != null){
					buff_reader.close();	
				}
				
				sock.close();
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}



		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DailyAdvice client = new DailyAdvice();
		client.go();

	}

}
