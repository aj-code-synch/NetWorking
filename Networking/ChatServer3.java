package Networking;

public class ChatServer3 {

	
	public static void main(String[] args) {
		
		ChatServer server = new ChatServer("127.0.0.1", 5000);
		Thread serverT = new Thread(server);
		serverT.start();
	}

}
