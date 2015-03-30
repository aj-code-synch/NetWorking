package Networking;

import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Scanning {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Enumeration<NetworkInterface> interfaces;
		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface sys = interfaces.nextElement();
				
//				System.out.println(Inet4Address.getLocalHost());
				
//				System.out.println(sys.getInetAddresses());
				byte[] mac = sys.getHardwareAddress();
				
				StringBuilder sb = new StringBuilder();
				if (mac!=null) {
					System.out.println(sys.getInetAddresses());
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
					}
					
					System.out.println(sb.toString());
				}
	
				

			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		


	}

}
