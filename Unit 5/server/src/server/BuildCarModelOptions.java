/**
 * @author Junjian Xie
 */
package server;

import java.util.ArrayList;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;

public class BuildCarModelOptions implements AutoServer {
	/**
	 * Build an Automobile object from the properties file.
	 */
	@Override
	public void buildAutoFromProperties(Properties prop) {
		AutoServer as = new BuildAuto();
		as.buildAutoFromProperties(prop);
	}
	/**
	 * Get a list of models.
	 */
	@Override
	public ArrayList<String> getModelList() {
		AutoServer as = new BuildAuto();
		return as.getModelList();
	}
	/**
	 * Get the model by request.
	 */
	@Override
	public Automobile getModelByRequest(String name) {
		AutoServer as = new BuildAuto();
		return as.getModelByRequest(name);
	}
}
