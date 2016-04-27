package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import model.Automobile;

public class DefaultSocketClient extends Thread implements SocketClientConstants, SocketClientInterface {
	private ObjectInputStream reader; 
	private ObjectOutputStream writer;
	private Socket socket;
	private ServerSocket serverSocket;
	private String strHost; 
	private int iPort;
	/**
	 * Run method.
	 */
	public void run(){
		if (openConnection()) {
			handleSession();
			closeSession();
		}
	}
	/**
	 * Open the connection.
	 */
	@Override
	public boolean openConnection() {
		try {
			serverSocket = new ServerSocket(iPort);
			socket = serverSocket.accept();
		} catch(IOException socketError) {
			if (DEBUG)
				System.err.println("Unable to connect!");
			return false; 
		}
		try {
			writer = new ObjectOutputStream(socket.getOutputStream());
			reader = new ObjectInputStream(socket.getInputStream()); 
		} catch (Exception e) {
			if (DEBUG) 
				System.err.println("Unable to obtain stream to/from client!"); 
			return false;
		}
		return true;
	}
	/**
	 *Handle the connection. 
	 */
	@Override
	public void handleSession() {
		try {
			Object object;
			while((object = reader.readObject()) != null) {
				handleInput(object);
			}
			sendOutput("Bye!");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Handle the input from different objects.
	 * @param object
	 * @throws IOException
	 */
	public void handleInput(Object object) throws IOException { 
		BuildCarModelOptions build = new BuildCarModelOptions(); 
		String className = object.getClass().getSimpleName();
		
		if(className.equals("Properties")) {
			Properties prop = (Properties) object;
			build.buildAutoFromProperties(prop);
			sendOutput("An Automobile instance is successfully created!\n");
		} else if(className.equals("String")) {
			String request = (String) object;
			if(request.equals("configuration")) {
				sendOutput(build.getModelList());
			} else {
				sendOutput(build.getModelByRequest(request));
			}
		} 
	}
	/**
	 * Send output to the Client.
	 * @param obj
	 * @throws IOException
	 */
	public void sendOutput(Object obj) throws IOException { 
		writer.writeObject(obj);
		writer.flush();
	}
	/**
	 * Close the connection when finished.
	 */
	@Override
	public void closeSession() {
		try {
			writer = null; reader = null; socket.close();
		} catch (IOException e) {
			if (DEBUG) 
				System.err.println("Error closing socket to " + strHost);
		}
	}
	/**
	 * Set the Host.
	 * @param strHost
	 */
	public void setHost(String strHost) { 
		this.strHost = strHost;
	}
	/**
	 * Set the Port.
	 * @param iPort
	 */
	public void setPort(int iPort) { 
		this.iPort = iPort;
	}
}
