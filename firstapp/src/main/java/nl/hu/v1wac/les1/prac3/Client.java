package nl.hu.v1wac.les1.prac3;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Client {
    public static void main(String[] aps) throws Exception{
        Socket s = new Socket("145.89.253.38", 4711);
        OutputStream os = s.getOutputStream();
        PrintWriter pw =new  PrintWriter (os);
       
        
        
        Scanner sc = new Scanner (System.in);

        while (sc.hasNextLine()) {
        	String line = sc.next();
        	
        	if (line.equals("stop")){
        		break;
        	}
        	
        	pw.println(line);
        	pw.flush();
        	
        }
        
        
        os.close();      
        pw.close();
        s.close();
        sc.close();
    }
    
}
