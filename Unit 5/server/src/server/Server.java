/**
 * @author Junjian Xie
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(8888);
			while(true) {
				Socket c = s.accept();
				DefaultSocketClient socketThread = new DefaultSocketClient(c);
				socketThread.start();
			}
		} catch (IOException e) {
			System.err.println("Unable to connect!");
		}
	}
}
