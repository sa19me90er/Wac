package nl.hu.v1wac.les1.prac4;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

class Server {
	
	public static void main (String [] arg) throws Exception {
		while (true){
		ServerSocket ss = new ServerSocket (4711);
		Socket s = ss.accept();
		InputStream is = s.getInputStream();
		Scanner sc = new Scanner (is);
		OutputStream os = s.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			System.out.println(line);
			
		
			if (line.isEmpty())  {
			
			break;
		}
		
		}
		pw.println("HTTP/1.1 200 OK");
		pw.println("");
		pw.println("<h1>It Works <h1>");
		pw.flush();
				
		
		
	
		
		
		sc.close();
		s.close();
		ss.close();
	}
}
}