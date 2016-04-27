package client;

public class Client {
	public static void main(String[] args) {
		DefaultSocketClient socketThread = new DefaultSocketClient();
		socketThread.setHost("localhost");
		socketThread.setPort(8888);
		socketThread.start();
	}
}
