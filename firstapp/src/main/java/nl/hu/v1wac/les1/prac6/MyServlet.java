package nl.hu.v1wac.les1.prac6;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyServlet extends Thread {
	private Socket socket;

	public MyServlet(Socket sock) {
		socket = sock;

	}

	public void run() {
		InputStream is = null;
		OutputStream os = null;
		try {
			os = socket.getOutputStream();
			is = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner sc = new Scanner(is);
		PrintWriter pw = new PrintWriter(os, true);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.isEmpty()) {
				pw.println("HTTP/1.1 200 OK");
				pw.println("");
				pw.println("<h1>It Works <h1>");
				pw.flush();
				sc.close();
				break;
			}

			System.out.println(line);

		}
		try { Thread.sleep(10000); } catch (InterruptedException ie) { ie.printStackTrace(); }
	}

}
