/**
 * @author Junjian Xie
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SQL {
	private Properties prop;
	private final String fileName = "data/SQL.properties";
	
	public SQL() {
		try {
			FileInputStream in = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public String createDB() {
        return prop.getProperty("CREATE_DB");   
    }
    
    public String checkTableExist() {
        return prop.getProperty("CHECK_TABLE_EXISTS");     
    }
    
    public String createAutomobilesTable() { 
        return prop.getProperty("CREATE_AUTOS");   
    }
    
    public String createOptionSetsTable() {  
        return prop.getProperty("CREATE_OPTIONSETS");
        
    }
    
    public String createOptionsTable() {
        return prop.getProperty("CREATE_OPTIONS"); 
    }
    
    public String insertAutomobile() {
        return prop.getProperty("INSERT_AUTO"); 
    }
    
    public String insertOptionSet() {
        return prop.getProperty("INSERT_OPTIONSET"); 
    }
    
    public String insertOption() {
        return prop.getProperty("INSERT_OPTION"); 
    }

    public String deleteAutomobile() {
        return prop.getProperty("DELETE_AUTO"); 
    }
    
    public String getAutomobileID() {
        return prop.getProperty("GET_AUTOID");    
    }
    
    public String getAutomobile() {
        return prop.getProperty("GET_AUTO"); 
    }
    
    public String getOptionSets() {
        return prop.getProperty("GET_OPTIONSETS");   
    }
    
    public String getOptions() {
        return prop.getProperty("GET_OPTIONS");   
    }
    
    public String getOptionPrice() {
        return prop.getProperty("GET_OPTIONPRICE");   
    }
}
