/**
 * @author Junjian Xie
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import model.Automobile;

public class DefaultSocketClient extends Thread implements SocketClientConstants, SocketClientInterface {
	private ObjectInputStream reader; 
	private ObjectOutputStream writer;
	private Socket socket;
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
			socket = new Socket(strHost, iPort);
		} catch(IOException socketError) {
			if (DEBUG)
				System.err.println("Unable to connect to " + strHost);
			return false; 
		}
		try {
			reader = new ObjectInputStream(socket.getInputStream()); 
			writer = new ObjectOutputStream(socket.getOutputStream()); 
		} catch (Exception e) {
			if (DEBUG) 
				System.err.println("Unable to obtain stream to/from " + strHost); 
			return false;
		}
		return true;
	}
	/**
	 * Handle the connection. 
	 */
	@Override
	public void handleSession() {
		Scanner scanner = new Scanner(System.in);
		String userInput;
		
		try {
			while(true) {
				System.out.println("Please input a number:\n1.Upload a properties file\n"
						+ "2.Configure a car.");
				userInput = scanner.nextLine();
				if(userInput.equals(null)) {
					sendOutput(null);
					System.out.println((String) reader.readObject());
					break;
				}
				handleInput(userInput);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Handle the input with different choices.
	 */
	public void handleInput(String userInput) throws IOException, ClassNotFoundException { 
		CarModelOptionsIO io = new CarModelOptionsIO(); 
		SelectCarOption sco = new SelectCarOption(); 
		Scanner scanner = new Scanner(System.in);
		
		if(userInput.equals("1")) {
			System.out.println("Please input a file path:");
			String fileName = scanner.nextLine();
			Properties prop = io.buildProperties(fileName);
			System.out.println("Filename is: " + fileName);
			sendOutput(prop);
			System.out.println((String) reader.readObject());
		} else if(userInput.equals("2")) {
			sendOutput("configuration");
			ArrayList<String> modelList = (ArrayList<String>) reader.readObject();
			System.out.println("The following is the model list that you can configure:");
			for(String name : modelList) {
				System.out.println(name);
			}
			System.out.println();
			System.out.println("Please select one:");
			String modelName = scanner.nextLine();
			sendOutput(modelName);
			
			Automobile auto = (Automobile) reader.readObject();
			sco.selectCarOption(auto);
		} else {
			System.out.print("Please input \"1\" for uploading or \"2\" for configuring!");
		}
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
	/**
	 * Close the connection when finished.
	 */
	@Override
	public void closeSession() {
		try {
			writer = null; 
			reader = null; 
			socket.close();
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
