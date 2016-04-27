package client;

import java.util.ArrayList;
import model.Automobile;

public interface ServerSocketIO {
	Automobile getAutomobileObject(String modelName);
	//Get an Automobile object from the server with a particular model name.
	ArrayList<String> getAutomobileList();
	//Get a list of Automobile models from the server.
}
