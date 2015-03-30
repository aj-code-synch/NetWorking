package Networking;

import java.awt.*;
import java.util.*;

public class mousePosition implements Runnable {

	static Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
    static Timer t = new Timer();
    int x,y = 0;


    public void run()
    {
    	
    	while(true){
    		try
            {
                    x = mouseLocation.x;
                    y = mouseLocation.y;

                    System.out.println("X:"+x+" Y:"+y);
            }

            catch(Exception e)
            {
                System.out.println("Exception caught : "+e);
            }
	
    	}
    		
    	
        
    }


	public static void main(String[] args) {
        Thread t1 = new Thread(new mousePosition());
        t1.start();

    }

}
