package Networking;

import java.net.Socket;

public class ChatClientSocket extends Socket {
	
	private String userName;
	
	public ChatClientSocket(String Host, int port, String userID) {
		super();
		userName = userID;
	}
	
	public String getUser(){
		
		return userName;
		
	}

}
