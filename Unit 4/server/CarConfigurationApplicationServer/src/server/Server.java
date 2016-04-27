package server;

public class Server {
	public static void main(String[] args) {
		DefaultSocketClient socketThread = new DefaultSocketClient();
		socketThread.setPort(8888);
		socketThread.start();
	}
}
