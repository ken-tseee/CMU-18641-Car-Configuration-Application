package client;

public interface SocketClientInterface {
	boolean openConnection(); 
	//Open the connection.
	void handleSession(); 
	//Handle the connection.
	void closeSession();
	//Close the connection.
}
