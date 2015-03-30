package Networking;

import java.net.Socket;

public class MySocket extends Socket {
	
public String username;

public MySocket(String user) {
	username = user;
}

}
