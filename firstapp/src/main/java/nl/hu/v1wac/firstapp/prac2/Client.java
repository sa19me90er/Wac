package nl.hu.v1wac.firstapp.prac2;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

class Client {
    public static void main(String[] aps) throws Exception{
        Socket s = new Socket("145.89.84.177", 4711);
        OutputStream os = s.getOutputStream();
        PrintWriter pw =new  PrintWriter (os);
        pw.println("Shant");
        pw.flush();
        
        
        os.close();      
        pw.close();
        s.close();
    }
    
}
