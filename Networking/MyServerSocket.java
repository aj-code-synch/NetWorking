package Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket extends ServerSocket {

	@Override
	public MySocket accept() throws IOException {
		// TODO Auto-generated method stub
		return (MySocket) super.accept();
	}

	public MyServerSocket() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
