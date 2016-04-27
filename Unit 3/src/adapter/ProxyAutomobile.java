package adapter;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import exception.AutoException;
import exception.ErrorType;
import model.Automobile;
import scale.EditAuto;
import util.FileIO;

public abstract class ProxyAutomobile {
	private Automobile a1;
	private static LinkedHashMap <String,Automobile> lhsMap = new LinkedHashMap<String,Automobile>();
	/**
	 * Read data from a file to memory and build the Automobile model.
	 * @param filename
	 */
	public void buildAuto(String filename) {
		FileIO fio = new FileIO();
		a1 = fio.buildAutomotiveObject(filename);
	//	a1.print();
		lhsMap.put(a1.getModel(), a1);
	}
	/**
	 * Print the data in a particular format.
	 * @param Modelname
	 */
	public void printAuto(String Modelname) {
		try {
			Iterator<Entry<String, Automobile>> lit = lhsMap.entrySet().iterator();
			while(lit.hasNext()) {
				Entry<String, Automobile> e = (Entry<String, Automobile>) lit.next();
				if(e.getValue().getModel().equals(Modelname)) {
					e.getValue().print();
					return;
				}
				throw new AutoException(ErrorType.NO_MODELNAME.ordinal(), "Exception: Model is not found!");
			}
		} catch(AutoException ae) {
			AutoException.log(ae);
			Modelname = ae.fix(ErrorType.NO_MODELNAME.ordinal());
			printAuto(Modelname);
		}
	}
	/**
	 * Update the name of an OptionSet.
	 * @param Modelname
	 * @param OptionSetname
	 * @param newName
	 */
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) {
		try {
			Iterator<Entry<String, Automobile>> lit = lhsMap.entrySet().iterator();
			while(lit.hasNext()) {
				Entry<String, Automobile> e = (Entry<String, Automobile>) lit.next();
				if(e.getValue().getModel().equals(Modelname)) {
					e.getValue().updateOptionSet(OptionSetname, newName);
					return;
				}
				throw new AutoException(ErrorType.NO_OPTIONSETNAME.ordinal(), "Exception: OptionSet is not found!");
			}
		} catch(AutoException ae) {
			AutoException.log(ae);
			OptionSetname = ae.fix(ErrorType.NO_OPTIONSETNAME.ordinal());
			updateOptionSetName(Modelname, OptionSetname, newName);
		}
	}
	/**
	 * Update the price of an Option.
	 * @param Modelname
	 * @param Optionname
	 * @param Option
	 * @param newprice
	 */
	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice) {
		try {
			Iterator<Entry<String, Automobile>> lit = lhsMap.entrySet().iterator();
			while(lit.hasNext()) {
				Entry<String, Automobile> e = (Entry<String, Automobile>) lit.next();
				if(e.getValue().getModel().equals(Modelname)) {
					e.getValue().updateOptionPrice(Optionname, Option, newprice);
					return;
				}
				throw new AutoException(ErrorType.NO_OPTIONNAME.ordinal(), "Exception: Option is not found!");
			}
		} catch(AutoException ae) {
			AutoException.log(ae);
			Option = ae.fix(ErrorType.NO_OPTIONNAME.ordinal());
			updateOptionPrice(Modelname, Optionname, Option, newprice);
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
		a1 = lhsMap.get(modelName);
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
		a1 = lhsMap.get(modelName);
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
		a1 = lhsMap.get(modelName);
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
		a1 = lhsMap.get(modelName);
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
		a1 = lhsMap.get(modelName);
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
		a1 = lhsMap.get(modelName);
		if(a1 == null) {
			return;
		}
		String name = a1.findOptionName(setName, option);
		System.out.println("OptionSet: " + setName + "\nOption: " + name);
	}
}
