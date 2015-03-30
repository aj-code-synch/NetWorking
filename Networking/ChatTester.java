package Networking;

import java.util.*;

public class ChatTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ChatServer serverRunner = new ChatServer("127.0.0.1", 5000);
        
		Thread serverThread = new Thread(serverRunner);
		
		serverThread.start();
		
		System.out.println("Server Started" + new Date());
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		Thread clientThread = new Thread() {
			public void run() {
				Chat1 user1 = new Chat1("user1", "127.0.0.1", 5000);
				user1.startchat();
				
			}
		};
		clientThread.start();
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		Thread clientThread2 = new Thread() {
//			public void run() {
//				Chat1 user1 = new Chat1("user2", "127.0.0.1", 5000);
//				user1.startchat();
//				
//			}
//		};
//			
//		
//		clientThread2.start();
		
		System.out.println("Client Started" + new Date());
	}

}
