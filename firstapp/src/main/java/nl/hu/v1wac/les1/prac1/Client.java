package nl.hu.v1wac.les1.prac1;

import java.net.Socket;
import java.io.OutputStream;

public class Client {
	
	  public static void main(String[] aps) throws Exception{
	        Socket s = new Socket("145.89.253.38", 4711);
	        OutputStream os = s.getOutputStream();
	        os.write("hello\n".getBytes());
	        s.close();
	    }

}
