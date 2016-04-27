package adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;

import database.Database;

import java.util.Map.Entry;
import exception.AutoException;
import exception.ErrorType;
import model.Automobile;
import scale.EditAuto;
import util.FileIO;

public abstract class ProxyAutomobile {
	private Automobile a1;
	private static LinkedHashMap <String,Automobile> autoLHM = new LinkedHashMap<String,Automobile>();
	/**
	 * Read data from a text or properties file to memory and build the Automobile model.
	 * @param filename
	 * @throws IOException 
	 */
	public void buildAuto(String filename, String fileType) throws IOException {
		if(fileType.equals("txt")) {
			FileIO fio = new FileIO();
			a1 = fio.buildAutomobileObject(filename);
			autoLHM.put(a1.getModel(), a1);
		} else if(fileType.equals("properties")) {
			FileIO fio = new FileIO();
			fio.buildPropertiesObject(filename);
		}
	}
	
	public void builAutomobile(String filename, String fileType) throws IOException {
		if(fileType.equals("txt")) {
			FileIO fio = new FileIO();
			a1 = fio.buildAutomobileObject(filename);
			autoLHM.put(a1.getModel(), a1);
			Database db = new Database();
			db.insertAutomobile(a1);
		} else if(fileType.equals("properties")) {
			FileIO fio = new FileIO();
			fio.buildPropertiesObject(filename);
		}
	}
	/**
	 * Print the data in a particular format.
	 * @param Modelname
	 */
	public void printAuto(String Modelname) {
		Automobile auto = getInstance(Modelname);
		if(auto == null) {
			System.out.println("Could not find the model!");
		} else {
			auto.print();
		}
	}
	/**
	 * Update the name of an OptionSet.
	 * @param Modelname
	 * @param OptionSetname
	 * @param newName
	 * @throws AutoException 
	 */
	public void updateOptionSetName(String modelName, String optionSetname, String newName) throws AutoException {
		Automobile auto = getInstance(modelName);
		if(auto == null) {
			System.out.println("Could not find the model!");
		} else {
			auto.updateOptionSet(optionSetname, newName);
			Database db = new Database();
			db.updateAutomobile(modelName, auto);
		}
	}
	/**
	 * Update the name of an Option.
	 * @param modelName
	 * @param optionSetname
	 * @param optionName
	 * @param newName
	 */
	public void updateOptionName(String modelName, String optionSetName,String optionName, String newName) {
		Automobile auto = getInstance(modelName);
		if(auto == null) {
			System.out.println("Could not find the model!");
		} else {
			auto.updateOptionName(optionSetName, optionName, newName);
			Database db = new Database();
			db.updateAutomobile(modelName, auto);
		}
	}
	/**
	 * Update the price of an Option.
	 * @param Modelname
	 * @param Optionname
	 * @param Option
	 * @param newprice
	 * @throws AutoException 
	 */
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) throws AutoException {
		Automobile auto = getInstance(modelName);
		if(auto == null) {
			System.out.println("Could not find the model!");
		} else {
			auto.updateOptionPrice(optionSetName, optionName, newPrice);
			Database db = new Database();
			db.updateAutomobile(modelName, auto);
		}
	}
	/**
	 * Delete the Automobile according to the name.
	 * @param modelName
	 */
	public void deleteAutomoblie(String modelName) {
		Automobile auto = getInstance(modelName);
		if(auto == null) {
			System.out.println("Could not find the model!");
		} else {
			Database db = new Database();
			db.deleteAutomobile(modelName);
		}
	}
	/**
	 * Set the choice from the Option.
	 * @param setName
	 * @param optionName
	 */
	public void setOptionchoice(String setName, String optionName) {
		a1.setOptionChoice(setName, optionName);
	}
	/**
	 * Display the choice.
	 */
	public void printChoice() {
		a1.printChoice();
	}
	/**
	 * Get the total price for the model after choosing preferences.
	 * @return
	 */
	public float getTotalPrice() {
		return a1.getTotalPrice(); 
	}
	/**
	 * Get the price of a choice.
	 * @param setName
	 * @return
	 */
	public float getOptionChoicePrice(String setName) {
		return a1.getOptionChoicePrice(setName);
	}
	/**
	 * Get the price of a particular Option.
	 * @param modelName
	 * @param setName
	 * @param option
	 * @return
	 */
	public synchronized float getOptionPrice(String modelName, String setName, String option) {
		a1 = autoLHM.get(modelName);
		if(a1 == null) {
			return -1;
		}
		return a1.findOptionPrice(setName, option);
	}
	/**
	 * Get the name of a particular Option.
	 * @param modelName
	 * @param setName
	 * @param option
	 * @return
	 */
	public synchronized String getOptionName(String modelName, String setName, String option) {
		a1 = autoLHM.get(modelName);
		if(a1 == null) {
			return null;
		}
		return a1.findOptionName(setName, option);
	}
	/**
	 * Edit the price of a particular Option.
	 * @param modelName
	 * @param setName
	 * @param option
	 * @param newPrice
	 */
	public synchronized void editOptionPrice(String modelName, String setName, String option, float newPrice) {
		a1 = autoLHM.get(modelName);
		if(a1 == null) {
			return;
		}
		try {
			a1.updateOptionPrice(setName, option, newPrice);
		} catch (AutoException ae) {
			ae.printStackTrace();
			option = ae.fix(ErrorType.NO_OPTIONNAME.ordinal());
			editOptionPrice(modelName, setName, option, newPrice);
		};
	}
	/**
	 * Edit the name of a particular Option.
	 * @param modelName
	 * @param setName
	 * @param option
	 * @param newName
	 */
	public synchronized void editOptionName(String modelName, String setName, String option, String newName) {
		a1 = autoLHM.get(modelName);
		if(a1 == null) {
			return;
		}
		a1.updateOptionName(setName, option, newName);
	}
	/**
	 * Print the price of a particular Option.
	 * @param modelName
	 * @param setName
	 * @param option
	 */
	public synchronized void printOptionPrice(String modelName, String setName, String option) {
		a1 = autoLHM.get(modelName);
		if(a1 == null) {
			return;
		}
		float price = a1.findOptionPrice(setName, option);
		System.out.println("OptionSet: " + setName + "\nOption: " + option + "\nPrice: $" + price);
	}
	/**
	 * Print the name of a particular Option.
	 * @param modelName
	 * @param setName
	 * @param option
	 */
	public synchronized void printOptionName(String modelName, String setName, String option) {
		a1 = autoLHM.get(modelName);
		if(a1 == null) {
			return;
		}
		String name = a1.findOptionName(setName, option);
		System.out.println("OptionSet: " + setName + "\nOption: " + name);
	}
	/**
	 * Build an Automobile object from the properties file.
	 * @param prop
	 */
	public void buildAutoFromProperties(Properties prop) {
		String  model = prop.getProperty("CarModel");
		if(model != null) {
			float basePrice = Float.parseFloat(prop.getProperty("BasePrice"));
			String make = prop.getProperty("CarMake");
			Automobile auto = new Automobile(model, basePrice);
			
			String optionSet = "Option";
			String option = "OptionValue";
			String optionPrice = "OptionPrice";
			int optionSetIndex = 1;
			int optionIndex;
			while(true) {
				optionIndex = 1;
				String optSetName = prop.getProperty(optionSet + optionSetIndex);
				if(optSetName == null) 
					break;
				auto.setOptionSet(optSetName);
				while(true) {
					String optName = prop.getProperty(option + optionSetIndex + 
							"-" + optionIndex);
					if(optName == null)
						break;
					float optPrice = Float.parseFloat(prop.getProperty(optionPrice 
							+ optionSetIndex + "-" +optionIndex));
					auto.setOption(optSetName, optName, optPrice);
					++optionIndex;
				}
				++optionSetIndex;
			}
			autoLHM.put(auto.getModel(), auto);
		}
	}
	/**
	 * Get a list of models.
	 * @return
	 */
	public ArrayList<String> getModelList() {
		ArrayList<String> result = new ArrayList<String>();
		for(Entry<String, Automobile> entry : autoLHM.entrySet()) {
			result.add(entry.getKey());
		}
		return result;
	}
	/**
	 * Get an instance of an Automobile.
	 * @param name
	 * @return
	 */
	public Automobile getInstance(String name) {
		Database db = new Database();
		return db.getAutomobile(name);
	}
	/**
	 * Get the model by request.
	 * @param name
	 * @return
	 */
	public Automobile getModelByRequest(String name) {
		return autoLHM.get(name);
	}
}
