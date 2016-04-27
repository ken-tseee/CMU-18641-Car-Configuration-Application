package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import model.Automobile;

public class SocketIO implements ServerSocketIO {
	private String host;
	private int port;
	private ObjectInputStream reader; 
	private ObjectOutputStream writer;
	/**
	 * Constructor.
	 */
	public SocketIO(String host, int port) {
		this.host = host;
		this.port = port;
	}
	/**
	 * Get an Automobile object from the server with a particular model name.
	 */
	@Override
	public Automobile getAutomobileObject(String modelName) {
		Socket serverSocket = null;
		writer = null;
		Automobile auto = null;
		try {
			serverSocket = new Socket(host, port);
			writer = new ObjectOutputStream(serverSocket.getOutputStream());
			sendOutput(modelName);
			System.out.println(modelName);
			reader = new ObjectInputStream(serverSocket.getInputStream());
			auto = (Automobile) reader.readObject();
			serverSocket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return auto;
	}
	/**
	 * Get a list of Automobile models from the server.
	 */
	@Override
	public ArrayList<String> getAutomobileList() {
		Socket serverSocket = null;
		writer = null;
		reader = null;
		ArrayList<String> modelList = null;
		try {
			serverSocket = new Socket(host, port);
			writer = new ObjectOutputStream(serverSocket.getOutputStream());
			reader = new ObjectInputStream(serverSocket.getInputStream());
			sendOutput("configuration");
			modelList = (ArrayList<String>) reader.readObject();
			for(String name : modelList) {
				System.out.println(name);
			}
			serverSocket.close();
		} catch(Exception e) {
			System.out.println("Errors occur when getAutomobileList()");
	        return modelList;
		}
		return modelList;
	}
	/**
	 * Send the output to the Server.
	 * @param obj
	 * @throws IOException
	 */
	public void sendOutput(Object obj) throws IOException { 
		writer.writeObject(obj);
		writer.flush();
	}
}
