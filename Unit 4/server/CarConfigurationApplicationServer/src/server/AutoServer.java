package server;

import java.util.ArrayList;
import java.util.Properties;

import model.Automobile;

public interface AutoServer {
	void buildAutoFromProperties(Properties prop);
	//Build an Automobile object from the properties file.
	ArrayList<String> getModelList();
	//Get a list of models.
	Automobile getModelByRequest(String name);
	//Get the model by request.
}
