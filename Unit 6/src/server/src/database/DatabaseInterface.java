/**
 * @author Junjian Xie
 */
package database;

import java.sql.Connection;
import java.util.ArrayList;

import model.Automobile;

public interface DatabaseInterface {
	Connection getConnection();
	
	void insertAutomobile(Automobile auto);
	
	void insertOptionSet(String modelName, String setName);
	
	void insertOption(String modelName, String setName, String optionName, float price);
	
	Automobile getAutomobile(String modelName);
	
	ArrayList<String> getOptionSetList(int autoID);
	
	ArrayList<String> getOptionList(int autoID, String setName);
	
	float getOptionPrice(int autoID, String setName, String optionName);

	void deleteAutomobile(String modelName);
	
	void updateAutomobile(String modelName, Automobile auto);
}
