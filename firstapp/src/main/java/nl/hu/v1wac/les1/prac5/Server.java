package nl.hu.v1wac.les1.prac5;

import java.net.ServerSocket;
import java.net.Socket;

class Server {

	public static void main(String[] arg) throws Exception {
		ServerSocket ss = new ServerSocket(4711);

		while (true) {

			Socket s = ss.accept();
			MyServlet sl = new MyServlet(s);
			sl.start();

		}
	}
}
