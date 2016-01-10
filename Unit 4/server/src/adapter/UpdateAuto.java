/**
 * @author Junjian Xie
 */
package adapter;

import exception.AutoException;

public interface UpdateAuto {
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) throws AutoException;
	//This function searches the Model for a given OptionSet and sets the name of OptionSet to 
	//newName.
	
	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice) throws AutoException;
	//This function searches the Model for a given OptionSet and Option name, and sets the price to 
	//newPrice.
}
